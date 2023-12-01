package ime.flixing.mvc.view.person;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ime.flixing.entity.Person;
import ime.flixing.mvc.controller.PersonController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PersonGetAllView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnSearch;
	private JSeparator separator;
	private JTable tPerson;
	private JScrollPane spPerson;
	private JButton btnClean;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonGetAllView dialog = new PersonGetAllView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonGetAllView() {
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			btnSearch = new JButton("Search");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 86, SpringLayout.WEST, contentPanel);
			btnSearch.addActionListener( e -> searchAll() );
			contentPanel.add(btnSearch);
		}
		{
			btnClean = new JButton("Clean");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnClean, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, btnClean);
			sl_contentPanel.putConstraint(SpringLayout.EAST, btnClean, -102, SpringLayout.EAST, contentPanel);
			btnClean.addActionListener( e -> cleanFields() );
			contentPanel.add(btnClean);
		}
		{
			separator = new JSeparator();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, separator, 10, SpringLayout.SOUTH, btnSearch);
			sl_contentPanel.putConstraint(SpringLayout.WEST, separator, 20, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, separator, -170, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, separator, 400, SpringLayout.WEST, contentPanel);
			contentPanel.add(separator);
		}
		{
			spPerson = new JScrollPane();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, spPerson, 12, SpringLayout.SOUTH, separator);
			sl_contentPanel.putConstraint(SpringLayout.WEST, spPerson, 36, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, spPerson, -10, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, spPerson, -35, SpringLayout.EAST, contentPanel);
			contentPanel.add(spPerson);
			
			tPerson = new JTable();
			tPerson.setFillsViewportHeight(true);
			spPerson.setViewportView(tPerson);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, tPerson, 114, SpringLayout.SOUTH, separator);
			sl_contentPanel.putConstraint(SpringLayout.WEST, tPerson, 48, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, tPerson, -10, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, tPerson, 68, SpringLayout.WEST, contentPanel);
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

	private void cleanFields() {
		
		tPerson.setModel(new DefaultTableModel());
		
	}

	private void searchAll() {
		
		Optional<List<Person>> optListPerson = PersonController.getAllPerson();
		
		if (optListPerson.isPresent() ) {
			
			String[] aTitles = {"Person Id", "Name", "Surname"};
			Object [][] aData = IntStream.range(0, optListPerson.get().size())
										.mapToObj( i -> new Object []{
											optListPerson.get().get(i).getPersonId(),
											optListPerson.get().get(i).getName(),
											optListPerson.get().get(i).getSurname()	
										})
										.toArray(Object[][]::new);
			tPerson.setModel(new DefaultTableModel(aData,aTitles){
		        /**
				 * JTable treats the first columns as Object instead of Integer. Now, it should sort based on the integer values.
				 */
				private static final long serialVersionUID = 6529269790518969819L;

				@Override
		        public Class<?> getColumnClass(int columnIndex) {
		            if (columnIndex == 0) return Integer.class;
		            return super.getColumnClass(columnIndex);
		        }
		    });
			
			tPerson.setDefaultEditor(Object.class, null);
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tPerson.getModel());
			tPerson.setRowSorter(sorter);
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
		}
		
	}
	
	
}
