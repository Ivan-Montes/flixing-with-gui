package ime.flixing.mvc.view.genre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ime.flixing.entity.Genre;
import ime.flixing.mvc.controller.GenreController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;

public class GenreGetAllView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tGenres;
	private JTextArea taDescription;
	private JButton btnSearch;
	private List<String>descriptionList;
	private Map<Long,String>descriptionMap;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreGetAllView dialog = new GenreGetAllView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreGetAllView() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			btnSearch = new JButton("Search");
			btnSearch.addActionListener( e -> searchAllAndShow() );
			contentPanel.add(btnSearch);
		}
		{
			JButton btnClean = new JButton("Clean");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnClean, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnClean, 235, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, btnClean);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnSearch, -76, SpringLayout.WEST, btnClean);
			btnClean.addActionListener( e -> cleanFields() );
			contentPanel.add(btnClean);
		}
		
		JScrollPane spGenres = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spGenres, 18, SpringLayout.SOUTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spGenres, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spGenres, -69, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spGenres, -12, SpringLayout.EAST, contentPanel);
		contentPanel.add(spGenres);
		
		tGenres = new JTable();
		tGenres.setFillsViewportHeight(true);
		tGenres.setModel(new DefaultTableModel());
		tGenres.setName("jTableGenres");
		//TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tGenres.getModel());
		//tGenres.setRowSorter(sorter);
		ListSelectionModel listSelectionModel = tGenres.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener( e -> fillDescriptionText(e) );
		tGenres.setSelectionModel(listSelectionModel);		
		spGenres.setViewportView(tGenres);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tGenres, 76, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tGenres, 73, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tGenres, 0, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tGenres, 0, SpringLayout.EAST, contentPanel);
		
		JScrollPane spDescription = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spDescription, -46, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spDescription, 31, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -10, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spDescription, -33, SpringLayout.EAST, contentPanel);
		contentPanel.add(spDescription);
		
		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		spDescription.setViewportView(taDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose());
				buttonPane.add(btnBack);
			}
		}
	}
	
	private void searchAllAndShow() {
		
		Optional<List<Genre>> optListGenre = GenreController.getAllGenre();
		
		if ( optListGenre.isPresent() ) {
			
			descriptionMap = new HashMap<>();
			descriptionList = new ArrayList<>();
			Object[][] aGenres = IntStream.range(0, optListGenre.get().size() )
									.peek( n-> descriptionList.add(optListGenre.get().get(n).getDescription() ) )
									.peek( m-> descriptionMap.put(optListGenre.get().get(m).getGenreId(),
																	optListGenre.get().get(m).getDescription() ) )
                    				.mapToObj( i -> new Object []{
                    						optListGenre.get().get(i).getGenreId(),
                    						optListGenre.get().get(i).getName()
                    				}).toArray(Object[][]::new);
			
			tGenres.setModel(new DefaultTableModel(aGenres, new String [] {"GenreId", "GenreName"} ) );
			//TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tGenres.getModel());
			//tGenres.setRowSorter(sorter);
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
		}
		
	}
	
	private void cleanFields() {
		
		tGenres.setModel(new DefaultTableModel());
		taDescription.setText("");
	}
	
	private void fillDescriptionText(ListSelectionEvent e) {
		
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        int minIndex = lsm.getMinSelectionIndex();
        
        if ( !e.getValueIsAdjusting() && minIndex >= 0 ) {        	
        	
        	//Sacar el value de la columna Genre Id
        	Object objId = tGenres.getValueAt(tGenres.getSelectedRow(), tGenres.getSelectedColumn());
        	
        	//Buscar en el Map por genre id/clave para obtener la descripcion
        	
        	
        	//Mostrar
        	//System.out.println(descriptionList.get(minIndex));
        	taDescription.setText(descriptionList.get(minIndex));
        }
	}
	
}
