package ime.flixing.mvc.view.genre;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;

public class GenreGetAllView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tGenres;
	private JTextArea taDescription;
	private JButton btnSearch;
	private Map<Long,String>descriptionMap;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreGetAllView dialog = new GenreGetAllView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreGetAllView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreGetAllView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		{
			btnSearch = new JButton("Search");
			slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.EAST, btnSearch, -265, SpringLayout.EAST, contentPanel);
			btnSearch.addActionListener( e -> searchAllAndShow() );
			contentPanel.add(btnSearch);
		}
		{
			JButton btnClean = new JButton("Clean");
			slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, btnSearch);
			slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 88, SpringLayout.EAST, btnSearch);
			btnClean.addActionListener( e -> cleanFields() );
			contentPanel.add(btnClean);
		}
		
		JScrollPane spGenres = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenres, 18, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenres, 10, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spGenres, -69, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spGenres, -12, SpringLayout.EAST, contentPanel);
		contentPanel.add(spGenres);
		
		tGenres = new JTable();
		tGenres.setFillsViewportHeight(true);
		tGenres.setModel(new DefaultTableModel());
		ListSelectionModel listSelectionModel = tGenres.getSelectionModel();
		listSelectionModel.addListSelectionListener( this::fillDescriptionText );
		tGenres.setSelectionModel(listSelectionModel);		
		spGenres.setViewportView(tGenres);
		slContentPanel.putConstraint(SpringLayout.NORTH, tGenres, 76, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, tGenres, 73, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tGenres, 0, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, tGenres, 0, SpringLayout.EAST, contentPanel);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, -46, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 31, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -10, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, -33, SpringLayout.EAST, contentPanel);
		contentPanel.add(spDescription);
		
		taDescription = new JTextArea();
		taDescription.setEditable(false);
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		spDescription.setViewportView(taDescription);
		
		JLabel lblDescription = new JLabel("Description");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 6, SpringLayout.SOUTH, spGenres);
		slContentPanel.putConstraint(SpringLayout.WEST, lblDescription, 171, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblDescription);
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
		
		Optional<List<Genre>> optListGenre = new GenreController().getAllGenre();
		
		if ( optListGenre.isPresent() ) {
			
			descriptionMap = new HashMap<>();
			Object[][] aGenres = IntStream.range(0, optListGenre.get().size() )
									.peek( m-> descriptionMap.put(optListGenre.get().get(m).getGenreId(),
																	optListGenre.get().get(m).getDescription() ) )
                    				.mapToObj( i -> new Object []{
                    						optListGenre.get().get(i).getGenreId(),
                    						optListGenre.get().get(i).getName()
                    				}).toArray(Object[][]::new);
			
			tGenres.setModel(new DefaultTableModel(aGenres, new String [] {"GenreId", "GenreName"} ) );
			tGenres.setDefaultEditor(Object.class, null);
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(tGenres.getModel());
			tGenres.setRowSorter(sorter);
			
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
        	
        	Object objId = tGenres.getValueAt(tGenres.getSelectedRow(), 0 );        	
        	String descrip = descriptionMap.get(objId);        	
        	taDescription.setText(descrip);
        }
	}
}
