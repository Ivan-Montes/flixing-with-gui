package ime.flixing.mvc.view.flix_person_position;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FlixPersonPositionDetailsView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea taFlix;
	private JTextArea taPerson;
	private JTextArea taPosition;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixPersonPositionDetailsView dialog = new FlixPersonPositionDetailsView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixPersonPositionDetailsView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixPersonPositionDetailsView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		Font tahomaBold14 = new Font("Tahoma", Font.BOLD, 14);
		
		JLabel lblFlix = new JLabel("Flix");
		lblFlix.setFont(tahomaBold14);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblFlix, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblFlix, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblFlix);
		
		JLabel lblPerson = new JLabel("Person");
		lblPerson.setFont(tahomaBold14);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPerson, 118, SpringLayout.SOUTH, lblFlix);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPerson, 0, SpringLayout.WEST, lblFlix);
		contentPanel.add(lblPerson);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(tahomaBold14);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPosition, 106, SpringLayout.SOUTH, lblPerson);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPosition, 0, SpringLayout.WEST, lblFlix);
		contentPanel.add(lblPosition);
		
		JScrollPane spFlix = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlix, 20, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlix, 90, SpringLayout.EAST, lblFlix);
		slContentPanel.putConstraint(SpringLayout.EAST, spFlix, -55, SpringLayout.EAST, contentPanel);
		contentPanel.add(spFlix);
		
		taFlix = new JTextArea();
		taFlix.setWrapStyleWord(true);
		taFlix.setLineWrap(true);
		taFlix.setEditable(false);
		spFlix.setViewportView(taFlix);
		
		JScrollPane spPerson = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.SOUTH, spFlix, -19, SpringLayout.NORTH, spPerson);
		slContentPanel.putConstraint(SpringLayout.NORTH, spPerson, 150, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spPerson, -55, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spPerson, 64, SpringLayout.EAST, lblPerson);
		contentPanel.add(spPerson);
		
		taPerson = new JTextArea();
		taPerson.setWrapStyleWord(true);
		taPerson.setLineWrap(true);
		taPerson.setEditable(false);
		spPerson.setViewportView(taPerson);
		
		JScrollPane spPosition = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.WEST, spPosition, 57, SpringLayout.EAST, lblPosition);
		slContentPanel.putConstraint(SpringLayout.EAST, spPosition, -55, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spPerson, -17, SpringLayout.NORTH, spPosition);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spPosition, -15, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.NORTH, spPosition, 278, SpringLayout.NORTH, contentPanel);
		contentPanel.add(spPosition);
		
		taPosition = new JTextArea();
		taPosition.setWrapStyleWord(true);
		taPosition.setLineWrap(true);
		taPosition.setEditable(false);
		spPosition.setViewportView(taPosition);
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

	public void loadData(FlixPersonPosition flixPersonPosition) {
	
		if ( flixPersonPosition != null ) {
			
			taFlix.setText(flixPersonPosition.getFlix().toString());
			taPerson.setText(flixPersonPosition.getPerson().toString());
			taPosition.setText(flixPersonPosition.getPosition().toString());
			
		}else {
			
			taFlix.setText(DecoHelper.MSG_ERROR_NULL);
			taPerson.setText(DecoHelper.MSG_ERROR_NULL);
			taPosition.setText(DecoHelper.MSG_ERROR_NULL);
		}
		
	}
}
