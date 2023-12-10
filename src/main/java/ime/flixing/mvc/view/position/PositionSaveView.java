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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;

public class PositionSaveView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField ftfPositionName;
	private JTextArea taDescription;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PositionSaveView dialog = new PositionSaveView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PositionSaveView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionSaveView() {
		setModal(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblName = new JLabel("Position Name");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblName, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 48, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblDescription, 169, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblDescription);
		slContentPanel.putConstraint(SpringLayout.WEST, scrollPane, -392, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -58, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, scrollPane, -31, SpringLayout.EAST, contentPanel);
		contentPanel.add(scrollPane);
		
		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		
		taDescription.setLineWrap(true);
		((AbstractDocument)taDescription.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.DESCRIPTION_FULL));
		
		scrollPane.setViewportView(taDescription);
		slContentPanel.putConstraint(SpringLayout.NORTH, taDescription, -101, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, taDescription, -134, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, taDescription, -79, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, taDescription, -119, SpringLayout.EAST, contentPanel);
		
		JButton btnSave = new JButton("Save");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSave, 13, SpringLayout.SOUTH, scrollPane);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSave, 165, SpringLayout.WEST, contentPanel);
		btnSave.addActionListener( e -> savePosition() );
		contentPanel.add(btnSave);
	
		
		ftfPositionName = new JFormattedTextField();
		((AbstractDocument)ftfPositionName.getDocument()).setDocumentFilter(
				DocumentFilterFactory.createDocumentFilter(CheckerPattern.NAME_FULL));

        
		slContentPanel.putConstraint(SpringLayout.NORTH, ftfPositionName, -3, SpringLayout.NORTH, lblName);
		slContentPanel.putConstraint(SpringLayout.WEST, ftfPositionName, 11, SpringLayout.EAST, lblName);
		slContentPanel.putConstraint(SpringLayout.EAST, ftfPositionName, 0, SpringLayout.EAST, scrollPane);
		contentPanel.add(ftfPositionName);
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

	private void savePosition() {
		
		String strPositionName = ftfPositionName.getText();
		String strPositionDescription = taDescription.getText();
		
		if ( Checker.checkName(strPositionName) 
				&& Checker.checkDescription(strPositionDescription) ) {
			
			if ( JOptionPane.showConfirmDialog(this, DecoHelper.MSG_CONFIRM_OPTION, DecoHelper.MSG_CONFIRM_TITLE, JOptionPane.YES_NO_OPTION )
					 == JOptionPane.OK_OPTION ){
				
				Optional<Position>optPos = PositionController.savePosition(strPositionName, strPositionDescription);
				
				if (optPos.isPresent() ) {
					
					JOptionPane.showMessageDialog(this, 
							DecoHelper.chooseResultMessageByReturnValue( optPos.get().getPositionId() ) );
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
				}					
			}				
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
		
	}

	private void cleanFields() {
		
		ftfPositionName.setText("");
		taDescription.setText("");
		
	}
}
