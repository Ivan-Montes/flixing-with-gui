package ime.flixing.mvc.view.flix;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

@Getter
@Setter
public class FlixGetAllView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnSearch;
	private JButton btnBack;
	private JScrollPane spFlixes;
	private JTable jTableFlixes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixGetAllView dialog = new FlixGetAllView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixGetAllView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchAndShowFlixes();
				
			}
		});
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 159, SpringLayout.WEST, contentPanel);
		contentPanel.add(btnSearch);
		
		btnBack = new JButton("Back");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnBack, 0, SpringLayout.EAST, btnSearch);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		contentPanel.add(btnBack);
		
		spFlixes = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spFlixes, 12, SpringLayout.SOUTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spFlixes, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spFlixes, -6, SpringLayout.NORTH, btnBack);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spFlixes, 419, SpringLayout.WEST, contentPanel);
		spFlixes.setPreferredSize(new Dimension(73, 39));
		contentPanel.add(spFlixes);
		
		jTableFlixes = new JTable();
		jTableFlixes.setFillsViewportHeight(true);
		jTableFlixes.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null}
	            },
	            new String [] {
	            		"FlixId", "Title", "GenreId", "GenreName"
	            }
	        ));

		jTableFlixes.setName("jTableFlixes");
		spFlixes.setViewportView(jTableFlixes);
		
	}
	
	private void searchAndShowFlixes() {		

		Optional<List<Flix>> optListFlix = FlixController.searchAll();
		
		if ( optListFlix.isPresent() ) {			
			
			String [] aTitles = {"FlixId", "Title", "GenreId", "GenreName"};
			Object[][] aFlix = IntStream.range(0, optListFlix.get().size() )
                    .mapToObj((i) -> new Object []{
                    		optListFlix.get().get(i).getFlixId(),
                    		optListFlix.get().get(i).getTitle(),
                    		optListFlix.get().get(i).getGenre() !=null?optListFlix.get().get(i).getGenre().getGenreId():null,
                    		optListFlix.get().get(i).getGenre() !=null?optListFlix.get().get(i).getGenre().getName():null
                    		})
                    .toArray(Object[][]::new);
			
			DefaultTableModel modelTabla = new DefaultTableModel(aFlix,aTitles);
		    jTableFlixes.setModel(modelTabla);
		    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTableFlixes.getModel());
		    jTableFlixes.setRowSorter(sorter);
		    jTableFlixes.getColumnModel().getColumn(0).setPreferredWidth(22);
		    jTableFlixes.getColumnModel().getColumn(2).setPreferredWidth(22);
		    jTableFlixes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}		
	}
	
	
}
