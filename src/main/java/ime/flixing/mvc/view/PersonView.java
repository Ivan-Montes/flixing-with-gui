package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.PersonController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PersonView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPerson;
	private JButton btnGetAllPerson;
	private JButton btnGetPersonById;
	private JButton btnSavePerson;
	private JButton btnUpdatePerson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PersonView dialog = new PersonView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PersonView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonView() {
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		{
			lblPerson = new JLabel("PERSON");
			lblPerson.setFont(new Font("Tahoma", Font.BOLD, 14));
			slContentPanel.putConstraint(SpringLayout.NORTH, lblPerson, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblPerson, 171, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblPerson);
		}
		{
			btnGetAllPerson = new JButton("Get All");
			btnGetAllPerson.addActionListener( e -> PersonController.initPersonGetAllView());
			contentPanel.add(btnGetAllPerson);
		}
		{
			btnGetPersonById = new JButton("Get By Id");
			slContentPanel.putConstraint(SpringLayout.WEST, btnGetPersonById, 161, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, btnGetAllPerson, 0, SpringLayout.WEST, btnGetPersonById);
			slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetAllPerson, -6, SpringLayout.NORTH, btnGetPersonById);
			btnGetPersonById.addActionListener( e -> PersonController.initPersonGetByIdView() );
			contentPanel.add(btnGetPersonById);
		}
		{
			btnSavePerson = new JButton("Save");
			slContentPanel.putConstraint(SpringLayout.WEST, btnSavePerson, 161, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetPersonById, -6, SpringLayout.NORTH, btnSavePerson);
			btnSavePerson.addActionListener( e -> PersonController.initPersonSaveView() );
			contentPanel.add(btnSavePerson);
		}
		{
			btnUpdatePerson = new JButton("Update");
			slContentPanel.putConstraint(SpringLayout.WEST, btnUpdatePerson, 161, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, btnSavePerson, -6, SpringLayout.NORTH, btnUpdatePerson);
			btnUpdatePerson.addActionListener( e -> PersonController.initPersonUpdateView() );
			contentPanel.add(btnUpdatePerson);
		}
		{
			JButton btnDeletePerson = new JButton("Delete");
			slContentPanel.putConstraint(SpringLayout.NORTH, btnDeletePerson, 162, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, btnDeletePerson, 161, SpringLayout.WEST, contentPanel);
			slContentPanel.putConstraint(SpringLayout.SOUTH, btnUpdatePerson, -6, SpringLayout.NORTH, btnDeletePerson);
			btnDeletePerson.addActionListener( e -> PersonController.initPersonDeleteView() );
			contentPanel.add(btnDeletePerson);
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

}
