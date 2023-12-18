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
import java.util.logging.Logger;
import java.util.logging.Level;

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
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
		    final Logger logger = Logger.getLogger(FlixDeleteView.class.getName());
            logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixDeleteView() {
		setModal(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
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
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		{
			lblFlixCod = new JLabel("Flix Code");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblFlixCod, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblFlixCod, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblFlixCod);
		}

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 33, 1);
		spFlixCod = new JSpinner(spinnerNumberModel);
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, 0, SpringLayout.NORTH, lblFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 40, SpringLayout.EAST, lblFlixCod);
		contentPanel.add(spFlixCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 28, SpringLayout.EAST, spFlixCod);
		btnSearch.addActionListener( a -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 42, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( a -> cleanFields() );
		contentPanel.add(btnClean);
		
		tfFlixName = new JTextField();
		slContentPanel.putConstraint(SpringLayout.EAST, tfFlixName, 256, SpringLayout.WEST, contentPanel);
		tfFlixName.setMinimumSize(new Dimension(20, 20));
		tfFlixName.setPreferredSize(new Dimension(20, 20));
		tfFlixName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfFlixName, 30, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, tfFlixName, 87, SpringLayout.WEST, contentPanel);
		tfFlixName.setColumns(10);
		contentPanel.add(tfFlixName);
		
		tfGenreName = new JTextField();
		slContentPanel.putConstraint(SpringLayout.WEST, tfGenreName, 87, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, tfGenreName, 256, SpringLayout.WEST, contentPanel);
		tfGenreName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfGenreName, -55, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
		
		spGenreCod = new JSpinner();
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 20, SpringLayout.SOUTH, tfFlixName);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 0, SpringLayout.WEST, tfFlixName);
		spGenreCod.setEnabled(false);
		contentPanel.add(spGenreCod);
		
		JLabel lblFlixName = new JLabel("Flix Name");
		lblFlixName.setLabelFor(tfFlixName);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblFlixName, 0, SpringLayout.NORTH, tfFlixName);
		slContentPanel.putConstraint(SpringLayout.WEST, lblFlixName, 0, SpringLayout.WEST, lblFlixCod);
		contentPanel.add(lblFlixName);
		
		JLabel lblGenreCod = new JLabel("Genre Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreCod, 3, SpringLayout.NORTH, spGenreCod);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenreCod, 0, SpringLayout.WEST, lblFlixCod);
		lblGenreCod.setLabelFor(spGenreCod);
		contentPanel.add(lblGenreCod);
		
		JLabel lblGenreName = new JLabel("Genre Name");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreName, 3, SpringLayout.NORTH, tfGenreName);
		slContentPanel.putConstraint(SpringLayout.WEST, lblGenreName, 0, SpringLayout.WEST, lblFlixCod);
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
				 
				 showInfoAboutDelete( new FlixController().deleteFlix(txtFlixCod) );
				 cleanFields();
			 }
			
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}	
	}

	private void searchAndShow() {
		
			if ( Checker.checkDigits(spFlixCod.getValue().toString()) ){
			
			Optional<Flix> optFlix = new FlixController().getFlixById( spFlixCod.getValue().toString() );
			
			
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
