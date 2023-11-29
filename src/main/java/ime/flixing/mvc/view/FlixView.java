package ime.flixing.mvc.view;


import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.FlixController;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FlixView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					try {
					FlixView frame = new FlixView();
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
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
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblFlixTitle = new JLabel("FLIX");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFlixTitle, 166, SpringLayout.WEST, contentPane);
		lblFlixTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblFlixTitle);
		
		JButton btnGetAllFlix = new JButton("getAllFlix");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGetAllFlix, 33, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblFlixTitle, -6, SpringLayout.NORTH, btnGetAllFlix);
		btnGetAllFlix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FlixController.initFlixGetAllView();
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnGetAllFlix, 142, SpringLayout.WEST, contentPane);
		contentPane.add(btnGetAllFlix);
		
		JButton btnGetFlixById = new JButton("getFlixById");
		btnGetFlixById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FlixController.initFlixGetByIdView();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGetFlixById, 6, SpringLayout.SOUTH, btnGetAllFlix);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnGetFlixById, 0, SpringLayout.WEST, btnGetAllFlix);
		contentPane.add(btnGetFlixById);
		
		JButton btnSaveFlix = new JButton("saveFlix");
		btnSaveFlix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FlixController.initFlixSaveView();
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSaveFlix, 6, SpringLayout.SOUTH, btnGetFlixById);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSaveFlix, 0, SpringLayout.WEST, btnGetAllFlix);
		contentPane.add(btnSaveFlix);
		
		JButton btnUpdateflix = new JButton("updateFlix");
		btnUpdateflix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FlixController.initFlixUpdateView();
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnUpdateflix, 6, SpringLayout.SOUTH, btnSaveFlix);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnUpdateflix, 0, SpringLayout.WEST, btnGetAllFlix);
		contentPane.add(btnUpdateflix);
		
		JButton btnDeleteFlix = new JButton("deleteFlix");
		btnDeleteFlix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FlixController.initFlixDeleteView();
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnDeleteFlix, 6, SpringLayout.SOUTH, btnUpdateflix);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDeleteFlix, 0, SpringLayout.WEST, btnGetAllFlix);
		contentPane.add(btnDeleteFlix);
		
		JButton btnBack = new JButton("Back");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, contentPane);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		contentPane.add(btnBack);
	}
}
