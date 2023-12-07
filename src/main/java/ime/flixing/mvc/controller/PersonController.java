package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.PersonDao;
import ime.flixing.dao.impl.PersonDaoImpl;
import ime.flixing.entity.Person;
import ime.flixing.mvc.view.PersonView;
import ime.flixing.mvc.view.person.*;
import ime.flixing.tool.Checker;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonController {

	public static final void initPersonController() {
		
		PersonView personView = new PersonView();
		personView.setVisible(true);
		
	}
	
	public static final void initPersonDeleteView() {
		
		PersonDeleteView view = new PersonDeleteView();
		view.setVisible(true);
		
	}
	
	public static final void initPersonGetAllView() {
		
		PersonGetAllView view = new PersonGetAllView();
		view.setVisible(true);
		
	}

	public static final void initPersonGetByIdView() {
		
		PersonGetByIdView view = new PersonGetByIdView();
		view.setVisible(true);
		
	}

	public static final void initPersonSaveView() {
		
		PersonSaveView view = new PersonSaveView();
		view.setVisible(true);
		
	}

	public static final void initPersonUpdateView() {
		
		PersonUpdateView view = new PersonUpdateView();
		view.setVisible(true);
		
	}

	public static final Optional<List<Person>> getAllPerson() {

		return Optional.ofNullable( new PersonDaoImpl().getAllPerson() );
		
	}
	
	public static final Optional<Person> getPersonById(String strPersonCod){
		
		Optional<Person>optPerson = Optional.empty();
		
		if ( Checker.checkDigits(strPersonCod) ) {
			
			optPerson = Optional.ofNullable( new PersonDaoImpl().getPersonById(Long.parseLong(strPersonCod)) );
		}
		
		return optPerson;
	}
	
	public static final Optional<Person> savePerson(String strName, String strSurname){
		
		Optional<Person>optPerson = Optional.empty();
		
		if ( Checker.checkName(strName)
				&& Checker.checkSurname(strSurname) ){
			
			Person person = new Person();
			person.setName(strName);
			person.setSurname(strSurname);
			optPerson = Optional.ofNullable(new PersonDaoImpl().savePerson(person));
			
		}
		
		return optPerson;
		
	}
	
	public static final Optional<Person> updatePerson(String strPersonCod, String strName, String strSurname){

		Optional<Person>optPersonSaved = Optional.empty();
		
		if ( Checker.checkDigits(strPersonCod) &&
				Checker.checkName(strName)
				&& Checker.checkSurname(strSurname) ){
			
			PersonDao personDao = new PersonDaoImpl();
			Optional<Person> optPersonFound = Optional.ofNullable(personDao.getPersonById(Long.parseLong(strPersonCod)));
			
			if ( optPersonFound.isPresent() ) {
				
				Person person = new Person();
				person.setName(strName);
				person.setSurname(strSurname);
				optPersonSaved = Optional.ofNullable(personDao.updatePerson(Long.parseLong(strPersonCod), person));
				
			}
			
		}
		
		return optPersonSaved;
	}
	
	public static final int deletePerson(String strPersonCod) {
		
		int returnValue = -1;
		
		if ( Checker.checkDigits(strPersonCod) ) {
			
			PersonDao personDao = new PersonDaoImpl();
			Optional<Person>optPerson = Optional.ofNullable( personDao.getPersonByIdEagger(Long.parseLong(strPersonCod)) );
		
			if (optPerson.isPresent() ) {
				
					if ( optPerson.get().getFlixPersonPosition().isEmpty() ) {
					
						personDao.deletePerson( Long.parseLong(strPersonCod) );
						returnValue =  0;
				
					}
					else {
						returnValue = -2;
					}	
			}
		}
		
		return returnValue;
	}
	
	
}
