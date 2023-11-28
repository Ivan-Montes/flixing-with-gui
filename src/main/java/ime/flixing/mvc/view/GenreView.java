package ime.flixing.mvc.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.mvc.controller.GenreController;

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
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
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
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblGenreTitle = new JLabel("GENRE");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblGenreTitle, 10, SpringLayout.NORTH, contentPanel);
			lblGenreTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
			contentPanel.add(lblGenreTitle);
		}
		
		JButton btnGetAll = new JButton("Get All");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblGenreTitle, 0, SpringLayout.WEST, btnGetAll);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetAll, 163, SpringLayout.WEST, contentPanel);
		btnGetAll.addActionListener( e -> GenreController.initGenreGetAllView());
		contentPanel.add(btnGetAll);
		
		JButton btnGetById = new JButton("Get By Id");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetAll, -6, SpringLayout.NORTH, btnGetById);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnGetById, 0, SpringLayout.WEST, btnGetAll);
		btnGetById.addActionListener( e -> GenreController.initGenreGetByIdView());
		contentPanel.add(btnGetById);
		
		JButton btnSave = new JButton("Save");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnGetById, -6, SpringLayout.NORTH, btnSave);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, btnGetAll);
		btnSave.addActionListener( e -> GenreController.initGenreSaveView());
		contentPanel.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnUpdate, -90, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnSave, -7, SpringLayout.NORTH, btnUpdate);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, btnGetAll);
		btnUpdate.addActionListener( e -> GenreController.initGenreUpdateView());
		contentPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnDelete, 6, SpringLayout.SOUTH, btnUpdate);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, btnGetAll);
		btnDelete.addActionListener( e -> GenreController.initGenreDeleteView());
		contentPanel.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnBack, -10, SpringLayout.EAST, contentPanel);
		btnBack.addActionListener( e ->dispose());
		contentPanel.add(btnBack);
	}
}
