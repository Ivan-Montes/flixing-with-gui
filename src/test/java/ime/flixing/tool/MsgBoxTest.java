package ime.flixing.tool;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class MsgBoxTest {
	
	
	@Test
	void MsgBox_showExeptionDialog_ReturnVoid() {
		try( MockedStatic<JOptionPane>mockChecker = Mockito.mockStatic(JOptionPane.class) ) {
			
			MsgBox msgBox = Mockito.spy(MsgBox.class);
			
			msgBox.showExeptionDialog(new Exception("Error faltal"));
			
			verify(msgBox, times(1)).showExeptionDialog(Mockito.any(Exception.class));
		}
	}
	
	@Test
	void MsgBox_showBasicDialog_ReturnVoid() {
		try( MockedStatic<JOptionPane>mockChecker = Mockito.mockStatic(JOptionPane.class) ) {
			
			MsgBox msgBox = Mockito.spy(MsgBox.class);
			
			msgBox.showBasicDialog(null, "");
			
			verify(msgBox, times(1)).showBasicDialog(Mockito.isNull(),Mockito.anyString());
		}
	}
	
	

}
