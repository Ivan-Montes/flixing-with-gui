package ime.flixing.mvc.controller;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
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
import ime.flixing.dao.impl.PersonDaoImpl;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.Person;
import ime.flixing.mvc.view.PersonView;
import ime.flixing.mvc.view.person.*;
import ime.flixing.tool.Checker;


@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

	@Mock
	private PersonDaoImpl personDaoImpl;
	
	@InjectMocks
	private PersonController personController;
	
	@Mock
	private Person personTest;
	
	private Long personTestId = 1L;
	private String personTestIdStr = String.valueOf(personTestId);
	private String personTestName = "Makuly";
	private String personTestSurname = "Culkin";
	private List<Person> personList;
	
	@BeforeEach
	private void createObjects() {
		
		personTest = new Person();
		personTest.setPersonId(personTestId);
		personTest.setName(personTestName);
		personTest.setSurname(personTestSurname);
		personList = new ArrayList<>();
	}
	
	@Test
	void PersonController_initPersonController_ReturnVoid() {
		
		try(MockedConstruction<PersonView>mockPersonView = Mockito.mockConstruction(PersonView.class)){
			
			PersonController.initPersonController();
			
			PersonView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}
		
	}

	@Test
	void PersonController_initPersonDelete_ReturnVoid() {
		
		try(MockedConstruction<PersonDeleteView>mockPersonView = Mockito.mockConstruction(PersonDeleteView.class)){
			
			PersonController.initPersonDeleteView();
			
			PersonDeleteView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}
		
	}

	@Test
	void PersonController_initPersonGetAll_ReturnVoid() {
		
		try(MockedConstruction<PersonGetAllView>mockPersonView = Mockito.mockConstruction(PersonGetAllView.class)){
			
			PersonController.initPersonGetAllView();
			
			PersonGetAllView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}
		
	}

	@Test
	void PersonController_initPersonGetById_ReturnVoid() {
		
		try(MockedConstruction<PersonGetByIdView>mockPersonView = Mockito.mockConstruction(PersonGetByIdView.class)){
			
			PersonController.initPersonGetByIdView();
			
			PersonGetByIdView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}
		
	}

	@Test
	void PersonController_initPersonSave_ReturnVoid() {
		
		try(MockedConstruction<PersonSaveView>mockPersonView = Mockito.mockConstruction(PersonSaveView.class)){
			
			PersonController.initPersonSaveView();
			
			PersonSaveView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}
		
	}

	@Test
	void PersonController_initPersonUpdate_ReturnVoid() {
		
		try(MockedConstruction<PersonUpdateView>mockPersonView = Mockito.mockConstruction(PersonUpdateView.class)){
			
			PersonController.initPersonUpdateView();
			
			PersonUpdateView personView = mockPersonView.constructed().get(0);
			verify(personView,times(1)).setVisible(true);
			
		}		
	}
	
	@Test
	void PersonController_getAllPerson_ReturnOptListPerson() {
		
		personList.add(personTest);
		doReturn(personList).when(personDaoImpl).getAll();
		
		Optional<List<Person>>optListPerson = personController.getAllPerson();
		
		assertAll(
				()->Assertions.assertThat(optListPerson).isNotNull(),
				()->Assertions.assertThat(optListPerson.get()).hasSize(1),
				()->Assertions.assertThat(optListPerson.get().get(0).getPersonId()).isEqualTo(personTestId),
				()->Assertions.assertThat(optListPerson.get().get(0).getName()).isEqualTo(personTestName)
				);
		verify(personDaoImpl,times(1)).getAll();		
		
	}
	
	@Test
	void PersonController_getPersonById_ReturnPerson() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class) ){
			
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			doReturn(personTest).when(personDaoImpl).getById(Mockito.anyLong());
			
			Optional<Person>optPerson = personController.getPersonById(personTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(optPerson).isNotNull(),
					()->Assertions.assertThat(optPerson.get().getPersonId()).isEqualTo(personTestId),
					()->Assertions.assertThat(optPerson.get().getName()).isEqualTo(personTestName)
					);
			verify(personDaoImpl,times(1)).getById(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}		
	}
	
	
	@Test
	void PersonController_savePerson_ReturnPerson() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class) ){
			
			mockChecker.when( () -> Checker.checkName(Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkSurname(Mockito.anyString()) ).thenReturn(true);
			doReturn(personTest).when(personDaoImpl).save(Mockito.any(Person.class));
			
			Optional<Person>optPerson = personController.savePerson("","");
			
			assertAll(
					()->Assertions.assertThat(optPerson).isNotNull(),
					()->Assertions.assertThat(optPerson.get().getPersonId()).isEqualTo(personTestId),
					()->Assertions.assertThat(optPerson.get().getName()).isEqualTo(personTestName),
					()->Assertions.assertThat(optPerson.get().getSurname()).isEqualTo(personTestSurname)
					);
			verify(personDaoImpl,times(1)).save(Mockito.any(Person.class));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));			
			mockChecker.verify(() -> Checker.checkSurname(Mockito.anyString()), times(1));			
			}		
	}
	
	@Test
	void PersonController_updatePerson_ReturnPerson() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class) ){
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkName(Mockito.anyString()) ).thenReturn(true);
			mockChecker.when( () -> Checker.checkSurname(Mockito.anyString()) ).thenReturn(true);
			doReturn(personTest).when(personDaoImpl).getById(Mockito.anyLong());
			doReturn(personTest).when(personDaoImpl).update(Mockito.anyLong(), Mockito.any(Person.class));
			
			Optional<Person>optPerson = personController.updatePerson(personTestIdStr, "", "");
			
			assertAll(
					()->Assertions.assertThat(optPerson).isNotNull(),
					()->Assertions.assertThat(optPerson.get().getPersonId()).isEqualTo(personTestId),
					()->Assertions.assertThat(optPerson.get().getName()).isEqualTo(personTestName),
					()->Assertions.assertThat(optPerson.get().getSurname()).isEqualTo(personTestSurname)
					);
			verify(personDaoImpl,times(1)).getById(Mockito.anyLong());
			verify(personDaoImpl,times(1)).update(Mockito.anyLong(), Mockito.any(Person.class));
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
			mockChecker.verify(() -> Checker.checkName(Mockito.anyString()), times(1));			
			mockChecker.verify(() -> Checker.checkSurname(Mockito.anyString()), times(1));		
		}	
	}
	
	@Test
	void PersonController_deletePerson_ReturnInt() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class) ){
			
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			doReturn(personTest).when(personDaoImpl).getByIdEagger(Mockito.anyLong());
			doNothing().when(personDaoImpl).delete(Mockito.anyLong());			

			int returnValue = personController.deletePerson(personTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isZero()
					);
			verify(personDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
			verify(personDaoImpl,times(1)).delete(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}	
	}

	@Test
	void PersonController_deletePerson_ReturnIntError2() {
		
		try( MockedStatic<Checker>mockChecker = Mockito.mockStatic(Checker.class) ){
			
			mockChecker.when( () -> Checker.checkDigits(Mockito.anyString()) ).thenReturn(true);
			personTest.getFlixPersonPosition().add(new FlixPersonPosition());
			doReturn(personTest).when(personDaoImpl).getByIdEagger(Mockito.anyLong());

			int returnValue = personController.deletePerson(personTestIdStr);
			
			assertAll(
					()->Assertions.assertThat(returnValue).isEqualTo(-2L)
					);
			verify(personDaoImpl,times(1)).getByIdEagger(Mockito.anyLong());
			mockChecker.verify(() -> Checker.checkDigits(Mockito.anyString()), times(1));
		}	
	}
	
	
}
