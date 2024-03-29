package ime.flixing.tool;

import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
@Generated
public class CheckerPattern {
	
	public static final String DIGITS_BASIC = "\\d+";
	public static final String TITLE_FLIX_BASIC = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\?\\¿\\!\\¡\\.&,:]+";
	public static final String TITLE_FLIX_FULL = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\?\\¿\\!\\¡\\.&,:]{1,50}";
	public static final String NAME_BASIC = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+";
	public static final String NAME_FULL = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]{1,50}";
	public static final String DESCRIPTION_BASIC = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\?\\¿\\!\\¡\\.&,:]+";
	public static final String DESCRIPTION_FULL = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\?\\¿\\!\\¡\\.&,:]{1,100}";
	public static final String SURNAME_BASIC = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+";
	public static final String SURNAME_FULL = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]{1,50}";

}
