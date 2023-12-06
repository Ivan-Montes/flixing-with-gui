package ime.flixing.mvc.view.flix_person_position;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Collections;
import java.util.List;
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

import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.mvc.controller.FlixPersonPositionController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FlixPersonPositionEditView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnDelete;
	private JButton btnSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixPersonPositionEditView dialog = new FlixPersonPositionEditView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixPersonPositionEditView() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			btnSearch = new JButton("Search");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 247, SpringLayout.WEST, contentPanel);
			btnSearch.addActionListener( e -> searchAndShow() );
			contentPanel.add(btnSearch);
		}
		{
			btnDelete = new JButton("Delete");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnDelete, 104, SpringLayout.WEST, contentPanel);
			btnDelete.addActionListener( e -> deleteFlixPersonPosition() );
			contentPanel.add(btnDelete);
		}
		{
			JButton btnDetails = new JButton("Details");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnDetails);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnDetails, -10, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnDetails, -100, SpringLayout.EAST, contentPanel);
			btnDetails.addActionListener( e -> showDetails() );
			contentPanel.add(btnDetails);
		}
		{
			JScrollPane spTable = new JScrollPane();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, spTable, 6, SpringLayout.SOUTH, btnSearch);
			sl_contentPanel.putConstraint(SpringLayout.WEST, spTable, 10, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, spTable, 319, SpringLayout.SOUTH, btnSearch);
			sl_contentPanel.putConstraint(SpringLayout.EAST, spTable, 604, SpringLayout.WEST, contentPanel);
			contentPanel.add(spTable);
			{
				table = new JTable();
				table.setFillsViewportHeight(true);
				spTable.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}
	
	private void searchAndShow() {
		
		Optional<List<FlixPersonPosition>>optFlixPersonPosition = FlixPersonPositionController.getAllFlixPersonPosition();
		
		if ( optFlixPersonPosition.isPresent() ) {
			
			Object[][] aEntities = IntStream.range(0, optFlixPersonPosition.get().size())
					.mapToObj( n -> new Object[] {
						optFlixPersonPosition.get().get(n).getId().getFlixId(),
						optFlixPersonPosition.get().get(n).getFlix().getTitle(),
						optFlixPersonPosition.get().get(n).getFlix().getGenre().getName(),
						optFlixPersonPosition.get().get(n).getId().getPersonId(),
						optFlixPersonPosition.get().get(n).getPerson().getName(),
						optFlixPersonPosition.get().get(n).getPerson().getSurname(),
						optFlixPersonPosition.get().get(n).getId().getPositionId(),
						optFlixPersonPosition.get().get(n).getPosition().getName()
					})
					.toArray(Object[][]::new);
					
			
			table.setModel(new DefaultTableModel(aEntities, new String [] {"Flix Code","Flix Name", "Flix Genre", "Person Code", "Person Name", "Person Surname", "Position Code", "Position Name"} ){
				
		        /**
				 * JTable treats the first columns as Object instead of Integer. Now, it should sort based on the integer values.
				 */
				private static final long serialVersionUID = 6529269790518969819L;

				@Override
		        public Class<?> getColumnClass(int columnIndex) {
		            if (columnIndex == 0 || columnIndex == 3 || columnIndex == 6) return Integer.class;
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
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			
		}else {
			
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_UNEXPECTED);
		}
	}
	
	private void deleteFlixPersonPosition() {
		
		List<Object> list = getSelectedCodesFromTable();
		
		if ( list != null && !list.isEmpty() ) {
			
			if ( Checker.checkDigits( String.valueOf( list.get(0) ) )
					&& Checker.checkDigits( String.valueOf( list.get(1) ) )
					&& Checker.checkDigits( String.valueOf( list.get(2) ) )
					) {
				
				if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
						 == JOptionPane.OK_OPTION ){					
					
					
					int returnValue = FlixPersonPositionController.deleteFlixPersonPosition(
							String.valueOf( list.get(0) ), 
									String.valueOf( list.get(1) ), 
											String.valueOf( list.get(2) )
							);
					JOptionPane.showMessageDialog(this, DecoHelper.chooseResultMessageByReturnValue((long) returnValue));
				}
				
			}else {
				
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_CHECKER);

			}
			
		}else {
			
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);

		}
		
	}
	
	private void showDetails() {
		
	}
	
	private List<Object> getSelectedCodesFromTable() {
		
		int viewRow = table.getSelectedRow();
		List<Object> list = Collections.emptyList();

		
		if (viewRow >= 0){
			
			int modelRow = table.convertRowIndexToModel(viewRow);
			
			Object flixId = table.getModel().getValueAt(modelRow, 0);
			Object personId = table.getModel().getValueAt(modelRow, 3);
			Object positionId = table.getModel().getValueAt(modelRow, 6);			
			
			list =  List.of(flixId, personId, positionId);
		}
		
		return list;
	}

	private void reloadTable() {
		
		
	}
}
