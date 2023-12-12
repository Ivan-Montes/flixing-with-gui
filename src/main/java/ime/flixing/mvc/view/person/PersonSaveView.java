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
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class PersonSaveView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPersonName;
	private JTextField tfPersonName;
	private JTextField tfPersonSurname;
	private JLabel lblPersonSurname;
	private JButton btnClean;
	private JSeparator separator;
	private JTextPane tpResult;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonSaveView dialog = new PersonSaveView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PersonSaveView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonSaveView() {
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
			lblPersonName = new JLabel("Person Name");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonName, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblPersonName, 20, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblPersonName);
		}
		{
			lblPersonSurname = new JLabel("Person Surname");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonSurname, 25, SpringLayout.SOUTH, lblPersonName);
			slContentPanel.putConstraint(SpringLayout.WEST, lblPersonSurname, 0, SpringLayout.WEST, lblPersonName);
			contentPanel.add(lblPersonSurname);
		}
		{
			tfPersonName = new JTextField();
			slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonName, -3, SpringLayout.NORTH, lblPersonName);
			slContentPanel.putConstraint(SpringLayout.WEST, tfPersonName, 27, SpringLayout.EAST, lblPersonName);
			slContentPanel.putConstraint(SpringLayout.EAST, tfPersonName, -32, SpringLayout.EAST, contentPanel);
			contentPanel.add(tfPersonName);
			tfPersonName.setColumns(10);
		}
		{
			tfPersonSurname = new JTextField();
			slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonSurname, -3, SpringLayout.NORTH, lblPersonSurname);
			slContentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 12, SpringLayout.EAST, lblPersonSurname);
			slContentPanel.putConstraint(SpringLayout.EAST, tfPersonSurname, 0, SpringLayout.EAST, tfPersonName);
			contentPanel.add(tfPersonSurname);
			tfPersonSurname.setColumns(10);
		}
		{
			btnClean = new JButton("Clean");
			slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 170, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, btnClean, -10, SpringLayout.SOUTH, contentPanel);
			btnClean.addActionListener( e -> cleanAllFields() );
			contentPanel.add(btnClean);
		}
		{
			separator = new JSeparator();
			slContentPanel.putConstraint(SpringLayout.NORTH, separator, 17, SpringLayout.SOUTH, tfPersonSurname);
			slContentPanel.putConstraint(SpringLayout.WEST, separator, 20, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 31, SpringLayout.SOUTH, tfPersonSurname);
			slContentPanel.putConstraint(SpringLayout.EAST, separator, 392, SpringLayout.WEST, contentPanel);
			contentPanel.add(separator);
		}
		
		tpResult = new JTextPane();
		tpResult.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tpResult, 24, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.WEST, tpResult, 0, SpringLayout.WEST, lblPersonName);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tpResult, -19, SpringLayout.NORTH, btnClean);
		slContentPanel.putConstraint(SpringLayout.EAST, tpResult, -32, SpringLayout.EAST, contentPanel);
		contentPanel.add(tpResult);
		
		JLabel lblResult = new JLabel("Result");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblResult, 6, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.WEST, lblResult, 184, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblResult);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setActionCommand("OK");
				btnSave.addActionListener( e -> savePerson() );
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}
	
	private void savePerson() {
		
		String strPersonName = tfPersonName.getText();
		String strPersonSurname = tfPersonSurname.getText();
		
		if ( Checker.checkName(strPersonName) 
				&& Checker.checkSurname(strPersonSurname) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Person>optPerson = PersonController.savePerson(strPersonName, strPersonSurname);
				
				if (optPerson.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_SUCCESSFULLY);
					printResult(optPerson);
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
				}	
			}			
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_CHECKER);		}
		
	}
	
	private void cleanFields() {
		
		tfPersonName.setText("");
		tfPersonSurname.setText("");
		
	}
	

	private void cleanAllFields() {
		
		cleanFields();
		tpResult.setText("");
	}
	
	private void printResult(Optional<Person>optPerson) {
		
		
		if (optPerson.isPresent()) {
			
			tpResult.setText(" = Person Saved =\n");
			tpResult.setText( tpResult.getText() + optPerson.get().getPersonId() + " " + optPerson.get().getName() + " " + optPerson.get().getSurname());
			
		}else {
			
			tpResult.setText(" = Empty =\n");
		}
		
	}
}
