package ime.flixing.mvc.view.flix;

import javax.swing.JButton;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.util.Optional;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class FlixGetByIdView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner spFlixCod;
	private JScrollPane spFlix;
	private JTextArea taFlix;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixGetByIdView dialog = new FlixGetByIdView();
			dialog.setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			dialog.setVisible(true);
		} catch (Exception e) {
			final Logger logger = Logger.getLogger(FlixGetByIdView.class.getName());
			logger.log(Level.SEVERE, DecoHelper.MSG_SHIT_HAPPENS, e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public FlixGetByIdView() {
		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, contentPanel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, contentPanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, contentPanel, 261, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, contentPanel, 434, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		SpringLayout slContentPanel = new SpringLayout();
		contentPanel.setLayout(slContentPanel);
		
		JLabel lblCod = new JLabel("Flix Code");
		slContentPanel.putConstraint(SpringLayout.NORTH, lblCod, 10, SpringLayout.NORTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.WEST, lblCod, 22, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblCod);
		
		JButton btnSearch = new JButton("Search");
		slContentPanel.putConstraint(SpringLayout.WEST, btnSearch, 129, SpringLayout.EAST, lblCod);
		btnSearch.addActionListener(e -> searchFlixCod() );
		slContentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblCod);
		contentPanel.add(btnSearch);	
		
		spFlixCod = new JSpinner(new SpinnerNumberModel(0, 0, 33, 1));
		lblCod.setLabelFor(spFlixCod);
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, -3, SpringLayout.NORTH, lblCod);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 28, SpringLayout.EAST, lblCod);
		slContentPanel.putConstraint(SpringLayout.EAST, spFlixCod, 138, SpringLayout.WEST, contentPanel);
		contentPanel.add(spFlixCod);
		
		spFlix = new JScrollPane();
		slContentPanel.putConstraint(SpringLayout.NORTH, spFlix, 19, SpringLayout.SOUTH, btnSearch);
		slContentPanel.putConstraint(SpringLayout.WEST, spFlix, 0, SpringLayout.WEST, lblCod);
		slContentPanel.putConstraint(SpringLayout.SOUTH, spFlix, -46, SpringLayout.SOUTH, contentPanel);
		slContentPanel.putConstraint(SpringLayout.EAST, spFlix, -21, SpringLayout.EAST, contentPanel);
		contentPanel.add(spFlix);
		
		taFlix = new JTextArea();
		taFlix.setWrapStyleWord(true);
		taFlix.setLineWrap(true);
		spFlix.setViewportView(taFlix);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener( e -> dispose() );
		slContentPanel.putConstraint(SpringLayout.WEST, btnBack, 167, SpringLayout.WEST, contentPanel);
		slContentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(btnBack);
	}
	
	private void searchFlixCod() {
		
		if ( Checker.checkDigits(spFlixCod.getValue().toString()) ){
			
			Optional<Flix> optFlix = new FlixController().getFlixById( spFlixCod.getValue().toString() );
			
			
			if ( optFlix.isPresent() ) {				
				
				taFlix.setText(optFlix.get().toString());

			}else {
				
				taFlix.setText("\t" + DecoHelper.MSG_ERROR_NULL);
				
			}
			
		}
		else {
			taFlix.setText("\t" + DecoHelper.MSG_ERROR_COD);
		}
	}
}
