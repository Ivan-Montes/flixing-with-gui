package ime.flixing.mvc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.flixing.dao.FlixPersonPositionDao;
import ime.flixing.dao.impl.*;
import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.FlixPersonPositionId;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.mvc.view.FlixPersonPositionView;
import ime.flixing.mvc.view.flix_person_position.*;
import ime.flixing.tool.Checker;

@ExtendWith(MockitoExtension.class)
class FlixPersonPositionControllerTest {
	
	@Mock
	private FlixPersonPositionDao flixPersonPositionDaoImpl;
	
	@Mock
	private FlixDaoImpl flixDao;
	
	@Mock	
	private PersonDaoImpl personDao;
	
	@Mock
	private PositionDaoImpl positionDao;
	
	@InjectMocks
	private FlixPersonPositionController controller;	
	
	private FlixPersonPosition flixPersonPositionTest;
	private FlixPersonPositionId flixPersonPositionTestId;
	private Long genericId = 33L;
	private String genericIdStr = genericId.toString();
	private Flix flixTest;
	private Person personTest;
	private Position positionTest;
	
	@BeforeEach
	private void createObjects() {
		
		flixTest = new Flix();
		personTest = new Person();
		positionTest = new Position();
		flixPersonPositionTestId = new FlixPersonPositionId(genericId,genericId,genericId);
		flixPersonPositionTest = new FlixPersonPosition();
		flixPersonPositionTest.setId(flixPersonPositionTestId);
		flixPersonPositionTest.setFlix(flixTest);
		flixPersonPositionTest.setPerson(personTest);
		flixPersonPositionTest.setPosition(positionTest);
		
	}
	
	
	@Test
	void PersonController_initPersonController_ReturnVoid() {
		
		try(MockedConstruction<FlixPersonPositionView>mockView = Mockito.mockConstruction(FlixPersonPositionView.class)){
			
			FlixPersonPositionController.initFlixPersonPositionController();
			
			FlixPersonPositionView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
		
		}
	}
	

	@Test
	void PersonController_initFlixPersonPositionEditView_ReturnVoid() {
		
		try(MockedConstruction<FlixPersonPositionEditView>mockView = Mockito.mockConstruction(FlixPersonPositionEditView.class)){
			
			FlixPersonPositionController.initFlixPersonPositionEditView();
			
			FlixPersonPositionEditView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
		
		}
	}

	@Test
	void PersonController_initFlixPersonPositionSaveView_ReturnVoid() {
		
		try(MockedConstruction<FlixPersonPositionSaveView>mockView = Mockito.mockConstruction(FlixPersonPositionSaveView.class)){
			
			FlixPersonPositionController.initFlixPersonPositionSaveView();
			
			FlixPersonPositionSaveView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
		
		}
	}
	
	@Test
	void PersonController_initFlixPersonPositionDetailsView_ReturnVoid() {
		
		try(MockedConstruction<FlixPersonPositionDetailsView>mockView = Mockito.mockConstruction(FlixPersonPositionDetailsView.class)){
			
			doReturn(flixPersonPositionTest).when(flixPersonPositionDaoImpl).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			
			controller.initFlixPersonPositionDetailsView(genericIdStr, genericIdStr, genericIdStr);
			
			FlixPersonPositionDetailsView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
		}		
	}
	
	@Test
	void PersonController_saveFlixPersonPosition_ReturnOpt() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(flixTest).when(flixDao).getById(Mockito.anyLong());
			doReturn(personTest).when(personDao).getById(Mockito.anyLong());
			doReturn(positionTest).when(positionDao).getById(Mockito.anyLong());
			doReturn(null).when(flixPersonPositionDaoImpl).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			doReturn(flixPersonPositionTest).when(flixPersonPositionDaoImpl).saveFlixPersonPosition(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			
			Optional<FlixPersonPosition>optFlixPersonPosition = controller.saveFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(optFlixPersonPosition).isNotNull(),
					()->Assertions.assertThat(optFlixPersonPosition.get()).isNotNull(),
					()->Assertions.assertThat(optFlixPersonPosition.get().getId()).isEqualTo(flixPersonPositionTestId)
					);
			verify(flixDao, times(1)).getById(Mockito.anyLong());
			verify(personDao, times(1)).getById(Mockito.anyLong());
			verify(positionDao, times(1)).getById(Mockito.anyLong());
			verify(flixPersonPositionDaoImpl, times(1)).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			verify(flixPersonPositionDaoImpl, times(1)).saveFlixPersonPosition(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}

	@Test
	void PersonController_saveFlixPersonPosition_ReturnOptEmpty() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(false);
			
			Optional<FlixPersonPosition>optFlixPersonPosition = controller.saveFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(optFlixPersonPosition).isNotNull(),
					()->Assertions.assertThat(optFlixPersonPosition).isEmpty()
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}
	
	@Test
	void PersonController_deleteFlixPersonPosition_ReturnInt() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(flixTest).when(flixDao).getById(Mockito.anyLong());
			doReturn(personTest).when(personDao).getById(Mockito.anyLong());
			doReturn(positionTest).when(positionDao).getById(Mockito.anyLong());
			doReturn(flixPersonPositionTest).when(flixPersonPositionDaoImpl).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			doNothing().when(flixPersonPositionDaoImpl).deleteFlixPersonPosition(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isZero()
					);
			verify(flixDao, times(1)).getById(Mockito.anyLong());
			verify(personDao, times(1)).getById(Mockito.anyLong());
			verify(positionDao, times(1)).getById(Mockito.anyLong());
			verify(flixPersonPositionDaoImpl, times(1)).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			verify(flixPersonPositionDaoImpl, times(1)).deleteFlixPersonPosition(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}
	
	@Test
	void PersonController_deleteFlixPersonPosition_ReturnIntError1a() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true, true, false);
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}	

	@Test
	void PersonController_deleteFlixPersonPosition_ReturnIntError1b() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true, false);
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(2));
		}
	}	
	

	@Test
	void PersonController_deleteFlixPersonPosition_ReturnIntError1c() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(false);
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}
	}	

	@Test
	void PersonController_deleteFlixPersonPosition_ReturnIntError6() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(flixTest).when(flixDao).getById(Mockito.anyLong());
			doReturn(personTest).when(personDao).getById(Mockito.anyLong());
			doReturn(null).when(positionDao).getById(Mockito.anyLong());
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-6L)
					);
			verify(flixDao, times(1)).getById(Mockito.anyLong());
			verify(personDao, times(1)).getById(Mockito.anyLong());
			verify(positionDao, times(1)).getById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}

	@Test
	void PersonController_deleteFlixPersonPosition_ReturnIntError3() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(flixTest).when(flixDao).getById(Mockito.anyLong());
			doReturn(personTest).when(personDao).getById(Mockito.anyLong());
			doReturn(positionTest).when(positionDao).getById(Mockito.anyLong());
			doReturn(null).when(flixPersonPositionDaoImpl).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			
			int returnValue = controller.deleteFlixPersonPosition(genericIdStr, genericIdStr, genericIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-3L)
					);
			verify(flixDao, times(1)).getById(Mockito.anyLong());
			verify(personDao, times(1)).getById(Mockito.anyLong());
			verify(positionDao, times(1)).getById(Mockito.anyLong());
			verify(flixPersonPositionDaoImpl, times(1)).getFlixPersonPositionById(Mockito.anyLong(),Mockito.anyLong(),Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(3));
		}
	}
	
	@Test
	void PersonController_getAllFlixPersonPosition_OptList() {
		
		doReturn(List.of(flixPersonPositionTest)).when(flixPersonPositionDaoImpl).getAllFlixPersonPosition();
		
		Optional<List<FlixPersonPosition>>optList = controller.getAllFlixPersonPosition();
		
		assertAll(
				()->Assertions.assertThat(optList).isNotNull(),
				()->Assertions.assertThat(optList.get()).hasSize(1)
				);
		verify(flixPersonPositionDaoImpl,times(1)).getAllFlixPersonPosition();		
		
	}
	
}
