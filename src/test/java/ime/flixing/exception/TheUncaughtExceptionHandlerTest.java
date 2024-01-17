package ime.flixing.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ime.flixing.tool.MsgBox;


@ExtendWith(MockitoExtension.class)
class TheUncaughtExceptionHandlerTest {
	
	@InjectMocks
	private TheUncaughtExceptionHandler miniChefHandler;
	
	@Mock
	private MsgBox msgBoxM;
	
	@Mock
	private Logger loggerM;
	
	
	private Thread thread;	
	
	
	@Test
	void UncaughtExceptionHandler_Null_ReturnVoid() {
		
		try(MockedStatic<Logger>mockLogger = Mockito.mockStatic(Logger.class)){
			
			Logger loggerFake = Mockito.mock(Logger.class);
			mockLogger.when( () -> Logger.getLogger(Mockito.anyString())).thenReturn(loggerFake);
			doNothing().when(loggerFake).log(Mockito.any(Level.class), Mockito.anyString());
			thread = new Thread( () -> { throw new NullPointerException(); } );
			TheUncaughtExceptionHandler miniHandler = new TheUncaughtExceptionHandler();
			thread.setUncaughtExceptionHandler(miniHandler);
			
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				
			}
				assertAll(
						()->Assertions.assertThat(thread).isNotNull()
						);				
			verify(loggerFake, times(1)).log(Mockito.any(Level.class), Mockito.anyString());
				
		}
	}
	

	@Test
	void UncaughtExceptionHandler_IllegalState_ReturnVoid() {
		
		try(MockedStatic<Logger>mockLogger = Mockito.mockStatic(Logger.class)){
			
			Logger loggerFake = Mockito.mock(Logger.class);
			mockLogger.when( () -> Logger.getLogger(Mockito.anyString())).thenReturn(loggerFake);
			doNothing().when(loggerFake).log(Mockito.any(Level.class), Mockito.anyString());
			thread = new Thread( () -> { throw new java.lang.IllegalStateException(); } );
			TheUncaughtExceptionHandler miniHandler = new TheUncaughtExceptionHandler();
			thread.setUncaughtExceptionHandler(miniHandler);
			
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				
			}
			
				assertAll(
						()->Assertions.assertThat(thread).isNotNull()
						);				
			verify(loggerFake, times(1)).log(Mockito.any(Level.class), Mockito.anyString());
				
		}
	}

	@Test
	void UncaughtExceptionHandler_ServiceException_ReturnVoid() {
		
		doNothing().when(loggerM).log(Mockito.any(Level.class), Mockito.anyString());
		thread = new Thread( () -> { throw new org.hibernate.service.spi.ServiceException("How exception"); } );
		thread.setUncaughtExceptionHandler(miniChefHandler);
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			
		}
		
			assertAll(
					()->Assertions.assertThat(thread).isNotNull()
					);				
		verify(loggerM, times(1)).log(Mockito.any(Level.class), Mockito.anyString());
			
	}	

	
	@Test
	void UncaughtExceptionHandler_ConstraintViolation_ReturnVoid() {
			
			doNothing().when(loggerM).log(Mockito.any(Level.class), Mockito.anyString());			
			doNothing().when(msgBoxM).showExeptionDialog(Mockito.any());			
			thread = new Thread( () -> { throw new jakarta.validation.ConstraintViolationException(null); } );
			thread.setUncaughtExceptionHandler(miniChefHandler);
			
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				
			}
			
				assertAll(
						()->Assertions.assertThat(thread).isNotNull()
						);				
			verify(loggerM, times(1)).log(Mockito.any(Level.class), Mockito.anyString());
			verify(msgBoxM, times(1)).showExeptionDialog(Mockito.any());		
	}	
	

	@Test
	void UncaughtExceptionHandler_Others_ReturnVoid() {

		doNothing().when(loggerM).log(Mockito.any(Level.class), Mockito.anyString());			
		thread = new Thread( () -> { throw new RuntimeException(); } );
		thread.setUncaughtExceptionHandler(miniChefHandler);
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			
		}
		
			assertAll(
					()->Assertions.assertThat(thread).isNotNull()
					);				
		verify(loggerM, times(1)).log(Mockito.any(Level.class), Mockito.anyString());
				
	}	
	
	

}
