package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.impl.PersonDaoImpl;
import ime.flixing.entity.Person;
import ime.flixing.mvc.view.PersonView;
import ime.flixing.mvc.view.person.*;
import ime.flixing.tool.Checker;


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
}
