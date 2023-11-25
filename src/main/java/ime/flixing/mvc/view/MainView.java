package ime.flixing.mvc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.MainController;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import java.awt.Font;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lbTitle = new JLabel("Welcome");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbTitle, 10, SpringLayout.NORTH, contentPane);
		contentPane.add(lbTitle);
		
		JButton btnFlix = new JButton("Flix");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFlix, 26, SpringLayout.SOUTH, lbTitle);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbTitle, 0, SpringLayout.WEST, btnFlix);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnFlix, 157, SpringLayout.WEST, contentPane);
		btnFlix.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				MainController.callFlixController();
				
			}
		});
		contentPane.add(btnFlix);
		
		JButton btnGenre = new JButton("Genre");
		btnGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.callGenreController();
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGenre, 6, SpringLayout.SOUTH, btnFlix);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnGenre, 0, SpringLayout.EAST, btnFlix);
		contentPane.add(btnGenre);
		
		JButton btnPerson = new JButton("Person");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPerson, 13, SpringLayout.SOUTH, btnGenre);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnPerson, 0, SpringLayout.EAST, btnFlix);
		contentPane.add(btnPerson);
		
		JButton btnPosition = new JButton("Position");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPosition, 6, SpringLayout.SOUTH, btnPerson);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnPosition, 0, SpringLayout.EAST, btnFlix);
		contentPane.add(btnPosition);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSalir, 6, SpringLayout.SOUTH, btnPosition);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSalir, 0, SpringLayout.EAST, btnFlix);
		contentPane.add(btnSalir);
	}
}
