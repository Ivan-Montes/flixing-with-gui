package ime.flixing.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ime.flixing.tool.DecoHelper;

public class TheUncaughtExceptionHandler implements UncaughtExceptionHandler {
	
	private final Logger logger = Logger.getLogger(getClass().getName());

	@Override
    public void uncaughtException(Thread t, Throwable e) {
		
        if (e instanceof NullPointerException nullE) {
            handleNullPointerException( nullE );
            
        } else if (e instanceof java.lang.IllegalStateException illegalE) {
            handleIllegalStateException( illegalE );
            
        } else if (e instanceof org.hibernate.service.spi.ServiceException serviceE) {
            handleIllegalServiceException( serviceE );
            
        }else if (e instanceof org.hibernate.engine.jdbc.env.spi.JdbcEnvironment jdbcE) {
            handleJdbcEnvironmentException( jdbcE );
        }
        else {
            handleGenericException(e);
        }
    }

    private void handleNullPointerException(NullPointerException e) {
        logger.log(Level.SEVERE, String.format("Error: {0}.  Description: {1}", DecoHelper.EX_NULL, e.getMessage() ) );
    }

    private void handleIllegalStateException(java.lang.IllegalStateException e) {
        logger.log(Level.SEVERE, String.format("Error: {0}.  Description: {1}", DecoHelper.EX_ILLEGAL_STATE, e.getMessage() ) );
    }

    private void handleIllegalServiceException(org.hibernate.service.spi.ServiceException e) {
        logger.log(Level.SEVERE, String.format("Error: {0}.  Description: {1}", DecoHelper.EX_ILLEGAL_SERVICE, e.getMessage() ) );
    }

    private void handleJdbcEnvironmentException(org.hibernate.engine.jdbc.env.spi.JdbcEnvironment e) {
        logger.log(Level.SEVERE, String.format("Error: {0}.  Description: {1}", DecoHelper.EX_HIBERNATE_JDBC, ((Throwable) e).getMessage() ) );
    }

    private void handleGenericException(Throwable e) {
        logger.log(Level.SEVERE, String.format("Error: {0}.  Description: {1}", DecoHelper.EX_UNKNOWN, e.getMessage() ) );
    }

}
