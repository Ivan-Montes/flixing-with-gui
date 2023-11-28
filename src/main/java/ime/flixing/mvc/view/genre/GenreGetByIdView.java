package ime.flixing.mvc.view.genre;

import java.awt.BorderLayout;
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

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

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
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreGetByIdView() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblGenreCod = new JLabel("Genre Id");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblGenreCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblGenreCod);
		
		spGenreCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 0, SpringLayout.NORTH, lblGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 23, SpringLayout.EAST, lblGenreCod);
		contentPanel.add(spGenreCod);
		
		JButton btnSearch = new JButton("Search");
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 25, SpringLayout.EAST, spGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnSearch, 0, SpringLayout.SOUTH, spGenreCod);
		btnSearch.addActionListener( e -> searchAndShowGenre() );
		contentPanel.add(btnSearch);
		
		jListGenre = new JList<>();
		jListGenre.setModel(new DefaultListModel<String>());
		
		JScrollPane spListGenre = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spListGenre, 36, SpringLayout.SOUTH, spGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spListGenre, 0, SpringLayout.WEST, lblGenreCod);
		spListGenre.setViewportView(jListGenre);
		contentPanel.add(spListGenre);
		
		JScrollPane spDescription = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.EAST, spListGenre, 0, SpringLayout.EAST, spDescription);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spDescription, 0, SpringLayout.WEST, lblGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -5, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spDescription, -15, SpringLayout.EAST, contentPanel);
		contentPanel.add(spDescription);
		
		textAreaDescription = new JTextArea();
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setEditable(false);
		spDescription.setViewportView(textAreaDescription);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textAreaDescription, 46, SpringLayout.EAST, spListGenre);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, textAreaDescription, -150, SpringLayout.SOUTH, contentPanel);
		
		JButton btnClean = new JButton("Clean");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spDescription, 121, SpringLayout.SOUTH, btnClean);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnClean, 24, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JLabel lblDescription = new JLabel("Description");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spListGenre, -8, SpringLayout.NORTH, lblDescription);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblDescription, 0, SpringLayout.WEST, lblGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblDescription, -6, SpringLayout.NORTH, spDescription);
		contentPanel.add(lblDescription);
		
		JLabel lblGenre = new JLabel("Genre");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblGenre, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblGenre, -6, SpringLayout.NORTH, spListGenre);
		contentPanel.add(lblGenre);
		
		JSeparator separator = new JSeparator();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, separator, 6, SpringLayout.SOUTH, spGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, lblGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, separator, -5, SpringLayout.NORTH, lblGenre);
		sl_contentPanel.putConstraint(SpringLayout.EAST, separator, -10, SpringLayout.EAST, contentPanel);
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
			
			Optional<Genre>optGenreFound = GenreController.getGenreById( spGenreCod.getValue().toString() );
			
			if ( optGenreFound.isPresent() ) {
				
				jListGenre.setListData(new String[] {
													"Id: " + optGenreFound.get().getGenreId().toString() + 
													 "  //  " +
											 		"Name: " + optGenreFound.get().getName() 
											 		} 
										);
				textAreaDescription.setText( optGenreFound.get().getDescription() );
				
			}else {
				JOptionPane.showMessageDialog(this, "\t" + DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "\t" + DecoHelper.MSG_ERROR_DATA);
		}
		
	}
	
	private void cleanFields() {
		
		textAreaDescription.setText("");
		jListGenre.setListData(new String[] {});
	}
}
