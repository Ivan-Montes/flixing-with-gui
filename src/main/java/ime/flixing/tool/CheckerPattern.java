package ime.flixing.tool;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
public class CheckerPattern {
	
	public static final String CHECKERPATTERN_NAME_BASIC = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+";
	public static final String CHECKERPATTERN_DESCRIPTION_BASIC = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]+";
	public static final String CHECKERPATTERN_DESCRIPTION_MEDIUM = "[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ\\s\\-\\.&,:]{1,4}";

}
