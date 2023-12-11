package ime.flixing.mvc.view.position;

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

import ime.flixing.entity.Position;
import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.CheckerPattern;
import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.DocumentFilterFactory;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PositionUpdateView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPositionCod;
	private JTextField tfPositionName;
	private JSpinner spPositionCod;
	private JTextArea taDescription;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PositionUpdateView dialog = new PositionUpdateView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PositionUpdateView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionUpdateView() {
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblPositionCod = new JLabel("Position Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPositionCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPositionCod, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblPositionCod);
		
		spPositionCod = new JSpinner();
		spPositionCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spPositionCod, 0, SpringLayout.NORTH, lblPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spPositionCod, 31, SpringLayout.EAST, lblPositionCod);
		contentPanel.add(spPositionCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 36, SpringLayout.EAST, spPositionCod);
		btnSearch.addActionListener( e -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 0, SpringLayout.NORTH, lblPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 35, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 9, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 27, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 23, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, 387, SpringLayout.WEST, contentPanel);
		contentPanel.add(separator);
		
		tfPositionCod = new JTextField();
		((AbstractDocument)tfPositionCod.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.DIGITS_BASIC));
		slContentPanel.putConstraint(SpringLayout.WEST, tfPositionCod, 0, SpringLayout.WEST, separator);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfPositionCod, -133, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPositionCod, 38, SpringLayout.EAST, lblPositionCod);
		tfPositionCod.setEditable(false);
		contentPanel.add(tfPositionCod);
		tfPositionCod.setColumns(10);
		
		tfPositionName = new JTextField();
		((AbstractDocument)tfPositionName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.NAME_FULL));
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPositionName, 16, SpringLayout.SOUTH, tfPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPositionName, 0, SpringLayout.WEST, separator);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPositionName, -37, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfPositionName);
		tfPositionName.setColumns(10);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, 16, SpringLayout.SOUTH, tfPositionName);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 27, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, -15, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, 0, SpringLayout.EAST, separator);
		contentPanel.add(spDescription);
		
		taDescription = new JTextArea();
		((AbstractDocument)taDescription.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.DESCRIPTION_FULL));
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		spDescription.setViewportView(taDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUpdate = new JButton("Update");
				btnUpdate.setActionCommand("OK");
				btnUpdate.addActionListener( e -> updatePosition() );
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

	private void searchAndShow() {
		
		String strPositionCod = spPositionCod.getValue().toString();
		
		if ( Checker.checkDescription(strPositionCod) ) {
			
			Optional<Position>optPosition = new PositionController().getPositionById(strPositionCod);
			
			if ( optPosition.isPresent() ) {
				
				tfPositionCod.setText( optPosition.get().getPositionId().toString() );
				tfPositionName.setText( optPosition.get().getName() );
				taDescription.setText( optPosition.get().getDescription() );
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
	}

	private void cleanFields() {
		
		spPositionCod.setValue(0);
		tfPositionCod.setText("");
		tfPositionName.setText("");
		taDescription.setText("");
	}

	private void updatePosition() {
		
		String strPositionCod = tfPositionCod.getText();
		String strPositionName = tfPositionName.getText();
		String strPositionDescription = taDescription.getText();
		
		if ( Checker.checkDigits(strPositionCod) 
				&& Checker.checkName(strPositionName)
				&& Checker.checkDescription(strPositionDescription) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Position>optPosition = new PositionController().updatePosition(strPositionCod, strPositionName, strPositionDescription);

				if (optPosition.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, DecoHelper.chooseResultMessageByReturnValue( optPosition.get().getPositionId() ) );

					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
				}
				
			}			
			
		}else {
			
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_CHECKER);
			
		}
		
	}	
	
}
