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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GenreDeleteView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblGenreCod;
	private JSpinner spGenreCod;
	private JTextField tfGenreCod;
	private JTextField tfGenreName;
	private JTextArea taGenreDescription;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreDeleteView dialog = new GenreDeleteView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreDeleteView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreDeleteView() {
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
			lblGenreCod = new JLabel("Genre Code");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblGenreCod, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblGenreCod);
		}
		
		spGenreCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 11, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 18, SpringLayout.EAST, lblGenreCod);
		lblGenreCod.setLabelFor(spGenreCod);
		contentPanel.add(spGenreCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, -1, SpringLayout.NORTH, spGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 46, SpringLayout.EAST, spGenreCod);
		btnSearch.addActionListener( e -> searchAndShow() );		
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 59, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 13, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 26, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 27, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, 393, SpringLayout.WEST, contentPanel);
		contentPanel.add(separator);
		
		JLabel lblGenre = new JLabel("Genre");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenre, 6, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenre, 0, SpringLayout.WEST, btnSearch);
		contentPanel.add(lblGenre);
		
		tfGenreCod = new JTextField();
		tfGenreCod.setEditable(false);
		contentPanel.add(tfGenreCod);
		tfGenreCod.setColumns(10);
		
		tfGenreName = new JTextField();
		tfGenreName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreCod, 0, SpringLayout.WEST, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfGenreCod, -12, SpringLayout.NORTH, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreName, 34, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, tfGenreName, 269, SpringLayout.EAST, spGenreCod);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
		
		JScrollPane spGenreDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreDescription, 34, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spGenreDescription, -40, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfGenreName, -12, SpringLayout.NORTH, spGenreDescription);
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreDescription, 163, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spGenreDescription, -12, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(spGenreDescription);
		
		taGenreDescription = new JTextArea();
		taGenreDescription.setWrapStyleWord(true);
		taGenreDescription.setLineWrap(true);
		taGenreDescription.setEditable(false);
		spGenreDescription.setViewportView(taGenreDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, taGenreDescription, 0, SpringLayout.WEST, btnSearch);
		slContentPanel.putConstraint(SpringLayout.SOUTH, taGenreDescription, -10, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, taGenreDescription, 376, SpringLayout.WEST, contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.setActionCommand("OK");
				btnDelete.addActionListener( e -> deleteGenre() );
				buttonPane.add(btnDelete);
				getRootPane().setDefaultButton(btnDelete);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}

	private void deleteGenre() {
		
		String strGenreCod = tfGenreCod.getText();
		
		if ( Checker.checkDigits(strGenreCod) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				 
				 showInfoAboutDelete( new GenreController().deleteGenre(strGenreCod) );
				 cleanFields();
			 }
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
	}

	private void showInfoAboutDelete(int returnValue) {
		
		String msgInfo = "";
		
		switch(returnValue) {
		
		case 0:
			msgInfo = DecoHelper.MSG_SUCCESSFULLY;
			break;
		case -1:
			msgInfo = DecoHelper.MSG_ERROR_PROCESS;
			break;
		case -2:
			msgInfo = DecoHelper.MSG_ERROR_DELETE_ASSOCIATED_ITEMS;
			break;
		default:
			msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
		
		}
		
		JOptionPane.showMessageDialog(this, msgInfo);
		
	}

	private void cleanFields() {
		
		spGenreCod.setValue(0);
		tfGenreCod.setText("");
		tfGenreName.setText("");
		taGenreDescription.setText("");
		
	}

	private void searchAndShow() {
		
		String strGenreCod = spGenreCod.getValue().toString();
		
		if ( Checker.checkDigits(strGenreCod) ) {
			
			Optional<Genre>optGenre = new GenreController().getGenreById(strGenreCod);
			
			if (optGenre.isPresent() ) {
				
				tfGenreCod.setText( optGenre.get().getGenreId().toString() );
				tfGenreName.setText( optGenre.get().getName() );
				taGenreDescription.setText( optGenre.get().getDescription() );
				
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {			
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
	}
}
