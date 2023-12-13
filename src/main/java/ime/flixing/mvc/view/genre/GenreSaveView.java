package ime.flixing.mvc.view.genre;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Genre;
import ime.flixing.mvc.controller.GenreController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GenreSaveView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfGenreName;
	private JLabel lblGenreName;
	private JTextArea taDescription;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreSaveView dialog = new GenreSaveView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreSaveView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreSaveView() {
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
			lblGenreName = new JLabel("Genre Name");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreName, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblGenreName, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblGenreName);
		}
		
		tfGenreName = new JTextField();
		lblGenreName.setLabelFor(tfGenreName);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfGenreName, -3, SpringLayout.NORTH, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreName, 25, SpringLayout.EAST, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.EAST, tfGenreName, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, 48, SpringLayout.SOUTH, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 10, SpringLayout.WEST, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, 119, SpringLayout.SOUTH, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, 0, SpringLayout.EAST, tfGenreName);
		contentPanel.add(spDescription);
		
		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		spDescription.setViewportView(taDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setLabelFor(taDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, lblDescription, 181, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, lblDescription, -6, SpringLayout.NORTH, spDescription);
		contentPanel.add(lblDescription);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 14, SpringLayout.SOUTH, spDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 167, SpringLayout.WEST, contentPanel);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton("Save");
				btnSave.setActionCommand("OK");
				btnSave.addActionListener( e -> saveGenre() );
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}
	
	private void saveGenre() {
		
		String strGenreName = tfGenreName.getText();
		String strDescription = taDescription.getText();
		
		if( Checker.checkName(strGenreName) && Checker.checkDescription(strDescription) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Genre>optGenre = new GenreController().saveGenre(strGenreName, strDescription);
				
				if (optGenre.isPresent() ) {
					
					showResultInfo(optGenre);
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
				}	
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
		
	}

	private void cleanFields() {
		
		tfGenreName.setText("");
		taDescription.setText("");
		
	}

	private void showResultInfo(Optional<Genre>optGenre) {
		
		String msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
		
		if (optGenre.isPresent() && optGenre.get().getGenreId() != null ) {
			
			switch( String.valueOf(optGenre.get().getGenreId() ) ) {
			
			case "-2":
				msgInfo = DecoHelper.MSG_ERROR_CHECKER;
				break;
			case "-1":
				msgInfo = DecoHelper.MSG_DUPLICATED_NAME;
				break;
			case "0":
				msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
				break;
			default:
				msgInfo = DecoHelper.MSG_SUCCESSFULLY;
				break;
			}
		}
		
		JOptionPane.showMessageDialog(this, msgInfo);
	}
}
