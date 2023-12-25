package ime.flixing.mvc.view;

import java.awt.EventQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.MainController;
import ime.flixing.tool.DecoHelper;

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
		EventQueue.invokeLater( () -> {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					final Logger logger = Logger.getLogger(MainView.class.getName());
					logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
				}
			
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout slContentPane = new SpringLayout();
		contentPane.setLayout(slContentPane);
		
		JLabel lbTitle = new JLabel("Welcome");
		slContentPane.putConstraint(SpringLayout.WEST, lbTitle, 157, SpringLayout.WEST, contentPane);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		slContentPane.putConstraint(SpringLayout.NORTH, lbTitle, 10, SpringLayout.NORTH, contentPane);
		contentPane.add(lbTitle);
		
		JButton btnFlix = new JButton("Flix");
		slContentPane.putConstraint(SpringLayout.NORTH, btnFlix, 13, SpringLayout.SOUTH, lbTitle);
		slContentPane.putConstraint(SpringLayout.WEST, btnFlix, 0, SpringLayout.WEST, lbTitle);
		btnFlix.addActionListener( e -> MainController.callFlixController() );
		contentPane.add(btnFlix);
		
		JButton btnGenre = new JButton("Genre");
		slContentPane.putConstraint(SpringLayout.NORTH, btnGenre, 6, SpringLayout.SOUTH, btnFlix);
		slContentPane.putConstraint(SpringLayout.WEST, btnGenre, 0, SpringLayout.WEST, lbTitle);
		btnGenre.addActionListener( e -> MainController.callGenreController() );
		contentPane.add(btnGenre);
		
		JButton btnPerson = new JButton("Person");
		slContentPane.putConstraint(SpringLayout.NORTH, btnPerson, 6, SpringLayout.SOUTH, btnGenre);
		slContentPane.putConstraint(SpringLayout.WEST, btnPerson, 0, SpringLayout.WEST, lbTitle);
		btnPerson.addActionListener( e -> MainController.callPersonController() );
		contentPane.add(btnPerson);
		
		JButton btnPosition = new JButton("Position");
		slContentPane.putConstraint(SpringLayout.NORTH, btnPosition, 6, SpringLayout.SOUTH, btnPerson);
		slContentPane.putConstraint(SpringLayout.WEST, btnPosition, 0, SpringLayout.WEST, lbTitle);
		btnPosition.addActionListener( e -> MainController.callPositionController() );
		contentPane.add(btnPosition);
		
		JButton btnExit = new JButton("Exit");
		slContentPane.putConstraint(SpringLayout.SOUTH, btnExit, -10, SpringLayout.SOUTH, contentPane);
		slContentPane.putConstraint(SpringLayout.EAST, btnExit, -10, SpringLayout.EAST, contentPane);
		btnExit.addActionListener( e -> System.exit(NORMAL) );
		contentPane.add(btnExit);
		
		JButton btnFlixPersonPosition = new JButton("FlixPersonPosition");
		slContentPane.putConstraint(SpringLayout.NORTH, btnFlixPersonPosition, 6, SpringLayout.SOUTH, btnPosition);
		slContentPane.putConstraint(SpringLayout.WEST, btnFlixPersonPosition, 0, SpringLayout.WEST, lbTitle);
		btnFlixPersonPosition.addActionListener( e -> MainController.callFlixPersonPositionController() );
		contentPane.add(btnFlixPersonPosition);
	}
}
