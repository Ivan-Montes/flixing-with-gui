package ime.flixing.mvc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.MainController;

import javax.swing.JLabel;
import javax.swing.JButton;
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
		btnFlix.addActionListener( e -> MainController.callFlixController() );
		contentPane.add(btnFlix);
		
		JButton btnGenre = new JButton("Genre");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnGenre, 6, SpringLayout.SOUTH, btnFlix);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnGenre, 0, SpringLayout.WEST, lbTitle);
		btnGenre.addActionListener( e -> MainController.callGenreController() );
		contentPane.add(btnGenre);
		
		JButton btnPerson = new JButton("Person");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPerson, 6, SpringLayout.SOUTH, btnGenre);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnPerson, 0, SpringLayout.WEST, lbTitle);
		btnPerson.addActionListener( e -> MainController.callPersonController() );
		contentPane.add(btnPerson);
		
		JButton btnPosition = new JButton("Position");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnPosition, 6, SpringLayout.SOUTH, btnPerson);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnPosition, 0, SpringLayout.WEST, lbTitle);
		btnPosition.addActionListener( e -> MainController.callPositionController() );
		contentPane.add(btnPosition);
		
		JButton btnExit = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnExit, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnExit, -10, SpringLayout.EAST, contentPane);
		btnExit.addActionListener( e -> System.exit(NORMAL) );
		contentPane.add(btnExit);
		
		JButton btnFlixPersonPosition = new JButton("FlixPersonPosition");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFlixPersonPosition, 6, SpringLayout.SOUTH, btnPosition);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnFlixPersonPosition, 0, SpringLayout.WEST, lbTitle);
		btnFlixPersonPosition.addActionListener( e -> MainController.callFlixPersonPositionController() );
		contentPane.add(btnFlixPersonPosition);
	}
}
