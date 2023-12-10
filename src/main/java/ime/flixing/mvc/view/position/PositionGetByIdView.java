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

import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ime.flixing.entity.Position;

public class PositionGetByIdView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfPositionCodFound;
	private JTextField tfPositionName;
	private JSpinner spPositionCod;
	private JTextArea taDescription;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PositionGetByIdView dialog = new PositionGetByIdView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(PositionGetByIdView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public PositionGetByIdView() {
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
		slContentPanel.putConstraint(SpringLayout.WEST, spPositionCod, 43, SpringLayout.EAST, lblPositionCod);
		contentPanel.add(spPositionCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, -1, SpringLayout.NORTH, spPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 39, SpringLayout.EAST, spPositionCod);
		btnSearch.addActionListener( e -> searchAndShow() );
		contentPanel.add(btnSearch);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, -1, SpringLayout.NORTH, spPositionCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnClean, 52, SpringLayout.EAST, btnSearch);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		JSeparator separator = new JSeparator();
		slContentPanel.putConstraint(SpringLayout.NORTH, separator, 10, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, separator, 12, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, btnClean);
		contentPanel.add(separator);
		
		tfPositionCodFound = new JTextField();
		tfPositionCodFound.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPositionCodFound, 21, SpringLayout.SOUTH, separator);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPositionCodFound, 0, SpringLayout.WEST, lblPositionCod);
		contentPanel.add(tfPositionCodFound);
		tfPositionCodFound.setColumns(10);
		
		tfPositionName = new JTextField();
		tfPositionName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPositionName, 0, SpringLayout.NORTH, tfPositionCodFound);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPositionName, 0, SpringLayout.WEST, spPositionCod);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPositionName, 0, SpringLayout.EAST, btnClean);
		contentPanel.add(tfPositionName);
		tfPositionName.setColumns(10);
		
		JScrollPane spPositionDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spPositionDescription, 21, SpringLayout.SOUTH, tfPositionCodFound);
		slContentPanel.putConstraint(SpringLayout.WEST, spPositionDescription, 10, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spPositionDescription, 106, SpringLayout.SOUTH, tfPositionCodFound);
		slContentPanel.putConstraint(SpringLayout.EAST, spPositionDescription, 0, SpringLayout.EAST, btnClean);
		contentPanel.add(spPositionDescription);
		
		taDescription = new JTextArea();
		taDescription.setLineWrap(true);
		taDescription.setWrapStyleWord(true);
		taDescription.setEditable(false);
		spPositionDescription.setViewportView(taDescription);
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

	private void cleanFields() {
		
		tfPositionCodFound.setText("");
		tfPositionName.setText("");
		taDescription.setText("");
		
	}

	private void searchAndShow() {
		
		String strPositionCod = spPositionCod.getValue().toString();
		
		if ( Checker.checkDigits(strPositionCod)) {
			
			Optional<Position>optPosition = PositionController.getPositionById(strPositionCod);
			
			if ( optPosition.isPresent()) {
				
				tfPositionCodFound.setText( optPosition.get().getPositionId().toString() );
				tfPositionName.setText( optPosition.get().getName() );
				taDescription.setText( optPosition.get().getDescription() );
				
			}else {
				JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_NULL);
			}			
			
		}else {
			JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
		}
		
	}
}
