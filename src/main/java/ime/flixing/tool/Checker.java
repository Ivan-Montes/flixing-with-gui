package ime.flixing.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
public class Checker {

	
	public static final boolean checkDigits(String n) {	
		return ( n.matches("\\d+") );
	}

	public static final boolean checkFlixTitle(String n) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\?\\¿\\!\\¡\\.&,:]+");
		Matcher matcher = pattern.matcher(n);
		
		return matcher.matches() && (n.length() >= 1 && n.length() <= 50);
		
	}

	public static final boolean checkConfirmation(String n) {
		
		return n.matches("[yY]");
		
	}
	
	public static final boolean checkName(String str) {
		
		return str.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+") && str.length() <= 50;
		
	}
	
	public static final boolean checkDescription(String str) {
		
		return str.matches("[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+") && str.length() <= 100;
		
	}
	

	public static final boolean checkSurname(String str) {
		
		return str.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+") && str.length() <= 50;
		
	}
}
