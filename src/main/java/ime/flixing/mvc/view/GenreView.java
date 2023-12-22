package ime.flixing.mvc.view;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.GenreController;
import ime.flixing.tool.DecoHelper;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.JButton;

public class GenreView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblGenreTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenreView dialog = new GenreView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(GenreView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenreView() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		{
			lblGenreTitle = new JLabel("GENRE");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreTitle, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblGenreTitle, 173, SpringLayout.WEST, contentPanel);
			lblGenreTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblGenreTitle);
		}
		
		JButton btnGetAll = new JButton("Get All");
		slContentPanel.putConstraint(SpringLayout.WEST, btnGetAll, 163, SpringLayout.WEST, contentPanel);
		btnGetAll.addActionListener( e -> GenreController.initGenreGetAllView());
		contentPanel.add(btnGetAll);
		
		JButton btnGetById = new JButton("Get By Id");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetAll, -6, SpringLayout.NORTH, btnGetById);
		slContentPanel.putConstraint(SpringLayout.WEST, btnGetById, 0, SpringLayout.WEST, btnGetAll);
		btnGetById.addActionListener( e -> GenreController.initGenreGetByIdView());
		contentPanel.add(btnGetById);
		
		JButton btnSave = new JButton("Save");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnGetById, -6, SpringLayout.NORTH, btnSave);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, btnGetAll);
		btnSave.addActionListener( e -> GenreController.initGenreSaveView());
		contentPanel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnUpdate, -90, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnSave, -7, SpringLayout.NORTH, btnUpdate);
		slContentPanel.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, btnGetAll);
		btnUpdate.addActionListener( e -> GenreController.initGenreUpdateView());
		contentPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnDelete, 6, SpringLayout.SOUTH, btnUpdate);
		slContentPanel.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, btnGetAll);
		btnDelete.addActionListener( e -> GenreController.initGenreDeleteView());
		contentPanel.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, contentPanel);
		btnBack.addActionListener( e ->dispose());
		contentPanel.add(btnBack);
	}
}
