package ime.flixing.mvc.view;


import javax.swing.JDialog;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class FlixView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					try {
					FlixView frame = new FlixView();
					frame.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
					frame.setVisible(true);
				} catch (Exception e) {
					final Logger logger = Logger.getLogger(FlixView.class.getName());
					logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
				}
			
		
	}

	/**
	 * Create the frame.
	 */
	public FlixView() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout slContentPane = new SpringLayout();
		contentPane.setLayout(slContentPane);
		
		JLabel lblFlixTitle = new JLabel("FLIX");
		slContentPane.putConstraint(SpringLayout.WEST, lblFlixTitle, 168, SpringLayout.WEST, contentPane);
		lblFlixTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblFlixTitle);
		
		JButton btnGetAllFlix = new JButton("Get All");
		slContentPane.putConstraint(SpringLayout.SOUTH, lblFlixTitle, -13, SpringLayout.NORTH, btnGetAllFlix);
		btnGetAllFlix.addActionListener( e -> FlixController.initFlixGetAllView());
		contentPane.add(btnGetAllFlix);
		
		JButton btnGetFlixById = new JButton("Get By Id");
		slContentPane.putConstraint(SpringLayout.WEST, btnGetFlixById, 156, SpringLayout.WEST, contentPane);
		slContentPane.putConstraint(SpringLayout.WEST, btnGetAllFlix, 0, SpringLayout.WEST, btnGetFlixById);
		slContentPane.putConstraint(SpringLayout.SOUTH, btnGetAllFlix, -6, SpringLayout.NORTH, btnGetFlixById);
		btnGetFlixById.addActionListener( e -> FlixController.initFlixGetByIdView() );
		contentPane.add(btnGetFlixById);
		
		JButton btnSaveFlix = new JButton("Save");
		slContentPane.putConstraint(SpringLayout.WEST, btnSaveFlix, 156, SpringLayout.WEST, contentPane);
		slContentPane.putConstraint(SpringLayout.SOUTH, btnGetFlixById, -6, SpringLayout.NORTH, btnSaveFlix);
		btnSaveFlix.addActionListener( e -> FlixController.initFlixSaveView());
		contentPane.add(btnSaveFlix);
		
		JButton btnUpdateflix = new JButton("Update");
		slContentPane.putConstraint(SpringLayout.WEST, btnUpdateflix, 156, SpringLayout.WEST, contentPane);
		slContentPane.putConstraint(SpringLayout.SOUTH, btnSaveFlix, -6, SpringLayout.NORTH, btnUpdateflix);
		btnUpdateflix.addActionListener( e -> FlixController.initFlixUpdateView());
		contentPane.add(btnUpdateflix);
		
		JButton btnDeleteFlix = new JButton("Delete");
		slContentPane.putConstraint(SpringLayout.NORTH, btnDeleteFlix, 156, SpringLayout.NORTH, contentPane);
		slContentPane.putConstraint(SpringLayout.WEST, btnDeleteFlix, 156, SpringLayout.WEST, contentPane);
		slContentPane.putConstraint(SpringLayout.SOUTH, btnUpdateflix, -6, SpringLayout.NORTH, btnDeleteFlix);
		btnDeleteFlix.addActionListener( e -> FlixController.initFlixDeleteView());
		contentPane.add(btnDeleteFlix);
		
		JButton btnBack = new JButton("Back");
		slContentPane.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPane);
		slContentPane.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, contentPane);
		btnBack.addActionListener( e -> dispose());
		contentPane.add(btnBack);
	}
}
