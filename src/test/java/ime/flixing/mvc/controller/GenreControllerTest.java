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

import ime.flixing.dao.impl.GenreDaoImpl;
import ime.flixing.entity.Flix;
import ime.flixing.entity.Genre;
import ime.flixing.mvc.view.GenreView;
import ime.flixing.mvc.view.genre.*;
import ime.flixing.tool.Checker;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

	@Mock
	private GenreDaoImpl genreDaoImpl;
	
	@InjectMocks
	private GenreController genreController;
	
	private Genre genreTest;
	private Long genreTestId = 1L;
	private String genreTestIdStr = genreTestId.toString();
	private String genreTestName = "Dramedy";
	private String genreTestDes = "Laughts and cries";

	@BeforeEach
	private void createObjects() {
		genreTest = new Genre();
		genreTest.setGenreId(genreTestId);
		genreTest.setName(genreTestName);
		genreTest.setDescription(genreTestDes);
	}
	
	@Test
	void GenreController_initGenreController_ReturnVoid() {
		
		try( MockedConstruction<GenreView>mockView = Mockito.mockConstruction(GenreView.class) ){
			
			GenreController.initGenreController();
			
			GenreView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}
	

	@Test
	void GenreController_initGenreDeleteView_ReturnVoid() {
		
		try( MockedConstruction<GenreDeleteView>mockView = Mockito.mockConstruction(GenreDeleteView.class) ){
			
			GenreController.initGenreDeleteView();
			
			GenreDeleteView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}

	@Test
	void GenreController_initGenreGetAllView_ReturnVoid() {
		
		try( MockedConstruction<GenreGetAllView>mockView = Mockito.mockConstruction(GenreGetAllView.class) ){
			
			GenreController.initGenreGetAllView();
			
			GenreGetAllView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}

	@Test
	void GenreController_initGenreGetByIdView_ReturnVoid() {
		
		try( MockedConstruction<GenreGetByIdView>mockView = Mockito.mockConstruction(GenreGetByIdView.class) ){
			
			GenreController.initGenreGetByIdView();
			
			GenreGetByIdView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}

	@Test
	void GenreController_initGenreSaveView_ReturnVoid() {
		
		try( MockedConstruction<GenreSaveView>mockView = Mockito.mockConstruction(GenreSaveView.class) ){
			
			GenreController.initGenreSaveView();
			
			GenreSaveView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}

	@Test
	void GenreController_initGenreUpdateView_ReturnVoid() {
		
		try( MockedConstruction<GenreUpdateView>mockView = Mockito.mockConstruction(GenreUpdateView.class) ){
			
			GenreController.initGenreUpdateView();
			
			GenreUpdateView view = mockView.constructed().get(0);
			assertAll(
					()->Assertions.assertThat(view).isNotNull()
					);
			verify(view, times(1)).setVisible(true);
		}
	}
	
	@Test
	void GenreController_getAllGenre_ReturnOptListGenre() {
		
		doReturn(List.of(genreTest)).when(genreDaoImpl).getAll();
		
		Optional<List<Genre>>optListGen = genreController.getAllGenre();
		
		assertAll(
				()->Assertions.assertThat(optListGen).isNotNull(),
				()->Assertions.assertThat(optListGen.get()).hasSize(1),
				()->Assertions.assertThat(optListGen.get().get(0).getGenreId()).isEqualTo(genreTestId),
				()->Assertions.assertThat(optListGen.get().get(0).getName()).isEqualTo(genreTestName)
				);
		verify(genreDaoImpl,times(1)).getAll();		
	}
	

	@Test
	void GenreController_getGenreById_ReturnOptGenre() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(true);
			doReturn(genreTest).when(genreDaoImpl).getById( Mockito.anyLong() );
			
			Optional<Genre>optGen = genreController.getGenreById( genreTestIdStr );
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(genreTestId),
					()->Assertions.assertThat(optGen.get().getName()).isEqualTo(genreTestName)
					);
			verify(genreDaoImpl,times(1)).getById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}		
	}
	

	@Test
	void GenreController_saveGenre_ReturnOptGenre() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(true);
			doReturn( List.of() ).when(genreDaoImpl).getByName(Mockito.anyString());
			doReturn(genreTest).when(genreDaoImpl).save(Mockito.any(Genre.class));
			
			Optional<Genre>optGen = genreController.saveGenre(genreTestIdStr, "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(genreTestId),
					()->Assertions.assertThat(optGen.get().getName()).isEqualTo(genreTestName),
					()->Assertions.assertThat(optGen.get().getDescription()).isEqualTo(genreTestDes)
					);
			verify(genreDaoImpl,times(1)).getByName(Mockito.anyString());
			verify(genreDaoImpl,times(1)).save(Mockito.any(Genre.class));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}		
	}

		
	@Test
	void GenreController_saveGenre_ReturnError2() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(false);
			
			Optional<Genre>optGen = genreController.saveGenre(genreTestIdStr, "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-2L)
					);
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}		
	}

	
	@Test
	void GenreController_saveGenre_ReturnError1() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(true);
			doReturn( List.of(new Genre()) ).when(genreDaoImpl).getByName(Mockito.anyString());
			
			Optional<Genre>optGen = genreController.saveGenre(genreTestIdStr, "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-1L)
					);
			verify(genreDaoImpl,times(1)).getByName(Mockito.anyString());
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}		
	}

	@Test
	void GenreController_updateGenre_ReturnOptGenre() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(true);
			doReturn( List.of() ).when(genreDaoImpl).getByName(Mockito.anyString());
			doReturn(genreTest).when(genreDaoImpl).update(Mockito.anyLong(), Mockito.any(Genre.class));
			
			Optional<Genre>optGen = genreController.updateGenre(genreTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(genreTestId),
					()->Assertions.assertThat(optGen.get().getName()).isEqualTo(genreTestName),
					()->Assertions.assertThat(optGen.get().getDescription()).isEqualTo(genreTestDes)
					);
			verify(genreDaoImpl,times(1)).getByName(Mockito.anyString());
			verify(genreDaoImpl,times(1)).update(Mockito.anyLong(), Mockito.any(Genre.class));
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}	
	}
	
	@Test
	void GenreController_updateGenre_ReturnError1() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(true);
			doReturn( List.of(new Genre()) ).when(genreDaoImpl).getByName(Mockito.anyString());
			
			Optional<Genre>optGen = genreController.updateGenre(genreTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-1L)
					);
			verify(genreDaoImpl,times(1)).getByName(Mockito.anyString());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}	
	}
	
	@Test
	void GenreController_updateGenre_ReturnError2a() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkDescription( Mockito.anyString()) ).thenReturn(false);
			
			Optional<Genre>optGen = genreController.updateGenre(genreTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-2L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkDescription(Mockito.anyString()), times(1));
		}	
	}

	
	@Test
	void GenreController_updateGenre_ReturnError2b() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkName( Mockito.anyString()) ).thenReturn(false);
			
			Optional<Genre>optGen = genreController.updateGenre(genreTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-2L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));
		}	
	}

	@Test
	void GenreController_updateGenre_ReturnError2c() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits( Mockito.anyString()) ).thenReturn(false);
			
			Optional<Genre>optGen = genreController.updateGenre(genreTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optGen).isNotNull(),
					()->Assertions.assertThat(optGen.get().getGenreId()).isEqualTo(-2L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}	
	}

	@Test
	void GenreController_deleteGenre_ReturnInt() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			doReturn(genreTest).when(genreDaoImpl).getByIdEagger(Mockito.anyLong());			
			doNothing().when(genreDaoImpl).delete(Mockito.anyLong());	
			
			int returnValue = genreController.deleteGenre(genreTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isZero()
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			verify(genreDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
			verify(genreDaoImpl,times(1)).delete(Mockito.anyLong());
		}	
	}

	@Test
	void GenreController_deleteGenre_ReturnIntError2() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(true);
			genreTest.getFlixes().add(new Flix());
			doReturn(genreTest).when(genreDaoImpl).getByIdEagger(Mockito.anyLong());
			
			int returnValue = genreController.deleteGenre(genreTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-2L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			verify(genreDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
		}	
	}

	@Test
	void GenreController_deleteGenre_ReturnIntError1() {
		
		try(MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class)){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString())).thenReturn(false);
			
			int returnValue = genreController.deleteGenre(genreTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-1L)
					);
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}	
	}
	
	
}
