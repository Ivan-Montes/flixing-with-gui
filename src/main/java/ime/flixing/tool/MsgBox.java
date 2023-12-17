package ime.flixing.tool;

import java.awt.Component;

import javax.swing.JOptionPane;



public class MsgBox {
	
	public void showExeptionDialog(Exception e) {
		
		JOptionPane.showMessageDialog(null, e.getMessage(), DecoHelper.MSG_SHIT_HAPPENS, JOptionPane.WARNING_MESSAGE);
		
	}

	public void showBasicDialog(Component component, String msg) {
		
		JOptionPane.showMessageDialog(component, msg);
		
	}
	
}
