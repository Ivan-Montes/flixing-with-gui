package ime.flixing.mvc.view.position;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import ime.flixing.entity.Position;


public class PositionGetAllView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tPosition;
	private JTextArea taDescription;
	private JButton btnSearch;
	private Map<Long,String>descriptionMap;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PositionGetAllView dialog = new PositionGetAllView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PositionGetAllView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionGetAllView() {

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
		
		JScrollPane spPosition = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spPosition, 18, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, spPosition, 10, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spPosition, -69, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spPosition, -12, SpringLayout.EAST, contentPanel);
		contentPanel.add(spPosition);
		
		tPosition = new JTable();
		tPosition.setFillsViewportHeight(true);
		tPosition.setModel(new DefaultTableModel());		
		ListSelectionModel listSelectionModel = tPosition.getSelectionModel();
		listSelectionModel.addListSelectionListener( e -> fillDescriptionText() );
		tPosition.setSelectionModel(listSelectionModel);
		
		spPosition.setViewportView(tPosition);
		slContentPanel.putConstraint(SpringLayout.NORTH, tPosition, 76, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, tPosition, 73, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tPosition, 0, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, tPosition, 0, SpringLayout.EAST, contentPanel);
		
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
		slContentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 6, SpringLayout.SOUTH, spPosition);
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

	private void fillDescriptionText() {
		
		int viewRow = tPosition.getSelectedRow();
		
		if (viewRow >= 0){
			
			int modelRow =tPosition.convertRowIndexToModel(viewRow);
			
			Object objId = tPosition.getModel().getValueAt(modelRow, 0);
			taDescription.setText(descriptionMap.get(objId));
		}
	}


	private void cleanFields() {
		
		tPosition.setModel(new DefaultTableModel());
		taDescription.setText("");
	}

	private void searchAllAndShow() {
		
		Optional<List<Position>>optPosition = PositionController.getAllPosition();
		
		if ( optPosition.isPresent() ) {
			
			descriptionMap = new HashMap<>();
			Object[][]aPosition = IntStream.range(0, optPosition.get().size())
					.peek( n -> descriptionMap.put(optPosition.get().get(n).getPositionId(), 
													optPosition.get().get(n).getDescription()))
					.mapToObj( m -> new Object[] {
							optPosition.get().get(m).getPositionId(), 
							optPosition.get().get(m).getName()
					})
					.toArray(Object[][]::new);
			
			tPosition.setModel(new DefaultTableModel(aPosition, new String [] {"Position Code", "Position Name"} ){
				
		        /**
				 * JTable treats the first columns as Object instead of Integer. Now, it should sort based on the integer values.
				 */
				private static final long serialVersionUID = 6529269790518969819L;

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
		    });
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(tPosition.getModel());
			tPosition.setRowSorter(sorter);
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
		}
	}
}
