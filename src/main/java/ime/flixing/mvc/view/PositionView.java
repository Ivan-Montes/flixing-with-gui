package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.PositionController;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class PositionView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PositionView dialog = new PositionView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblPosition = new JLabel("POSITION");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblPosition, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPosition, 167, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPosition);
		
		JButton btnGetAll = new JButton("Get All");
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetAll, 0, SpringLayout.WEST, lblPosition);
		btnGetAll.addActionListener( e -> PositionController.initPositionGetAllView() );
		contentPanel.add(btnGetAll);
		
		JButton btnGetById = new JButton("Get By Id");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetAll, -6, SpringLayout.NORTH, btnGetById);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetById, 0, SpringLayout.WEST, lblPosition);
		btnGetById.addActionListener( e -> PositionController.initPositionGetByIdView() );
		contentPanel.add(btnGetById);
		
		JButton btnSave = new JButton("Save");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetById, -6, SpringLayout.NORTH, btnSave);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, lblPosition);
		btnSave.addActionListener( e -> PositionController.initPositionSaveView() );
		contentPanel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnUpdate, -63, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnSave, -6, SpringLayout.NORTH, btnUpdate);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, lblPosition);
		btnUpdate.addActionListener( e -> PositionController.initPositionUpdateView() );
		contentPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnDelete, 6, SpringLayout.SOUTH, btnUpdate);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, lblPosition);
		btnDelete.addActionListener( e -> PositionController.initPositionDeleteView() );
		contentPanel.add(btnDelete);
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
