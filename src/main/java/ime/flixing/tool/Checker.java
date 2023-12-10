package ime.flixing.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
public class Checker {

	
	public static final boolean checkDigits(String n) {	
		return ( n.matches( CheckerPattern.DIGITS_BASIC ) );
	}

	public static final boolean checkFlixTitle(String n) {
		
		Pattern pattern = Pattern.compile( CheckerPattern.TITLE_FLIX_BASIC );
		Matcher matcher = pattern.matcher(n);
		
		return matcher.matches() && (n.length() >= 1 && n.length() <= 50);
		
	}
	
	public static final boolean checkName(String str) {
		
		return str.matches( CheckerPattern.NAME_BASIC ) && str.length() <= 50;
		
	}
	
	public static final boolean checkDescription(String str) {
		
		return str.matches( CheckerPattern.DESCRIPTION_BASIC ) && str.length() <= 100;
		
	}
	

	public static final boolean checkSurname(String str) {
		
		return str.matches( CheckerPattern.SURNAME_BASIC) && str.length() <= 50;
		
	}
}
