package ime.flixing.tool;


import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
@Generated
public class DecoHelper {	
		
	public static final String MSG_VOLVER = "Enter option number (0 for back): ";
	public static final String MSG_SALIR = "Enter option number (0 for exit): ";
	public static final String MSG_ERROR_NULL = "Null object found";
	public static final String MSG_ERROR_OPT = "Wrong option";
	public static final String MSG_ERROR_COD = "Wrong code";
	public static final String MSG_ERROR_DATA = "Some data is Wrong";
	public static final String MSG_ERROR_PROCESS = "Unable to terminate the process";
	public static final String MSG_ERROR_UNEXPECTED = "Unexpected error";
	public static final String MSG_ERROR_DELETE_ASSOCIATED_ITEMS = "Some items are still associated to this entity. Impossible to delete. Please, first empty the list";
	public static final String MSG_ERROR_CHECKER = "Checker fail: Some data is incorrect";
	public static final String MSG_WRITE_COD = "Next, Write a valid code ";
	public static final String MSG_WRITE_COD_FLIX = "Please, Write a valid FLIX code ";
	public static final String MSG_WRITE_COD_PERSON = "Next, Write a valid PERSON code ";
	public static final String MSG_WRITE_COD_POSITION = "Now, Write a valid POSITION code ";
	public static final String MSG_WRITE_NAME = "Now, Write a name ";
	public static final String MSG_WRITE_SURNAME = "Now, Write a surname ";
	public static final String MSG_WRITE_COD_GENRE = "Please next Write a cod GENRE ";
	public static final String MSG_WRITE_DESCRIPTION = "Now, Write a description ";
	public static final String MSG_WRITE_NEWNAME = "Please now, Write a new name ";
	public static final String MSG_WRITE_NEWCOD_GENRE = "Write a new cod GENRE ";
	public static final String MSG_WRITE_NEWDESCRIPTION = "Now, Write a new description ";
	public static final String MSG_ASK_CONFIRMATION = "Enter Y/N to confirm: ";
	public static final String MSG_SUCCESSFULLY = "Process complete successfully";
	public static final String MSG_REGISTRY_REPEATED = "Repeated info: This registry is already in the database";
	public static final String MSG_DUPLICATED_NAME = "Repeated info: This NAME is already in the database";
	public static final String MSG_CONFIRM_OPTION = "Please, confirm option";
	public static final String MSG_CONFIRM_TITLE = "Confirmation box";
	public static final String BTN_SEARCH = "Search";
	public static final String EX_NULL = "Se ha producido una excepción de puntero nulo.";
	public static final String EX_ILLEGAL_STATE = "Se ha producido una excepción de java.lang.IllegalStateException.";
	public static final String EX_ILLEGAL_SERVICE = "Se ha producido una excepción de org.hibernate.service.spi.ServiceException.";
	public static final String EX_HIBERNATE_JDBC = "Se ha producido una excepción de org.hibernate.engine.jdbc.env.spi.JdbcEnvironment.";
	public static final String EX_CONSTRAINT_VIOLATION = "Se ha producido una excepción jakarta.validation.constraintviolation.";
	public static final String EX_UNKNOWN = "Se ha producido una excepción desconocida.";
	public static final String EX_MSG_TEMPLATE = "Error => %s  Description => %s";
	public static final String MSG_SHIT_HAPPENS = "An error has occurred";
	
	
	
	public static final String chooseResultMessageByReturnValue(Long returnValue) {
		
		String msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
		
		if ( returnValue != null ) {
			
			switch( String.valueOf( returnValue ) ) {
			
			case "-1":
				msgInfo = DecoHelper.MSG_ERROR_CHECKER;
				break;
			case "-2":
				msgInfo = DecoHelper.MSG_DUPLICATED_NAME;
				break;
			case "-3":
				msgInfo = DecoHelper.MSG_ERROR_COD;
				break;
			case "-4":
				msgInfo = DecoHelper.MSG_ERROR_DELETE_ASSOCIATED_ITEMS;
				break;
			case "-5":
				msgInfo = DecoHelper.MSG_ERROR_UNEXPECTED;
				break;
			case "-6":
				msgInfo = DecoHelper.MSG_ERROR_NULL;
				break;
			default:
				msgInfo = DecoHelper.MSG_SUCCESSFULLY;
				break;
			}
		}
		
		return msgInfo;
	}

}
