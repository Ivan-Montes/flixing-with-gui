package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.FlixPersonPositionController;
import ime.flixing.tool.DecoHelper;

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
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixPersonPositionView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixPersonPositionView() {
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
			lblFlixPersonPosition = new JLabel("FlixPersonPosition");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblFlixPersonPosition, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblFlixPersonPosition, 145, SpringLayout.WEST, contentPanel);
			lblFlixPersonPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblFlixPersonPosition);
		}
		
		JButton btnEdit = new JButton("Edit Elements");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnEdit, 20, SpringLayout.SOUTH, lblFlixPersonPosition);
		slContentPanel.putConstraint(SpringLayout.WEST, btnEdit, 156, SpringLayout.WEST, contentPanel);
		btnEdit.addActionListener( e -> FlixPersonPositionController.initFlixPersonPositionEditView() );
		contentPanel.add(btnEdit);
		
		JButton btnSave = new JButton("Save");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSave, 24, SpringLayout.SOUTH, btnEdit);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSave, 180, SpringLayout.WEST, contentPanel);
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
