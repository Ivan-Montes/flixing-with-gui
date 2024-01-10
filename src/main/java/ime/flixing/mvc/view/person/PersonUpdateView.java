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
import javax.swing.text.AbstractDocument;

import ime.flixing.entity.Person;
import ime.flixing.mvc.controller.PersonController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.CheckerPattern;
import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.DocumentFilterFactory;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PersonUpdateView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPersonCod;
	private JTextField tfPersonName;
	private JTextField tfPersonSurname;
	private JSpinner spPersonCod;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonUpdateView dialog = new PersonUpdateView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PersonUpdateView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonUpdateView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblPersonCod = new JLabel("Person Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPersonCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPersonCod);
		
		spPersonCod = new JSpinner();
		spPersonCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spPersonCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spPersonCod, 18, SpringLayout.EAST, lblPersonCod);
		contentPanel.add(spPersonCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblPersonCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 45, SpringLayout.EAST, spPersonCod);
		btnSearch.addActionListener( e -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblPersonCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 48, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 10, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 27, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 24, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, 389, SpringLayout.WEST, contentPanel);
		contentPanel.add(separator);
		
		JLabel lblPersonCodFound = new JLabel("Person Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonCodFound, 6, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.EAST, lblPersonCodFound, 0, SpringLayout.EAST, lblPersonCod);
		contentPanel.add(lblPersonCodFound);
		
		JLabel lblPersonName = new JLabel("Person Name");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonName, 21, SpringLayout.SOUTH, lblPersonCodFound);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPersonName, 0, SpringLayout.WEST, lblPersonCod);
		contentPanel.add(lblPersonName);
		
		JLabel lblPersonSurname = new JLabel("Person Surname");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPersonSurname, 21, SpringLayout.SOUTH, lblPersonName);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPersonSurname, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPersonSurname);
		
		tfPersonCod = new JTextField();
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonCod, 6, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPersonCod, 57, SpringLayout.EAST, lblPersonCodFound);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPersonCod, 318, SpringLayout.EAST, lblPersonCodFound);
		tfPersonCod.setEditable(false);
		contentPanel.add(tfPersonCod);
		tfPersonCod.setColumns(10);
		
		tfPersonName = new JTextField();
		((AbstractDocument)tfPersonName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.NAME_FULL));
		slContentPanel.putConstraint(SpringLayout.EAST, tfPersonName, 316, SpringLayout.EAST, lblPersonName);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPersonName, 55, SpringLayout.EAST, lblPersonName);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonName, 0, SpringLayout.NORTH, lblPersonName);
		contentPanel.add(tfPersonName);
		tfPersonName.setColumns(10);
		
		tfPersonSurname = new JTextField();
		((AbstractDocument)tfPersonSurname.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.SURNAME_FULL));
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonSurname, 0, SpringLayout.NORTH, lblPersonSurname);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 40, SpringLayout.EAST, lblPersonSurname);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPersonSurname, 0, SpringLayout.EAST, separator);
		contentPanel.add(tfPersonSurname);
		tfPersonSurname.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.setActionCommand("OK");
				btnUpdate.addActionListener( e -> updatePerson() );
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}

	private void cleanFields() {
		
		spPersonCod.setValue(0);
		tfPersonCod.setText("");
		tfPersonName.setText("");
		tfPersonSurname.setText("");
		
	}

	private void searchAndShow() {
		
		String strPersonCod = spPersonCod.getValue().toString();
		
		if ( Checker.checkDigits(strPersonCod)) {
			
			Optional<Person>optPerson = new PersonController().getPersonById(strPersonCod);
			
			if ( optPerson.isPresent() ) {
				
				tfPersonCod.setText( optPerson.get().getPersonId().toString() );
				tfPersonName.setText( optPerson.get().getName() );
				tfPersonSurname.setText (optPerson.get().getSurname() );
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}		
		
	}

	private void updatePerson() {
		
		String strPersonCod = tfPersonCod.getText();
		String strPersonName = tfPersonName.getText();
		String strPersonSurname = tfPersonSurname.getText();
		
		if ( Checker.checkDigits(strPersonCod) &&
				Checker.checkName(strPersonName)
				&& Checker.checkSurname(strPersonSurname) ){
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Person>optPerson = new PersonController().updatePerson(strPersonCod, strPersonName, strPersonSurname);
				
				if ( optPerson.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_SUCCESSFULLY);	
					cleanFields();
					
				}else {
					
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);		
				}
				
			}
			
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_DATA);
		}		
		
	}
}
