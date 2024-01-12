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
import javax.swing.text.AbstractDocument;

import ime.flixing.entity.Genre;
import ime.flixing.mvc.controller.GenreController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.CheckerPattern;
import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.DocumentFilterFactory;
import ime.flixing.tool.MsgBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class GenreUpdateView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblGenreCod;
	private JSpinner spGenreCod;
	private JTextField tfGenreName;
	private JTextArea taGenreDescription;
	private JTextField tfGenreCodFound;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreUpdateView dialog = new GenreUpdateView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreUpdateView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreUpdateView() {
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
			slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 13, SpringLayout.NORTH, contentPanel);
			contentPanel.add(lblGenreCod);
		}
		
		spGenreCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1) );
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, -3, SpringLayout.NORTH, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 38, SpringLayout.EAST, lblGenreCod);
		lblGenreCod.setLabelFor(spGenreCod);
		contentPanel.add(spGenreCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, -4, SpringLayout.NORTH, lblGenreCod);
		btnSearch.addActionListener( e -> searchAndShowGenre() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.EAST, btnSearch, -55, SpringLayout.WEST, btnClean);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, -4, SpringLayout.NORTH, lblGenreCod);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JLabel lblGenreName = new JLabel("Genre Name");
		slContentPanel.putConstraint(SpringLayout.EAST, lblGenreCod, 0, SpringLayout.EAST, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenreName, 34, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblGenreName);
		
		tfGenreName = new JTextField();
		((AbstractDocument)tfGenreName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.NAME_FULL));
		slContentPanel.putConstraint(SpringLayout.EAST, btnClean, 0, SpringLayout.EAST, tfGenreName);
		lblGenreName.setLabelFor(tfGenreName);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfGenreName, 0, SpringLayout.NORTH, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreName, 15, SpringLayout.EAST, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.EAST, tfGenreName, -26, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.SOUTH, lblGenreName, -37, SpringLayout.NORTH, spDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 0, SpringLayout.WEST, lblGenreName);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -10, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, -26, SpringLayout.EAST, contentPanel);
		contentPanel.add(spDescription);
		
		taGenreDescription = new JTextArea();
		((AbstractDocument)taGenreDescription.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.DESCRIPTION_FULL));
		spDescription.setViewportView(taGenreDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setLabelFor(taGenreDescription);
		slContentPanel.putConstraint(SpringLayout.SOUTH, lblDescription, -69, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, 1, SpringLayout.SOUTH, lblDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, lblDescription, 184, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblDescription);
		
		tfGenreCodFound = new JTextField();
		tfGenreCodFound.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreCodFound, 0, SpringLayout.WEST, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfGenreCodFound, -10, SpringLayout.NORTH, tfGenreName);
		contentPanel.add(tfGenreCodFound);
		tfGenreCodFound.setColumns(10);
		
		JLabel lblGenreCodFound = new JLabel("Genre Code");
		lblGenreCodFound.setLabelFor(tfGenreCodFound);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreCodFound, 3, SpringLayout.NORTH, tfGenreCodFound);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenreCodFound, 0, SpringLayout.WEST, lblGenreName);
		contentPanel.add(lblGenreCodFound);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 47, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 36, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, -15, SpringLayout.NORTH, tfGenreCodFound);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, -28, SpringLayout.EAST, contentPanel);
		contentPanel.add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.setActionCommand("OK");
				btnUpdate.addActionListener( e -> updateGenre() );
				buttonPane.add(btnUpdate);
				getRootPane().setDefaultButton(btnUpdate);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( e -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}
	

	private void searchAndShowGenre() {
		
		String strGenreCod = spGenreCod.getValue().toString();
		
		if ( Checker.checkDigits(strGenreCod) ) {
			
			Optional<Genre>optGenre = new GenreController().getGenreById(strGenreCod);
			
			if ( optGenre.isPresent() ) {
				
				tfGenreCodFound.setText(strGenreCod);
				tfGenreName.setText(optGenre.get().getName());
				taGenreDescription.setText(optGenre.get().getDescription() );
				
			}else {
				new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_NULL);		
			}
			
		}else {
			new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_COD);
		}		
		
	}

	private void updateGenre() {
		
		if(	Checker.checkDigits( tfGenreCodFound.getText() ) 
				&& Checker.checkName( tfGenreName.getText() ) 
				&& Checker.checkDescription( taGenreDescription.getText() ) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Genre>optGenre = new GenreController().updateGenre(tfGenreCodFound.getText(),
															tfGenreName.getText(), 
															taGenreDescription.getText());

				if (optGenre.isPresent() ) {				

					showResultInfo(optGenre);
					cleanFields();
				
				}else {
					
					new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_NULL);		
				
				}				
			}
		}else {
			new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
		
	}

	private void showResultInfo(Optional<Genre> optGenre) {
		
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
				msgInfo = DecoHelper.MSG_SUCCESSFULLY + " with Genre Code: " + optGenre.get().getGenreId();	
				break;
			}
		}
		
		JOptionPane.showMessageDialog(this, msgInfo);
		
	}

	private void cleanFields() {
		
		tfGenreCodFound.setText("");
		tfGenreName.setText("");
		spGenreCod.setValue(0);
		taGenreDescription.setText("");
		
	}
}
