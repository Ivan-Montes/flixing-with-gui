package ime.flixing.mvc.view.flix;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.CheckerPattern;
import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.DocumentFilterFactory;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.util.Optional;

public class FlixUpdateView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFlixName;
	private JSpinner spFlixCod;
	private JButton btnSearch;
	private JSpinner spGenreCod;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixUpdateView dialog = new FlixUpdateView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixUpdateView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixUpdateView() {
		setModal(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblFlixCod = new JLabel("Flix Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblFlixCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblFlixCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblFlixCod);
		
		
		spFlixCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, 0, SpringLayout.NORTH, lblFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 28, SpringLayout.EAST, lblFlixCod);
		contentPanel.add(spFlixCod);
		
		btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, -1, SpringLayout.NORTH, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 42, SpringLayout.EAST, spFlixCod);
		btnSearch.addActionListener( e -> searchFlix() );
		contentPanel.add(btnSearch);
		
		tfFlixName = new JTextField();
		((AbstractDocument)tfFlixName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.TITLE_FLIX_FULL));
		slContentPanel.putConstraint(SpringLayout.NORTH, tfFlixName, 21, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, tfFlixName, 0, SpringLayout.WEST, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.EAST, tfFlixName, -172, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfFlixName);
		tfFlixName.setColumns(10);
		
		spGenreCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreCod, 95, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreCod, 80, SpringLayout.WEST, contentPanel);
		contentPanel.add(spGenreCod);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, -1, SpringLayout.NORTH, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 49, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( a -> cleanFields() );
		contentPanel.add(btnClean);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.addActionListener( e -> saveFlix() );
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				JButton btnBack = new JButton("Back");
				btnBack.addActionListener( e -> dispose() );
				btnBack.setActionCommand("Cancel");
				buttonPane.add(btnBack);
			}
		}
	}
	
	private void searchFlix() {
		
		if ( Checker.checkDigits(spFlixCod.getValue().toString()) ){
			
			Optional<Flix> optFlix = new FlixController().getFlixById( spFlixCod.getValue().toString() );
			
			
			if ( optFlix.isPresent() ) {				
				
				tfFlixName.setText( optFlix.get().getTitle() );
				spGenreCod.setValue( optFlix.get().getGenre().getGenreId() );
				

			}else {				
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);				
			}			
		}
		else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}		
	}
	
	private void saveFlix() {
		
		String txtFlixCod = spFlixCod.getValue().toString();
		String txtFlixName = tfFlixName.getText();
		String txtSpGenreCod = spGenreCod.getValue().toString();
		
		if ( Checker.checkDigits( txtFlixCod ) 
				&& Checker.checkFlixTitle( txtFlixName ) 
				&& Checker.checkDigits( txtSpGenreCod) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Flix>optFlixSaved = new FlixController().updateFlix(txtFlixCod, txtFlixName, txtSpGenreCod);
				
				if ( optFlixSaved.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_SUCCESSFULLY);	
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
				}				
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
	}
	
	
	private void cleanFields() {
		
		tfFlixName.setText("");
		spFlixCod.setValue(0);
		spGenreCod.setValue(0);
		
	}
}
