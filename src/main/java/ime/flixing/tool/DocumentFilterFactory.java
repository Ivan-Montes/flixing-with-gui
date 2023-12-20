package ime.flixing.tool;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
public class DocumentFilterFactory {

	public static final DocumentFilter createDocumentFilter(String regex) {		
        
        return new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
                if (newStr.matches(regex)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newStr.matches(regex)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
    }
	
	
	
}
