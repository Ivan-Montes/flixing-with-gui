package ime.flixing.mvc.view.person;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Person;
import ime.flixing.mvc.controller.PersonController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

public class PersonGetByIdView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPersonName;
	private JTextField tfPersonSurname;
	private JTextField tfPersonCod;
	private JSpinner spPersonCod;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonGetByIdView dialog = new PersonGetByIdView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonGetByIdView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblPersonCod = new JLabel("Person Code");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPersonCod, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPersonCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPersonCod);
		
		tfPersonName = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfPersonName, 61, SpringLayout.WEST, contentPanel);
		tfPersonName.setEditable(false);
		contentPanel.add(tfPersonName);
		tfPersonName.setColumns(10);
		
		spPersonCod = new JSpinner();
		sl_contentPanel.putConstraint(SpringLayout.WEST, spPersonCod, 23, SpringLayout.EAST, lblPersonCod);
		spPersonCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		contentPanel.add(spPersonCod);
		
		JButton btnSearch = new JButton("Search");
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 39, SpringLayout.EAST, spPersonCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spPersonCod, 0, SpringLayout.SOUTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 10, SpringLayout.NORTH, contentPanel);
		btnSearch.addActionListener( e -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblPersonCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnClean, 42, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JSeparator separator = new JSeparator();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, separator, 19, SpringLayout.SOUTH, spPersonCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, separator, -158, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, separator, 387, SpringLayout.WEST, contentPanel);
		contentPanel.add(separator);
		
		tfPersonSurname = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfPersonSurname, 118, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 88, SpringLayout.EAST, tfPersonName);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfPersonName, 0, SpringLayout.NORTH, tfPersonSurname);
		tfPersonSurname.setEditable(false);
		contentPanel.add(tfPersonSurname);
		tfPersonSurname.setColumns(10);
		
		tfPersonCod = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfPersonCod, 145, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfPersonCod, -18, SpringLayout.NORTH, tfPersonName);
		tfPersonCod.setEditable(false);
		contentPanel.add(tfPersonCod);
		tfPersonCod.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, separator_1, 46, SpringLayout.SOUTH, tfPersonName);
		sl_contentPanel.putConstraint(SpringLayout.WEST, separator_1, 15, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, separator_1, -26, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, separator_1, 5, SpringLayout.EAST, separator);
		contentPanel.add(separator_1);
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
		
		String strPersonCod = spPersonCod.getValue().toString();
		
		if (Checker.checkDigits(strPersonCod)) {
			
			Optional<Person>optPerson = PersonController.getPersonById(strPersonCod);
			
			if ( optPerson.isPresent() ) {
				
				tfPersonName.setText( optPerson.get().getName() );
				tfPersonSurname.setText( optPerson.get().getSurname() );
				tfPersonCod.setText( optPerson.get().getPersonId().toString() );
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
		
	}
	
	private void cleanFields() {
		
		spPersonCod.setValue(0);
		tfPersonName.setText("");
		tfPersonSurname.setText("");
		tfPersonCod.setText("");
		
	}
}
