package ime.flixing.mvc.view.flix;

import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;

public class FlixDeleteView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblFlixCod;
	private JTextField tfFlixName;
	private JTextField tfGenreName;
	private JSpinner spFlixCod;
	private JSpinner spGenreCod;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixDeleteView dialog = new FlixDeleteView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixDeleteView() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, contentPanel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, contentPanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, contentPanel, 228, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, contentPanel, 434, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblFlixCod = new JLabel("Flix Code");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblFlixCod, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblFlixCod, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblFlixCod);
		}

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 33, 1);
		spFlixCod = new JSpinner(spinnerNumberModel);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, 0, SpringLayout.NORTH, lblFlixCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 40, SpringLayout.EAST, lblFlixCod);
		contentPanel.add(spFlixCod);
		
		JButton btnSearch = new JButton("Search");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblFlixCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 28, SpringLayout.EAST, spFlixCod);
		btnSearch.addActionListener( a -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblFlixCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnClean, 42, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( a -> cleanFields() );
		contentPanel.add(btnClean);
		
		tfFlixName = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfFlixName, 256, SpringLayout.WEST, contentPanel);
		tfFlixName.setMinimumSize(new Dimension(20, 20));
		tfFlixName.setPreferredSize(new Dimension(20, 20));
		tfFlixName.setEditable(false);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, tfFlixName, 30, SpringLayout.SOUTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfFlixName, 87, SpringLayout.WEST, contentPanel);
		tfFlixName.setColumns(10);
		contentPanel.add(tfFlixName);
		
		tfGenreName = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, tfGenreName, 87, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, tfGenreName, 256, SpringLayout.WEST, contentPanel);
		tfGenreName.setEditable(false);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, tfGenreName, -55, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
		
		spGenreCod = new JSpinner();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 20, SpringLayout.SOUTH, tfFlixName);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 0, SpringLayout.WEST, tfFlixName);
		spGenreCod.setEnabled(false);
		contentPanel.add(spGenreCod);
		
		JLabel lblFlixName = new JLabel("Flix Name");
		lblFlixName.setLabelFor(tfFlixName);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblFlixName, 0, SpringLayout.NORTH, tfFlixName);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblFlixName, 0, SpringLayout.WEST, lblFlixCod);
		contentPanel.add(lblFlixName);
		
		JLabel lblGenreCod = new JLabel("Genre Code");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 3, SpringLayout.NORTH, spGenreCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblGenreCod, 0, SpringLayout.WEST, lblFlixCod);
		lblGenreCod.setLabelFor(spGenreCod);
		contentPanel.add(lblGenreCod);
		
		JLabel lblGenreName = new JLabel("Genre Name");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblGenreName, 3, SpringLayout.NORTH, tfGenreName);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblGenreName, 0, SpringLayout.WEST, lblFlixCod);
		lblGenreName.setLabelFor(tfGenreName);
		contentPanel.add(lblGenreName);
		{
			JPanel buttonPane = new JPanel();
			springLayout.putConstraint(SpringLayout.NORTH, buttonPane, 228, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, buttonPane, 0, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, buttonPane, 434, SpringLayout.WEST, getContentPane());
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.setActionCommand("OK");
				buttonPane.add(btnDelete);
				btnDelete.addActionListener( a -> deleteFlix() );
				getRootPane().setDefaultButton(btnDelete);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.setActionCommand("Cancel");
				btnBack.addActionListener( a -> dispose() );
				buttonPane.add(btnBack);
			}
		}
	}
	

	private void deleteFlix() {
		
		String txtFlixCod = spFlixCod.getValue().toString();
		
		if ( Checker.checkDigits(txtFlixCod ) ) {
			
			
			 if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				 
				 showInfoAboutDelete( FlixController.deleteFlix(txtFlixCod) );
				 cleanFields();
			 }
			
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}	
	}

	private void searchAndShow() {
		
			if ( Checker.checkDigits(spFlixCod.getValue().toString()) ){
			
			Optional<Flix> optFlix = FlixController.searchFlixCod( spFlixCod.getValue().toString() );
			
			
			if ( optFlix.isPresent() ) {				
				
				tfFlixName.setText( optFlix.get().getTitle() );
				spGenreCod.setValue( optFlix.get().getGenre() != null? optFlix.get().getGenre().getGenreId():"" );
				tfGenreName.setText( optFlix.get().getGenre() != null? optFlix.get().getGenre().getName():"" );

			}else {				
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);				
			}			
		}
		else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}		
	}

	private void cleanFields() {

		spFlixCod.setValue(0);
		tfFlixName.setText("");
		spGenreCod.setValue(0);
		tfGenreName.setText("");
		
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
}
