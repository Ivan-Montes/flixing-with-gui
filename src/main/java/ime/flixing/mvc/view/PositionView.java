package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.DecoHelper;

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
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PositionView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblPosition = new JLabel("POSITION");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPosition, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPosition, 167, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPosition);
		
		JButton btnGetAll = new JButton("Get All");
		slContentPanel.putConstraint(SpringLayout.WEST, btnGetAll, 0, SpringLayout.WEST, lblPosition);
		btnGetAll.addActionListener( e -> PositionController.initPositionGetAllView() );
		contentPanel.add(btnGetAll);
		
		JButton btnGetById = new JButton("Get By Id");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetAll, -6, SpringLayout.NORTH, btnGetById);
		slContentPanel.putConstraint(SpringLayout.WEST, btnGetById, 0, SpringLayout.WEST, lblPosition);
		btnGetById.addActionListener( e -> PositionController.initPositionGetByIdView() );
		contentPanel.add(btnGetById);
		
		JButton btnSave = new JButton("Save");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetById, -6, SpringLayout.NORTH, btnSave);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, lblPosition);
		btnSave.addActionListener( e -> PositionController.initPositionSaveView() );
		contentPanel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnUpdate, -63, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnSave, -6, SpringLayout.NORTH, btnUpdate);
		slContentPanel.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, lblPosition);
		btnUpdate.addActionListener( e -> PositionController.initPositionUpdateView() );
		contentPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnDelete, 6, SpringLayout.SOUTH, btnUpdate);
		slContentPanel.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, lblPosition);
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
