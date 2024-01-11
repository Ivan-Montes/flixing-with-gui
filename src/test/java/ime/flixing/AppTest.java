package ime.flixing;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ime.flixing.mvc.controller.MainController;
import ime.flixing.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
class AppTest 
{
	@Mock
	private SessionFactory sessionFactory;
	
	@InjectMocks
	private App app;
	
	@Test
	void App_main_ReturnVoid() {
		
		try ( MockedConstruction<App> mockApp = Mockito.mockConstruction(App.class) ){
			
			App.main(null);
			
			App app = mockApp.constructed().get(0);
			
			assertAll(
					()->Assertions.assertThat(app).isNotNull()
					);
			verify(app,times(1)).init();
		}		
	}
	
	
	@Test
	void App_init_ReturnVoid() {
		
		App ap = Mockito.mock(App.class);
		doCallRealMethod().when(ap).init();
		doNothing().when(ap).initExceptionHandler();
		doNothing().when(ap).initHibernate();
		doNothing().when(ap).initGui();
		
		ap.init();
		
		verify(ap,times(1)).init();
		verify(ap,times(1)).initExceptionHandler();
		verify(ap,times(1)).initHibernate();
		verify(ap,times(1)).initGui();
		
	}

	@Test
	@Disabled("Thread and system classes only mocked with powermockito library at end of 2023")
	void App_initExceptionHandler_ReturnVoid() {
		
		try(MockedStatic<Thread>threadMock = Mockito.mockStatic(Thread.class)){

			threadMock.when( () -> Thread.setDefaultUncaughtExceptionHandler(Mockito.any())).thenAnswer((Answer<Void>) invocation -> null);
			App ap = Mockito.spy(App.class);
			doNothing().when(ap).initExceptionHandler();
			
			ap.initExceptionHandler();
			
			verify(ap,times(1)).initExceptionHandler();
			threadMock.verify(() -> Thread.setDefaultUncaughtExceptionHandler(Mockito.any()));
		}
		
	}

	@Test
	@Disabled("Problems to mock static block inside HibernateUtil at end of 2023")
	void App_initHibernate_ReturnVoid() {
		
		try ( MockedStatic<HibernateUtil>hibernateMock = Mockito.mockStatic(HibernateUtil.class) ){
			App ap = Mockito.mock(App.class);
			//App ap =  Mockito.spy(App.class);	
			//doNothing().when(ap).initHibernate();
    		hibernateMock.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			
			ap.initHibernate();
			
			verify(ap,times(1)).initHibernate();
		    		
		}
	}

	@Test
	void App_initGui_ReturnVoid() {
		
		try ( MockedConstruction<MainController> mockMainController = Mockito.mockConstruction(MainController.class) ){

			App ap = Mockito.spy(App.class);	

			ap.initGui();
			
			MainController mainController = mockMainController.constructed().get(0);			
			assertAll(
					()->Assertions.assertThat(ap).isNotNull()
					);
			verify(mainController,times(1)).init();
			verify(ap,times(1)).initGui();
		}
	}
}
