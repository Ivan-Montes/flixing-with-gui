package ime.flixing.mvc.view.flix;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ime.flixing.entity.Flix;
import ime.flixing.mvc.controller.FlixController;
import ime.flixing.tool.Checker;
import ime.flixing.tool.DecoHelper;
import lombok.Getter;
import lombok.Setter;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@Getter
@Setter
public class FlixGetByIdView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCod;
	private JButton btnSearch;
	private JSpinner spFlixCod;
	private JScrollPane spFlix;
	private JTextArea taFlix;
	private JButton btnBack;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FlixGetByIdView dialog = new FlixGetByIdView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
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
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		lblCod = new JLabel("Flix Code");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblCod, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblCod, 22, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblCod);
		
		btnSearch = new JButton("Search");
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnSearch, 129, SpringLayout.EAST, lblCod);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchFlixCod();
				
			}
		});
		sl_contentPanel.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, lblCod);
		contentPanel.add(btnSearch);	
		
		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 33, 1);
		spFlixCod = new JSpinner(spinnerNumberModel);
		lblCod.setLabelFor(spFlixCod);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spFlixCod, -3, SpringLayout.NORTH, lblCod);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spFlixCod, 28, SpringLayout.EAST, lblCod);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spFlixCod, 138, SpringLayout.WEST, contentPanel);
		contentPanel.add(spFlixCod);
		
		spFlix = new JScrollPane();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, spFlix, 19, SpringLayout.SOUTH, btnSearch);
		sl_contentPanel.putConstraint(SpringLayout.WEST, spFlix, 0, SpringLayout.WEST, lblCod);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, spFlix, -46, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, spFlix, -21, SpringLayout.EAST, contentPanel);
		contentPanel.add(spFlix);
		
		taFlix = new JTextArea();
		taFlix.setWrapStyleWord(true);
		taFlix.setLineWrap(true);
		spFlix.setViewportView(taFlix);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		sl_contentPanel.putConstraint(SpringLayout.WEST, btnBack, 167, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnBack, -10, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(btnBack);
	}
	
	private void searchFlixCod() {
		
		if ( Checker.checkDigits(spFlixCod.getValue().toString()) ){
			
			Optional<Flix> optFlix = FlixController.searchFlixCod( spFlixCod.getValue().toString() );
			
			
			if ( optFlix.isPresent() ) {				
				
				getTaFlix().setText(optFlix.get().toString());

			}else {
				
				getTaFlix().setText("\t" + DecoHelper.MSG_NULL_ERROR);
				
			}
			
		}
		else {
			getTaFlix().setText("\t" + DecoHelper.MSG_COD_ERROR);
		}
	}
}
