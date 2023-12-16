package ime.flixing.mvc.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ime.flixing.mvc.view.MainView;



@ExtendWith(MockitoExtension.class)
class MainControllerTest {

	@Mock
	private MainView mainView;
	
	@InjectMocks
	private MainController mainController;
	
	
	@Test
	void MainController_init_ReturnVoid() {
		
		try(MockedConstruction<MainView>mockMainView = Mockito.mockConstruction(MainView.class)){						
			
			mainController.init();
			
			MainView mainConfiguration = mockMainView.constructed().get(0);			
			verify(mainConfiguration, times(1)).setVisible(true);			
			
		}
		
	}

	@Test
	void MainController_callFlixController_ReturnVoid() {

		try (MockedStatic<FlixController>mockFlixControllerStatic = Mockito.mockStatic(FlixController.class)) {
			mockFlixControllerStatic.when(() -> FlixController.initFlixController()).then( (Answer<Void>) invocation -> null);
			
			MainController.callFlixController();
			
			mockFlixControllerStatic.verify(FlixController::initFlixController);
		}
	}
	
	@Test
	void MainController_callGenreController_ReturnVoid() {
		
		try ( MockedStatic<GenreController>mock = Mockito.mockStatic(GenreController.class)){
			
			mock.when( () -> GenreController.initGenreController()).then( (Answer<Void>) invocation -> null);
			
			MainController.callGenreController();
			
			mock.verify(GenreController::initGenreController);
		}
	}

	
	@Test
	void MainController_callPersonController_ReturnVoid() {

		try ( MockedStatic<PersonController>mock = Mockito.mockStatic(PersonController.class)){
			
			mock.when( () -> PersonController.initPersonController()).then( (Answer<Void>) invocation -> null);
			
			MainController.callPersonController();
			
			mock.verify(PersonController::initPersonController);
		}
	}

	
	@Test
	void MainController_callPositionController_ReturnVoid() {

		try ( MockedStatic<PositionController>mock = Mockito.mockStatic(PositionController.class)){
			
			mock.when( () -> PositionController.initPositionController()).then( (Answer<Void>) invocation -> null);
			
			MainController.callPositionController();
			
			mock.verify(PositionController::initPositionController);
		}
	}

	
	@Test
	void MainController_callFlixPersonPositionController_ReturnVoid() {

		try ( MockedStatic<FlixPersonPositionController>mock = Mockito.mockStatic(FlixPersonPositionController.class)){
			
			mock.when( () -> FlixPersonPositionController.initFlixPersonPositionController()).then( (Answer<Void>) invocation -> null);
			
			MainController.callFlixPersonPositionController();
			
			mock.verify(FlixPersonPositionController::initFlixPersonPositionController);
		}
	}

	
	
}
