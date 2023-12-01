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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonSaveView dialog = new PersonSaveView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonSaveView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblPersonName = new JLabel("Person Name");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPersonName, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblPersonName, 20, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblPersonName);
		}
		{
			lblPersonSurname = new JLabel("Person Surname");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPersonSurname, 25, SpringLayout.SOUTH, lblPersonName);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblPersonSurname, 0, SpringLayout.WEST, lblPersonName);
			contentPanel.add(lblPersonSurname);
		}
		{
			tfPersonName = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, tfPersonName, -3, SpringLayout.NORTH, lblPersonName);
			sl_contentPanel.putConstraint(SpringLayout.WEST, tfPersonName, 27, SpringLayout.EAST, lblPersonName);
			sl_contentPanel.putConstraint(SpringLayout.EAST, tfPersonName, -32, SpringLayout.EAST, contentPanel);
			contentPanel.add(tfPersonName);
			tfPersonName.setColumns(10);
		}
		{
			tfPersonSurname = new JTextField();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, tfPersonSurname, -3, SpringLayout.NORTH, lblPersonSurname);
			sl_contentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 12, SpringLayout.EAST, lblPersonSurname);
			sl_contentPanel.putConstraint(SpringLayout.EAST, tfPersonSurname, 0, SpringLayout.EAST, tfPersonName);
			contentPanel.add(tfPersonSurname);
			tfPersonSurname.setColumns(10);
		}
		{
			btnClean = new JButton("Clean");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnClean, 170, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnClean, -10, SpringLayout.SOUTH, contentPanel);
			btnClean.addActionListener( e -> cleanFields() );
			contentPanel.add(btnClean);
		}
		{
			separator = new JSeparator();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, separator, 17, SpringLayout.SOUTH, tfPersonSurname);
			sl_contentPanel.putConstraint(SpringLayout.WEST, separator, 20, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, separator, 31, SpringLayout.SOUTH, tfPersonSurname);
			sl_contentPanel.putConstraint(SpringLayout.EAST, separator, 392, SpringLayout.WEST, contentPanel);
			contentPanel.add(separator);
		}
		
		JTextPane tpResult = new JTextPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tpResult, 24, SpringLayout.SOUTH, separator);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tpResult, 0, SpringLayout.WEST, lblPersonName);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tpResult, -19, SpringLayout.NORTH, btnClean);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tpResult, -32, SpringLayout.EAST, contentPanel);
		contentPanel.add(tpResult);
		
		JLabel lblResult = new JLabel("Result");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblResult, 6, SpringLayout.SOUTH, separator);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblResult, 184, SpringLayout.WEST, contentPanel);
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
	
	private void printResult(Optional<Person>optPerson) {
		
		
		if (optPerson.isPresent()) {
			
			
		}else {
			
			
		}
		
	}
}
