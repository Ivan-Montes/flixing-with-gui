package ime.flixing.mvc.view.flix;

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

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

public class FlixSaveView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFlixName;
	private JLabel lblFlixName;
	private JSpinner spGenreId;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixSaveView dialog = new FlixSaveView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixSaveView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixSaveView() {
		setModal(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		{
			lblFlixName = new JLabel("Flix Name");
			slContentPanel.putConstraint(SpringLayout.NORTH, lblFlixName, 10, SpringLayout.NORTH, contentPanel);
			slContentPanel.putConstraint(SpringLayout.WEST, lblFlixName, 10, SpringLayout.WEST, contentPanel);
			contentPanel.add(lblFlixName);
		}
		
		tfFlixName = new JTextField();
		((AbstractDocument)tfFlixName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.TITLE_FLIX_FULL));
		slContentPanel.putConstraint(SpringLayout.NORTH, tfFlixName, -3, SpringLayout.NORTH, lblFlixName);
		slContentPanel.putConstraint(SpringLayout.EAST, tfFlixName, -17, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfFlixName);
		tfFlixName.setColumns(10);

		spGenreId = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.WEST, tfFlixName, 0, SpringLayout.WEST, spGenreId);
		slContentPanel.putConstraint(SpringLayout.NORTH, spGenreId, 48, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, spGenreId, 79, SpringLayout.WEST, contentPanel);
		contentPanel.add(spGenreId);
		
		JLabel lblGenreId = new JLabel("Genre Id");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblGenreId, 0, SpringLayout.NORTH, spGenreId);
		slContentPanel.putConstraint(SpringLayout.EAST, lblGenreId, 0, SpringLayout.EAST, lblFlixName);
		contentPanel.add(lblGenreId);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener( e -> saveFlix() );
			}
			{
				JButton cancelButton = new JButton("Back");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener( e -> dispose() );
			}
		}
	}
	
	private void saveFlix() {
		
		String strFlixName = tfFlixName.getText();
		String strGenreId = spGenreId.getValue().toString();
		
		if ( Checker.checkFlixTitle(strFlixName) && Checker.checkDigits(strGenreId) ) {

			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){

				Optional<Flix> optFlixSaved = new FlixController().saveFlix(strFlixName, strGenreId);
				
				if (optFlixSaved.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_SUCCESSFULLY);
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
				}		
				
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "\t" + DecoHelper.MSG_ERROR_DATA);
		}
	}
	

	private void cleanFields() {
		
		tfFlixName.setText("");
		spGenreId.setValue(0);
		
	}
}
