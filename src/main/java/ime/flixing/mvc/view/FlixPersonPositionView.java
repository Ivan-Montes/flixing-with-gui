package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.FlixPersonPositionController;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class FlixPersonPositionView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblFlixPersonPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixPersonPositionView dialog = new FlixPersonPositionView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixPersonPositionView() {
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
			lblFlixPersonPosition = new JLabel("FlixPersonPosition");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblFlixPersonPosition, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblFlixPersonPosition, 145, SpringLayout.WEST, contentPanel);
			lblFlixPersonPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblFlixPersonPosition);
		}
		
		JButton btnEdit = new JButton("Edit Elements");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnEdit, 20, SpringLayout.SOUTH, lblFlixPersonPosition);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnEdit, 156, SpringLayout.WEST, contentPanel);
		btnEdit.addActionListener( e -> FlixPersonPositionController.initFlixPersonPositionEditView() );
		contentPanel.add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSave, 24, SpringLayout.SOUTH, btnEdit);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSave, 180, SpringLayout.WEST, contentPanel);
		btnSave.addActionListener( e -> FlixPersonPositionController.initFlixPersonPositionSaveView() );
		contentPanel.add(btnSave);
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
