package ime.flixing.mvc.view.position;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import ime.flixing.entity.Genre;
import ime.flixing.entity.Position;
import ime.flixing.mvc.controller.GenreController;
import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.Checker;
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
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionSaveView() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblName = new JLabel("Position Name");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblName, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblName);
		
		JLabel lblDescription = new JLabel("Description");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDescription, 48, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblDescription, 169, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblDescription);
		sl_contentPanel.putConstraint(SpringLayout.WEST, scrollPane, -392, SpringLayout.EAST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -58, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, scrollPane, -31, SpringLayout.EAST, contentPanel);
		contentPanel.add(scrollPane);
		
		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		
		taDescription.setLineWrap(true);
		((AbstractDocument)taDescription.getDocument()).setDocumentFilter(DocumentFilterFactory.createDocumentFilter(1));
		
		scrollPane.setViewportView(taDescription);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, taDescription, -101, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, taDescription, -134, SpringLayout.EAST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, taDescription, -79, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, taDescription, -119, SpringLayout.EAST, contentPanel);
		
		JButton btnSave = new JButton("Save");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSave, 13, SpringLayout.SOUTH, scrollPane);
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSave, 165, SpringLayout.WEST, contentPanel);
		btnSave.addActionListener( e -> savePosition() );
		contentPanel.add(btnSave);
	
		
		ftfPositionName = new JFormattedTextField();
	
	       ((AbstractDocument) ftfPositionName.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newStr.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+")) {
	                    super.insertString(fb, offset, string, attr);
	                }
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newStr.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+")) {
	                    super.replace(fb, offset, length, text, attrs);
	                }
	            }
	        });
        
        
		sl_contentPanel.putConstraint(SpringLayout.NORTH, ftfPositionName, -3, SpringLayout.NORTH, lblName);
		sl_contentPanel.putConstraint(SpringLayout.WEST, ftfPositionName, 11, SpringLayout.EAST, lblName);
		sl_contentPanel.putConstraint(SpringLayout.EAST, ftfPositionName, 0, SpringLayout.EAST, scrollPane);
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
					
					showResultInfo(optPos);
					cleanFields();
					
				}else {
					JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
				}					
			}				
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_DATA);
		}
		
	}
	
	private void showResultInfo(Optional<Position> optPos) {
		
		String msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
		
		if (optPos.isPresent() && optPos.get().getPositionId() != null ) {
			
			switch( String.valueOf(optPos.get().getPositionId() ) ) {
			
			case "-2":
				msgInfo = DecoHelper.MSG_ERROR_CHECKER;
				break;
			case "-1":
				msgInfo = DecoHelper.MSG_DUPLICATED_NAME;
				break;
			case "0":
				msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
				break;
			default:
				msgInfo = DecoHelper.MSG_SUCCESSFULLY;
				break;
			}
		}
		
		JOptionPane.showMessageDialog(this, msgInfo);
		
	}

	private void cleanFields() {
		
		ftfPositionName.setText("");
		taDescription.setText("");
		
	}
}
