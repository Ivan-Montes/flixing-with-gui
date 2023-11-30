package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.PersonController;

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
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PersonView() {
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
			lblPerson = new JLabel("PERSON");
			lblPerson.setFont(new Font("Tahoma", Font.BOLD, 14));
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPerson, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblPerson, 171, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblPerson);
		}
		{
			btnGetAllPerson = new JButton("Get All");
			btnGetAllPerson.addActionListener( e -> PersonController.initPersonGetAllView());
			contentPanel.add(btnGetAllPerson);
		}
		{
			btnGetPersonById = new JButton("Get By Id");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetPersonById, 161, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetAllPerson, 0, SpringLayout.WEST, btnGetPersonById);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetAllPerson, -6, SpringLayout.NORTH, btnGetPersonById);
			btnGetPersonById.addActionListener( e -> PersonController.initPersonGetByIdView() );
			contentPanel.add(btnGetPersonById);
		}
		{
			btnSavePerson = new JButton("Save");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnSavePerson, 161, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetPersonById, -6, SpringLayout.NORTH, btnSavePerson);
			btnSavePerson.addActionListener( e -> PersonController.initPersonSaveView() );
			contentPanel.add(btnSavePerson);
		}
		{
			btnUpdatePerson = new JButton("Update");
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnUpdatePerson, 161, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnSavePerson, -6, SpringLayout.NORTH, btnUpdatePerson);
			btnUpdatePerson.addActionListener( e -> PersonController.initPersonUpdateView() );
			contentPanel.add(btnUpdatePerson);
		}
		{
			JButton btnDeletePerson = new JButton("Delete");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, btnDeletePerson, 162, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, btnDeletePerson, 161, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnUpdatePerson, -6, SpringLayout.NORTH, btnDeletePerson);
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
