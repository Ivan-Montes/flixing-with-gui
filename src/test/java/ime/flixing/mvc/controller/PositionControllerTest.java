package ime.flixing.mvc.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collections;
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

import ime.flixing.dao.impl.PositionDaoImpl;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.Position;
import ime.flixing.mvc.view.PositionView;
import ime.flixing.mvc.view.position.*;
import ime.flixing.tool.Checker;


@ExtendWith(MockitoExtension.class)
class PositionControllerTest {

	@Mock
	private PositionDaoImpl positionDaoImpl;
	
	@InjectMocks
	private PositionController positionController;	
	
	private Position positionTest;
	private Long positionTestId = 1L;
	private String positionStrTestId = positionTestId.toString();
	private String positionTestName = "Best boy";
	private String positionTestDes = "Electric Boogaloo mainly";
	private List<Position> list;
	
	@BeforeEach
	private void createObjects() {
		
		positionTest = new Position();
		positionTest.setPositionId(positionTestId);
		positionTest.setName(positionTestName);
		positionTest.setDescription(positionTestDes);
		list = new ArrayList<>();
		
	}
	
	@Test
	void PositionController_initPositionController_ReturnVoid() {
		
		try(MockedConstruction<PositionView>mockView = Mockito.mockConstruction(PositionView.class)){
			
			PositionController.initPositionController();
			
			PositionView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initPositionGetAll_ReturnVoid() {
		
		try(MockedConstruction<PositionGetAllView>mockView = Mockito.mockConstruction(PositionGetAllView.class)){
			
			PositionController.initPositionGetAllView();
			
			PositionGetAllView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initPositionGetById_ReturnVoid() {
		
		try(MockedConstruction<PositionGetByIdView>mockView = Mockito.mockConstruction(PositionGetByIdView.class)){
			
			PositionController.initPositionGetByIdView();
			
			PositionGetByIdView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initPositionSave_ReturnVoid() {
		
		try(MockedConstruction<PositionSaveView>mockView = Mockito.mockConstruction(PositionSaveView.class)){
			
			PositionController.initPositionSaveView();
			
			PositionSaveView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initPositionUpdate_ReturnVoid() {
		
		try(MockedConstruction<PositionUpdateView>mockView = Mockito.mockConstruction(PositionUpdateView.class)){
			
			PositionController.initPositionUpdateView();
			
			PositionUpdateView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initPositionDelete_ReturnVoid() {
		
		try(MockedConstruction<PositionDeleteView>mockView = Mockito.mockConstruction(PositionDeleteView.class)){
			
			PositionController.initPositionDeleteView();
			
			PositionDeleteView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);	
			
		}
	}
	
	@Test
	void PositionController_getAllPosition_ReturnOptListPosition() {
			
		list.add(positionTest);
		doReturn(list).when(positionDaoImpl).getAllPosition();
		
		Optional<List<Position>>optListPos = positionController.getAllPosition();
		
		assertAll(
				()->Assertions.assertThat(optListPos).isNotNull(),
				()->Assertions.assertThat(optListPos.get()).hasSize(1),
				()->Assertions.assertThat(optListPos.get().get(0).getPositionId()).isEqualTo(positionTestId),
				()->Assertions.assertThat(optListPos.get().get(0).getName()).isEqualTo(positionTestName)
				);
		verify(positionDaoImpl,times(1)).getAllPosition();		
	
	}
	
	@Test
	void PositionController_getPositionbyId_ReturnOptPosition() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			doReturn(positionTest).when(positionDaoImpl).getPositionById(Mockito.anyLong());
			
			Optional<Position>optPos = positionController.getPositionById(positionStrTestId);
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(positionTestId),
					()->Assertions.assertThat(optPos.get().getName()).isEqualTo(positionTestName)
					);
			verify(positionDaoImpl,times(1)).getPositionById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}
	}
	
	@Test
	void PositionController_savePosition_ReturnPosition() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(true);
			doReturn( Collections.emptyList() ).when(positionDaoImpl).getPositionByNameId(Mockito.anyString());
			doReturn( positionTest ).when(positionDaoImpl).savePosition(Mockito.any(Position.class));
			
			Optional<Position>optPos = positionController.savePosition("","");
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(positionTestId),
					()->Assertions.assertThat(optPos.get().getName()).isEqualTo(positionTestName),
					()->Assertions.assertThat(optPos.get().getDescription()).isEqualTo(positionTestDes)
					);
			verify(positionDaoImpl,times(1)).getPositionByNameId(Mockito.anyString());
			verify(positionDaoImpl,times(1)).savePosition(Mockito.any(Position.class));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}
	
	@Test
	void PositionController_savePosition_ReturnPositionWithError1() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(false);
			
			Optional<Position>optPos = positionController.savePosition("","");
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}

	@Test
	void PositionController_savePosition_ReturnPositionWithError2() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(true);
			doReturn( List.of(positionTest) ).when(positionDaoImpl).getPositionByNameId(Mockito.anyString());
			
			Optional<Position>optPos = positionController.savePosition("", "");
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(-2L)
					);
			verify(positionDaoImpl,times(1)).getPositionByNameId(Mockito.anyString());
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}
	
	@Test
	void PositionController_updatePosition_ReturnPosition() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(true);
			doReturn( Collections.emptyList() ).when(positionDaoImpl).getPositionByNameId(Mockito.anyString());
			doReturn(positionTest).when(positionDaoImpl).getPositionById(Mockito.anyLong());
			doReturn(positionTest).when(positionDaoImpl).updatePosition(Mockito.anyLong(), Mockito.any(Position.class));
			
			Optional<Position>optPos = positionController.updatePosition(positionStrTestId, "",  "") ;
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(positionTestId),
					()->Assertions.assertThat(optPos.get().getName()).isEqualTo(positionTestName),
					()->Assertions.assertThat(optPos.get().getDescription()).isEqualTo(positionTestDes)
					);
			verify(positionDaoImpl,times(1)).getPositionByNameId(Mockito.anyString());
			verify(positionDaoImpl,times(1)).getPositionById(Mockito.anyLong());
			verify(positionDaoImpl,times(1)).updatePosition(Mockito.anyLong(), Mockito.any(Position.class));
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}
	
	@Test
	void PositionController_updatePosition_ReturnPositionWithError1() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(false);
			
			Optional<Position>optPos = positionController.updatePosition(positionStrTestId, "",  "") ;
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}
	

	@Test
	void PositionController_updatePosition_ReturnPositionWithError2() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(true);
			positionTest.setPositionId(33L);
			doReturn( List.of( positionTest ) ).when(positionDaoImpl).getPositionByNameId(Mockito.anyString());
			
			Optional<Position>optPos = positionController.updatePosition(positionStrTestId, "",  "") ;
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(-2L)
					);
			verify(positionDaoImpl,times(1)).getPositionByNameId(Mockito.anyString());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}

	@Test
	void PositionController_updatePosition_ReturnPositionWithError3() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkName(Mockito.anyString())).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription(Mockito.anyString())).thenReturn(true);
			doReturn( Collections.emptyList() ).when(positionDaoImpl).getPositionByNameId(Mockito.anyString());
			doReturn(null).when(positionDaoImpl).getPositionById(Mockito.anyLong());
			
			Optional<Position>optPos = positionController.updatePosition(positionStrTestId, "",  "") ;
			
			assertAll(
					()->Assertions.assertThat(optPos).isNotNull(),
					()->Assertions.assertThat(optPos.get().getPositionId()).isEqualTo(-3l)
					);
			verify(positionDaoImpl,times(1)).getPositionByNameId(Mockito.anyString());
			verify(positionDaoImpl,times(1)).getPositionById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}
	}
	
	@Test
	void PositionController_deleteposition_ReturnInt() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(positionTest).when(positionDaoImpl).getPositionByIdEagger(Mockito.anyLong());			
			doNothing().when(positionDaoImpl).deletePosition(Mockito.anyLong());			
			
			int returnValue = positionController.deletePerson(positionStrTestId);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isZero()
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			verify(positionDaoImpl,times(1)).getPositionByIdEagger(Mockito.anyLong());
			verify(positionDaoImpl,times(1)).deletePosition(Mockito.anyLong());
		}
	}

	@Test
	void PositionController_deleteposition_ReturnIntError1() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(false);
			
			int returnValue = positionController.deletePerson(positionStrTestId);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-1l)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}
	}

	@Test
	void PositionController_deleteposition_ReturnIntError3() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(null).when(positionDaoImpl).getPositionByIdEagger(Mockito.anyLong());
			
			
			int returnValue = positionController.deletePerson(positionStrTestId);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-3L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			verify(positionDaoImpl,times(1)).getPositionByIdEagger(Mockito.anyLong());
		}
	}

	@Test
	void PositionController_deleteposition_ReturnIntError4() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			positionTest.getFlixPersonPosition().add(new FlixPersonPosition());
			doReturn(positionTest).when(positionDaoImpl).getPositionByIdEagger(Mockito.anyLong());			
			
			int returnValue = positionController.deletePerson(positionStrTestId);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-4L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			verify(positionDaoImpl,times(1)).getPositionByIdEagger(Mockito.anyLong());
		}
	}

}
