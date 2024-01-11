package ime.flixing.mvc.view.genre;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Genre;
import ime.flixing.mvc.controller.GenreController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.MsgBox;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class GenreGetByIdView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textAreaDescription;
	private JList<String> jListGenre;
	private JSpinner spGenreCod;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreGetByIdView dialog = new GenreGetByIdView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreGetByIdView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreGetByIdView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblGenreCod = new JLabel("Genre Id");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenreCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblGenreCod);
		
		spGenreCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 0, SpringLayout.NORTH, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 23, SpringLayout.EAST, lblGenreCod);
		contentPanel.add(spGenreCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 25, SpringLayout.EAST, spGenreCod);
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnSearch, 0, SpringLayout.SOUTH, spGenreCod);
		btnSearch.addActionListener( e -> searchAndShowGenre() );
		contentPanel.add(btnSearch);
		
		jListGenre = new JList<>();
		jListGenre.setModel(new DefaultListModel<>());
		
		JScrollPane spListGenre = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spListGenre, 36, SpringLayout.SOUTH, spGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spListGenre, 0, SpringLayout.WEST, lblGenreCod);
		spListGenre.setViewportView(jListGenre);
		contentPanel.add(spListGenre);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.EAST, spListGenre, 0, SpringLayout.EAST, spDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 0, SpringLayout.WEST, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -5, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(spDescription);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setEditable(false);
		spDescription.setViewportView(textAreaDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, textAreaDescription, 46, SpringLayout.EAST, spListGenre);
		slContentPanel.putConstraint(SpringLayout.SOUTH, textAreaDescription, -150, SpringLayout.SOUTH, contentPanel);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, 121, SpringLayout.SOUTH, btnClean);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 24, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JLabel lblDescription = new JLabel("Description");
		slContentPanel.putConstraint(SpringLayout.SOUTH, spListGenre, -8, SpringLayout.NORTH, lblDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.SOUTH, lblDescription, -6, SpringLayout.NORTH, spDescription);
		contentPanel.add(lblDescription);
		
		JLabel lblGenre = new JLabel("Genre");
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenre, 10, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, lblGenre, -6, SpringLayout.NORTH, spListGenre);
		contentPanel.add(lblGenre);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 6, SpringLayout.SOUTH, spGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, lblGenreCod);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, -5, SpringLayout.NORTH, lblGenre);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, contentPanel);
		contentPanel.add(separator);
				
		
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
	
	private void searchAndShowGenre() {
		
		if ( Checker.checkDigits(spGenreCod.getValue().toString()) ){
			
			Optional<Genre>optGenreFound = new GenreController().getGenreById( spGenreCod.getValue().toString() );
			
			if ( optGenreFound.isPresent() ) {
				
				jListGenre.setListData(new String[] {
													"Id: " + optGenreFound.get().getGenreId().toString() + 
													 "  //  " +
											 		"Name: " + optGenreFound.get().getName() 
											 		} 
										);
				textAreaDescription.setText( optGenreFound.get().getDescription() );
				
			}else {
				new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			new MsgBox().showBasicDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
		
	}
	
	private void cleanFields() {
		
		textAreaDescription.setText("");
		jListGenre.setListData(new String[] {});
	}
}
