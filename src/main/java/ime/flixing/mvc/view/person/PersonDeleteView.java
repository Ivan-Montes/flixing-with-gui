package ime.flixing.mvc.view.person;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PersonDeleteView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPersonCod;
	private JSpinner spPersonCod;
	private JButton btnSearch;
	private JTextField tfPersonCod;
	private JSeparator separator;
	private JTextField tfPersonName;
	private JTextField tfPersonSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonDeleteView dialog = new PersonDeleteView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PersonDeleteView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonDeleteView() {
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
			lblPersonCod = new JLabel("Person Code");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonCod, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblPersonCod, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblPersonCod);
		}
		{
			spPersonCod = new JSpinner();
			spPersonCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
			slContentPanel.putConstraint(SpringLayout.NORTH, spPersonCod, 0, SpringLayout.NORTH, lblPersonCod);
			slContentPanel.putConstraint(SpringLayout.WEST, spPersonCod, 29, SpringLayout.EAST, lblPersonCod);
			contentPanel.add(spPersonCod);
		}
		{
			btnSearch = new JButton("Search");
			slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblPersonCod);
			slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 50, SpringLayout.EAST, spPersonCod);
			btnSearch.addActionListener( e -> searchAndShow() );
			contentPanel.add(btnSearch);
		}
		{
			JButton btnClean = new JButton("Clean");
			slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblPersonCod);
			slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 34, SpringLayout.EAST, btnSearch);
			btnClean.addActionListener( e -> cleanFields() );
			contentPanel.add(btnClean);
		}
		{
			separator = new JSeparator();
			slContentPanel.putConstraint(SpringLayout.NORTH, separator, 20, SpringLayout.SOUTH, btnSearch);
			slContentPanel.putConstraint(SpringLayout.WEST, separator, 133, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 34, SpringLayout.SOUTH, btnSearch);
			slContentPanel.putConstraint(SpringLayout.EAST, separator, 287, SpringLayout.WEST, contentPanel);
			contentPanel.add(separator);
		}
		{
			tfPersonCod = new JTextField();
			tfPersonCod.setEditable(false);
			slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonCod, 28, SpringLayout.SOUTH, separator);
			slContentPanel.putConstraint(SpringLayout.EAST, tfPersonCod, 0, SpringLayout.EAST, btnSearch);
			contentPanel.add(tfPersonCod);
			tfPersonCod.setColumns(10);
		}
		{
			tfPersonName = new JTextField();
			slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonName, 25, SpringLayout.SOUTH, tfPersonCod);
			slContentPanel.putConstraint(SpringLayout.WEST, tfPersonName, 39, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.EAST, tfPersonName, 259, SpringLayout.WEST, contentPanel);
			tfPersonName.setEditable(false);
			contentPanel.add(tfPersonName);
			tfPersonName.setColumns(10);
		}
		{
			tfPersonSurname = new JTextField();
			slContentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 0, SpringLayout.WEST, tfPersonCod);
			slContentPanel.putConstraint(SpringLayout.SOUTH, tfPersonSurname, -10, SpringLayout.SOUTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.EAST, tfPersonSurname, -24, SpringLayout.EAST, contentPanel);
			tfPersonSurname.setEditable(false);
			contentPanel.add(tfPersonSurname);
			tfPersonSurname.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.setActionCommand("OK");
				btnDelete.addActionListener( e -> deletePerson() );
				buttonPane.add(btnDelete);
				getRootPane().setDefaultButton(btnDelete);
			}
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
		
		if ( Checker.checkDigits(strPersonCod) ) {
			
			Optional<Person>optPerson = new PersonController().getPersonById(strPersonCod);
			
			if ( optPerson.isPresent() ) {
				
				tfPersonCod.setText( optPerson.get().getPersonId().toString() );
				tfPersonName.setText( optPerson.get().getName() );
				tfPersonSurname.setText( optPerson.get().getSurname() );
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
		
	}

	private void cleanFields() {
		
		spPersonCod.setValue(0);
		tfPersonCod.setText("");
		tfPersonName.setText("");
		tfPersonSurname.setText("");
		
	}

	private void deletePerson() {
		
		String strPersonCod = tfPersonCod.getText();
		
		if ( Checker.checkDigits(strPersonCod) ) {
			
			 if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){

					showResultInfo(new PersonController().deletePerson(strPersonCod));
					cleanFields();
			 }
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
		
	}
	
	private void showResultInfo(int returnValue) {
		
		String msgResult = "";
		
		switch(returnValue) {
		
		case 0:
			msgResult = DecoHelper.MSG_SUCCESSFULLY;
			break;
		
		case -1:
			msgResult = DecoHelper.MSG_ERROR_PROCESS;
			break;
			
		case -2:
			msgResult = DecoHelper.MSG_ERROR_DELETE_ASSOCIATED_ITEMS;
			break;
			
		default:
			msgResult = DecoHelper.MSG_ERROR_UNEXPECTED;
		}
		
		JOptionPane.showMessageDialog(this, msgResult);
		
	}

}
