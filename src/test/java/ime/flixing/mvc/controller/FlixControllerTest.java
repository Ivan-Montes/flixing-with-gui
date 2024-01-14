package ime.flixing.mvc.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import ime.flixing.dao.impl.FlixDaoImpl;
import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.FlixView;
import ime.flixing.mvc.view.flix.*;
import ime.flixing.tool.Checker;


@ExtendWith(MockitoExtension.class)
class FlixControllerTest {

	@InjectMocks
	private FlixController flixController;
	
	@Mock
	private FlixDaoImpl flixDaoImpl;
	
	@Mock
	private GenreDaoImpl genreDaoImpl;
	
	private Flix flixTest;
	private Long flixTestId = 33L;
	private String flixTestIdStr = String.valueOf(flixTestId);
	private String flixTestTitle = "Groundhog Day";
	private Genre genreTest;
	private Long genreTestId = 32L;
	private String genreTestIdStr = String.valueOf(genreTestId);
	private String genreTestName = "Dramedy";

	@BeforeEach
	private void createObjects() {
		
		flixTest = new Flix();
		flixTest.setFlixId(flixTestId);
		flixTest.setTitle(flixTestTitle);
		
		genreTest = new Genre();
		genreTest.setGenreId(genreTestId);
		genreTest.setName(genreTestName);		
		
	}
	
	@Test
	void PositionController_initFlixController_ReturnVoid() {
		
		try(MockedConstruction<FlixView>mockView = Mockito.mockConstruction(FlixView.class)){
			
			FlixController.initFlixController();
			
			FlixView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}
	

	@Test
	void PositionController_initFlixGetAllView_ReturnVoid() {
		
		try(MockedConstruction<FlixGetAllView>mockView = Mockito.mockConstruction(FlixGetAllView.class)){
			
			FlixController.initFlixGetAllView();
			
			FlixGetAllView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initFlixGetById_ReturnVoid() {
		
		try(MockedConstruction<FlixGetByIdView>mockView = Mockito.mockConstruction(FlixGetByIdView.class)){
			
			FlixController.initFlixGetByIdView();
			
			FlixGetByIdView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initFlixSaveView_ReturnVoid() {
		
		try(MockedConstruction<FlixSaveView>mockView = Mockito.mockConstruction(FlixSaveView.class)){
			
			FlixController.initFlixSaveView();
			
			FlixSaveView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}

	@Test
	void PositionController_initFlixUpdateView_ReturnVoid() {
		
		try(MockedConstruction<FlixUpdateView>mockView = Mockito.mockConstruction(FlixUpdateView.class)){
			
			FlixController.initFlixUpdateView();
			
			FlixUpdateView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}	


	@Test
	void PositionController_initFlixDeleteView_ReturnVoid() {
		
		try(MockedConstruction<FlixDeleteView>mockView = Mockito.mockConstruction(FlixDeleteView.class)){
			
			FlixController.initFlixDeleteView();
			
			FlixDeleteView view = mockView.constructed().get(0);
			verify(view,times(1)).setVisible(true);
			
		}		
	}
	
	
	@Test
	void PositionController_getAllFlix_ReturnOptListFlix() {
		
		doReturn(List.of(flixTest)).when(flixDaoImpl).getAll();
		
		Optional<List<Flix>>optListFlix = flixController.getAllFlix();
		
		assertAll(
				()->Assertions.assertThat(optListFlix).isNotNull(),
				()->Assertions.assertThat(optListFlix.get()).hasSize(1),
				()->Assertions.assertThat(optListFlix.get().get(0).getFlixId()).isEqualTo(flixTestId),
				()->Assertions.assertThat(optListFlix.get().get(0).getTitle()).isEqualTo(flixTestTitle)
				);
		verify(flixDaoImpl,times(1)).getAll();		
		
		
	}
	
	

	@Test
	void PositionController_getFlixById_ReturnOptFlix() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			doReturn(flixTest).when(flixDaoImpl).getById(Mockito.anyLong());
			
			Optional<Flix>optFlix = flixController.getFlixById(flixTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(optFlix).isNotNull(),
					()->Assertions.assertThat(optFlix.get().getFlixId()).isEqualTo(flixTestId),
					()->Assertions.assertThat(optFlix.get().getTitle()).isEqualTo(flixTestTitle)
					);
			verify(flixDaoImpl,times(1)).getById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}		
	}

	@Test
	void PositionController_getFlixById_ReturnOptNull() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(false);
			
			Optional<Flix>optFlix = flixController.getFlixById(flixTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(optFlix).isNotNull(),
					()->assertFalse( optFlix.isPresent() )
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}		
	}
	
	@Test
	void PositionController_saveFlix_ReturnOptFlix() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkFlixTitle(Mockito.anyString()) ).thenReturn(true);
			doReturn(genreTest).when(genreDaoImpl).getById(Mockito.anyLong());
			flixTest.setGenre(genreTest);
			doReturn(flixTest).when(flixDaoImpl).save(Mockito.any(Flix.class));
			
			Optional<Flix>optFlix = flixController.saveFlix(Mockito.anyString(), genreTestIdStr);

			assertAll(
					()->Assertions.assertThat(optFlix).isNotNull(),
					()->Assertions.assertThat(optFlix.get().getTitle()).isEqualTo(flixTestTitle),
					()->Assertions.assertThat(optFlix.get().getGenre().getGenreId()).isEqualTo(genreTestId)
					);
			verify(genreDaoImpl,times(1)).getById(Mockito.anyLong());
			verify(flixDaoImpl,times(1)).save(Mockito.any(Flix.class));
			mockChecker.verify(() -> Checker.checkFlixTitle(Mockito.anyString()), times(1));	
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));	
		}
		
	}	
		

	@Test
	void PositionController_updateFlix_ReturnOptFlix() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkFlixTitle(Mockito.anyString()) ).thenReturn(true);	
			doReturn(genreTest).when(genreDaoImpl).getById(Mockito.anyLong());
			flixTest.setGenre(genreTest);
			doReturn(flixTest).when(flixDaoImpl).update( Mockito.anyLong(), Mockito.any(Flix.class) );
			
			Optional<Flix>optFlix = flixController.updateFlix(genreTestIdStr, "", flixTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(optFlix).isNotNull(),
					()->Assertions.assertThat(optFlix.get().getTitle()).isEqualTo(flixTestTitle),
					()->Assertions.assertThat(optFlix.get().getGenre().getGenreId()).isEqualTo(genreTestId)
					);
			verify(genreDaoImpl,times(1)).getById(Mockito.anyLong());
			verify(flixDaoImpl,times(1)).update( Mockito.anyLong(), Mockito.any(Flix.class) );
			mockChecker.verify(() -> Checker.checkFlixTitle(Mockito.anyString()), times(1));	
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(2));	
		}		
	}		
	

	@Test
	void PositionController_deleteFlix_ReturnInt() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			doReturn(flixTest).when(flixDaoImpl).getByIdEagger(Mockito.anyLong());
			
			int returnValue = flixController.deleteFlix(flixTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isZero()
					);
			verify(flixDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
			verify(flixDaoImpl,times(1)).delete(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}
	}

	@Test
	void PositionController_deleteFlix_ReturnIntError2() {
		
		try (MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			flixTest.getFlixPersonPosition().add(new FlixPersonPosition());
			doReturn(flixTest).when(flixDaoImpl).getByIdEagger(Mockito.anyLong());
			
			int returnValue = flixController.deleteFlix(flixTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-2L)
					);
			verify(flixDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}
	}
	
	
}
		