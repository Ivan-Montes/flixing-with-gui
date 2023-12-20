package ime.flixing.mvc.view.flix;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.DecoHelper;
import lombok.Getter;
import lombok.Setter;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.util.logging.Logger;
import java.util.logging.Level;

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
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixGetAllView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixGetAllView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener( e -> searchAndShowFlixes() );				
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 159, SpringLayout.WEST, contentPanel);
		contentPanel.add(btnSearch);
		
		btnBack = new JButton("Back");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, btnBack, 0, SpringLayout.EAST, btnSearch);
		btnBack.addActionListener( e -> dispose());
		contentPanel.add(btnBack);
		
		spFlixes = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlixes, 12, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlixes, 5, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spFlixes, -6, SpringLayout.NORTH, btnBack);
		slContentPanel.putConstraint(SpringLayout.EAST, spFlixes, 419, SpringLayout.WEST, contentPanel);
		spFlixes.setPreferredSize(new Dimension(73, 39));
		contentPanel.add(spFlixes);
		
		jTableFlixes = new JTable();
		jTableFlixes.setFillsViewportHeight(true);
		spFlixes.setViewportView(jTableFlixes);
		
	}
	
	private void searchAndShowFlixes() {		

		Optional<List<Flix>> optListFlix = new FlixController().getAllFlix();
		
		if ( optListFlix.isPresent() ) {			
			
			Object[][] aFlix = IntStream.range(0, optListFlix.get().size() )
                    .mapToObj( i -> new Object []{
                    		optListFlix.get().get(i).getFlixId(),
                    		optListFlix.get().get(i).getTitle(),
                    		optListFlix.get().get(i).getGenre() !=null?optListFlix.get().get(i).getGenre().getGenreId():null,
                    		optListFlix.get().get(i).getGenre() !=null?optListFlix.get().get(i).getGenre().getName():null
                    		})
                    .toArray(Object[][]::new);
			
			DefaultTableModel modelTabla = new DefaultTableModel(aFlix, new String []{"FlixId", "Title", "GenreId", "GenreName"}){
				
		        /**
				 * JTable treats the first columns as Object instead of Integer. Now, it should sort based on the integer values.
				 */
				private static final long serialVersionUID = 6529289790518969819L;

				@Override
		        public Class<?> getColumnClass(int columnIndex) {
		            if (columnIndex == 0) return Integer.class;
		            return super.getColumnClass(columnIndex);
		        }
				
				/**
				 * JTable treats cells as not editable
				 */
				@Override
			    public boolean isCellEditable(int row, int column) {			       
			       return false;
			    }
		    };
		    
		    jTableFlixes.setModel(modelTabla);
		    jTableFlixes.setRowSorter(new TableRowSorter<>(jTableFlixes.getModel()));
		    jTableFlixes.getColumnModel().getColumn(0).setPreferredWidth(22);
		    jTableFlixes.getColumnModel().getColumn(2).setPreferredWidth(22);
		    jTableFlixes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}		
	}
	
	
}
