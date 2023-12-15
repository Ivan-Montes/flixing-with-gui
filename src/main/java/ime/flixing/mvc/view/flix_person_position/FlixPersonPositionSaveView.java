package ime.flixing.mvc.view.flix_person_position;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.tool.Checker;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.mvc.controller.FlixPersonPositionController;
import ime.flixing.mvc.controller.PersonController;
import ime.flixing.mvc.controller.PositionController;
import ime.flixing.tool.DecoHelper;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.Arrays;
import java.util.Optional;

import javax.swing.SpinnerNumberModel;

public class FlixPersonPositionSaveView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFlixCod;
	private JTextField tfPersonCod;
	private JTextField tfPositionCod;
	private JTextField tfFlixName;
	private JTextField tfPersonName;
	private JTextField tfPersonSurname;
	private JTextField tfPositionName;
	private JSpinner spFlixCod;
	private JSpinner spPositionCod;
	private JSpinner spPersonCod;
	private JTextArea taDescription;
	private JTextField tfGenreName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixPersonPositionSaveView dialog = new FlixPersonPositionSaveView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixPersonPositionSaveView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixPersonPositionSaveView() {
		setModal(true);
		setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setAlwaysOnTop(true);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		Font tahomaBold14 = new Font("Tahoma", Font.BOLD, 14);
		
		JLabel lblFlix = new JLabel("Flix");
		lblFlix.setFont(tahomaBold14);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblFlix, 22, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblFlix, 55, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblFlix);
		
		JLabel lblPerson = new JLabel("Person");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPerson, 0, SpringLayout.NORTH, lblFlix);
		slContentPanel.putConstraint(SpringLayout.WEST, lblPerson, 176, SpringLayout.EAST, lblFlix);
		lblPerson.setFont(tahomaBold14);
		contentPanel.add(lblPerson);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(tahomaBold14);
		slContentPanel.putConstraint(SpringLayout.NORTH, lblPosition, 0, SpringLayout.NORTH, lblFlix);
		slContentPanel.putConstraint(SpringLayout.EAST, lblPosition, -76, SpringLayout.EAST, contentPanel);
		contentPanel.add(lblPosition);
		
		tfFlixCod = new JTextField();
		tfFlixCod.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfFlixCod, 63, SpringLayout.SOUTH, lblFlix);
		slContentPanel.putConstraint(SpringLayout.WEST, tfFlixCod, 37, SpringLayout.WEST, contentPanel);
		contentPanel.add(tfFlixCod);
		tfFlixCod.setColumns(10);
		
		tfPersonCod = new JTextField();
		tfPersonCod.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonCod, 0, SpringLayout.NORTH, tfFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPersonCod, 122, SpringLayout.EAST, tfFlixCod);
		contentPanel.add(tfPersonCod);
		tfPersonCod.setColumns(10);
		
		tfPositionCod = new JTextField();
		tfPositionCod.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPositionCod, 0, SpringLayout.NORTH, tfFlixCod);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPositionCod, -50, SpringLayout.EAST, contentPanel);
		contentPanel.add(tfPositionCod);
		tfPositionCod.setColumns(10);
		
		spFlixCod = new JSpinner();
		spFlixCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, 17, SpringLayout.SOUTH, lblFlix);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 0, SpringLayout.WEST, tfFlixCod);
		contentPanel.add(spFlixCod);
		
		JButton btnSearchFlix = new JButton(DecoHelper.BTN_SEARCH);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearchFlix, 0, SpringLayout.NORTH, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearchFlix, 8, SpringLayout.EAST, spFlixCod);
		btnSearchFlix.addActionListener( e -> searchAndShowFlix() );
		contentPanel.add(btnSearchFlix);
		
		spPersonCod = new JSpinner();
		spPersonCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.WEST, spPersonCod, 70, SpringLayout.EAST, btnSearchFlix);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spPersonCod, 0, SpringLayout.SOUTH, spFlixCod);
		contentPanel.add(spPersonCod);
		
		JButton btnSearchPerson = new JButton(DecoHelper.BTN_SEARCH);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearchPerson, 0, SpringLayout.NORTH, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearchPerson, 16, SpringLayout.EAST, spPersonCod);
		btnSearchPerson.addActionListener( e -> searchAndShowPerson() );
		contentPanel.add(btnSearchPerson);
		
		spPositionCod = new JSpinner();
		spPositionCod.setModel(new SpinnerNumberModel(0, 0, 33, 1));
		slContentPanel.putConstraint(SpringLayout.NORTH, spPositionCod, 0, SpringLayout.NORTH, spFlixCod);
		contentPanel.add(spPositionCod);
		
		JButton btnSearchPosition = new JButton(DecoHelper.BTN_SEARCH);
		slContentPanel.putConstraint(SpringLayout.EAST, spPositionCod, -6, SpringLayout.WEST, btnSearchPosition);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearchPosition, 0, SpringLayout.NORTH, spFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearchPosition, 0, SpringLayout.WEST, lblPosition);
		btnSearchPosition.addActionListener( e -> searchAndShowPosition() );
		contentPanel.add(btnSearchPosition);
		
		tfFlixName = new JTextField();
		tfFlixName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfFlixName, 25, SpringLayout.SOUTH, tfFlixCod);
		slContentPanel.putConstraint(SpringLayout.WEST, tfFlixName, 0, SpringLayout.WEST, tfFlixCod);
		contentPanel.add(tfFlixName);
		tfFlixName.setColumns(10);
		
		tfPersonName = new JTextField();
		tfPersonName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.SOUTH, tfPersonName, 0, SpringLayout.SOUTH, tfFlixName);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPersonName, 0, SpringLayout.EAST, tfPersonCod);
		contentPanel.add(tfPersonName);
		tfPersonName.setColumns(10);
		
		tfPersonSurname = new JTextField();
		tfPersonSurname.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPersonSurname, 28, SpringLayout.SOUTH, tfPersonName);
		slContentPanel.putConstraint(SpringLayout.WEST, tfPersonSurname, 0, SpringLayout.WEST, tfPersonCod);
		contentPanel.add(tfPersonSurname);
		tfPersonSurname.setColumns(10);
		
		tfPositionName = new JTextField();
		tfPositionName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfPositionName, 0, SpringLayout.NORTH, tfFlixName);
		slContentPanel.putConstraint(SpringLayout.EAST, tfPositionName, 0, SpringLayout.EAST, tfPositionCod);
		contentPanel.add(tfPositionName);
		tfPositionName.setColumns(10);
		
		JScrollPane spDescription = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spDescription, 27, SpringLayout.SOUTH, tfPersonSurname);
		slContentPanel.putConstraint(SpringLayout.WEST, spDescription, 37, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spDescription, 104, SpringLayout.SOUTH, tfPersonSurname);
		slContentPanel.putConstraint(SpringLayout.EAST, spDescription, 572, SpringLayout.WEST, contentPanel);
		contentPanel.add(spDescription);
		
		taDescription = new JTextArea();
		taDescription.setWrapStyleWord(true);
		taDescription.setLineWrap(true);
		taDescription.setEditable(false);
		spDescription.setViewportView(taDescription);
		
		JButton btnSave = new JButton("Save");
		slContentPanel.putConstraint(SpringLayout.WEST, btnSave, 157, SpringLayout.WEST, contentPanel);
		btnSave.setActionCommand("OK");
		btnSave.addActionListener( e -> saveFlixPersonPosition() );
		contentPanel.add(btnSave);
		
		JButton btnClean = new JButton("Clean");
		slContentPanel.putConstraint(SpringLayout.EAST, btnClean, -170, SpringLayout.EAST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnClean);
		slContentPanel.putConstraint(SpringLayout.NORTH, btnClean, 24, SpringLayout.SOUTH, spDescription);
		btnClean.addActionListener( e -> cleanFields() );
		contentPanel.add(btnClean);
		
		tfGenreName = new JTextField();
		tfGenreName.setEditable(false);
		slContentPanel.putConstraint(SpringLayout.NORTH, tfGenreName, 0, SpringLayout.NORTH, tfPersonSurname);
		slContentPanel.putConstraint(SpringLayout.EAST, tfGenreName, 0, SpringLayout.EAST, tfFlixCod);
		contentPanel.add(tfGenreName);
		tfGenreName.setColumns(10);
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
	
	private void searchAndShowFlix() {
		
		
		Optional<Flix> optFlix = new FlixController().getFlixById(spFlixCod.getValue().toString());
		
		if ( optFlix.isPresent() ) {
			
			tfFlixCod.setText(optFlix.get().getFlixId().toString());
			tfFlixName.setText(optFlix.get().getTitle());
			tfGenreName.setText(optFlix.get().getGenre().getName());
		}else {
			showMessageBox();
		}
	}

	
	private void searchAndShowPerson() {
		
		Optional<Person> optPerson = new PersonController().getPersonById(spPersonCod.getValue().toString());
		
		if ( optPerson.isPresent() ) {
			
			tfPersonCod.setText(optPerson.get().getPersonId().toString());
			tfPersonName.setText(optPerson.get().getName());
			tfPersonSurname.setText(optPerson.get().getSurname());
		}else {
			showMessageBox();
		}
		
	}

	
	private void searchAndShowPosition() {
		
		Optional<Position> optPosition = new PositionController().getPositionById(spPositionCod.getValue().toString());
		
		if ( optPosition.isPresent() ) {
			
			tfPositionCod.setText(optPosition.get().getPositionId().toString());
			tfPositionName.setText(optPosition.get().getName());
			
		}else {
			showMessageBox();
		}
		
	}
	
	private void cleanFields() {
		
		spFlixCod.setValue(0);
		tfFlixCod.setText("");
		tfFlixName.setText("");
		tfGenreName.setText("");
		
		spPersonCod.setValue(0);
		tfPersonCod.setText("");
		tfPersonName.setText("");
		tfPersonSurname.setText("");
		
		spPositionCod.setValue(0);
		tfPositionCod.setText("");
		tfPositionName.setText("");
		
		taDescription.setText("");
	}
	
	private void showMessageBox() {
		JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_COD);
	}
	
	private void saveFlixPersonPosition() {
		
		String strFlixCod = tfFlixCod.getText();
		String strPersonCod = tfPersonCod.getText();
		String strPositionCod = tfPositionCod.getText();
		
		if( Arrays.asList(
				Checker.checkDigits(strFlixCod),Checker.checkDigits(strPersonCod),Checker.checkDigits(strPositionCod))
											.stream().allMatch( b -> b.equals(true))){
												
					Optional<FlixPersonPosition>optFlixPersonPosition = new FlixPersonPositionController().saveFlixPersonPosition(strFlixCod, strPersonCod, strPositionCod);				
			
						if ( optFlixPersonPosition.isPresent()) {
							
							JOptionPane.showMessageDialog(this, DecoHelper.MSG_SUCCESSFULLY);
							cleanFields();
							taDescription.setText(optFlixPersonPosition.get().toString());
						}else {
							JOptionPane.showMessageDialog(this, DecoHelper.MSG_ERROR_PROCESS);
						}
					
					
		}else {
			showMessageBox();
		}
	}
	
	
}
