package ime.flixing.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import ime.flixing.tool.DecoHelper;
import ime.flixing.tool.MsgBox;

public final class TheUncaughtExceptionHandler implements UncaughtExceptionHandler {
	
	private final MsgBox msgBox;
	private final Logger logger;
	
	
	public TheUncaughtExceptionHandler() {
		super();
		this.msgBox = new MsgBox();
		this.logger = Logger.getLogger(getClass().getName());
	}


	public TheUncaughtExceptionHandler(MsgBox msgBox, Logger logger) {
		super();
		this.msgBox = msgBox;
		this.logger = logger;
	}

	@Override
    public final void uncaughtException(Thread t, Throwable e) {
		
		String msg = "";
		
        if (e instanceof NullPointerException ex) {
        	
        	msg = String.format(DecoHelper.EX_MSG_TEMPLATE, DecoHelper.EX_NULL, ex.getMessage());
            
        } else if (e instanceof java.lang.IllegalStateException ex) {
        	
        	msg = String.format(DecoHelper.EX_MSG_TEMPLATE, DecoHelper.EX_ILLEGAL_STATE, ex.getMessage());
            
        } else if (e instanceof org.hibernate.service.spi.ServiceException ex) {
        	
        	msg = String.format(DecoHelper.EX_MSG_TEMPLATE, DecoHelper.EX_ILLEGAL_SERVICE, ex.getMessage());
            
        }else if (e instanceof jakarta.validation.ConstraintViolationException ex) {
        	
        	msg = String.format(DecoHelper.EX_MSG_TEMPLATE, DecoHelper.EX_CONSTRAINT_VIOLATION, ex.getMessage());
        	msgBox.showExeptionDialog(ex);
        }
        else {
        	
        	msg = String.format(DecoHelper.EX_MSG_TEMPLATE, DecoHelper.EX_UNKNOWN, e.getMessage());
        }
        
        
        logger.log(Level.SEVERE, msg );
    }

}
