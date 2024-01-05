package ime.flixing.mvc.controller;

import java.util.List;
import java.util.Optional;

import ime.flixing.dao.impl.PersonDaoImpl;
import ime.flixing.entity.Person;
import ime.flixing.mvc.view.PersonView;
import ime.flixing.mvc.view.person.*;
import ime.flixing.tool.Checker;


public class PersonController {

	private PersonDaoImpl personDaoImpl;
	
	public PersonController() {
		super();
		this.personDaoImpl = new PersonDaoImpl();
	}

	public PersonController(PersonDaoImpl personDaoImpl) {
		super();
		this.personDaoImpl = personDaoImpl;
	}

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

	public final Optional<List<Person>> getAllPerson() {

		return Optional.ofNullable( personDaoImpl.getAll() );
		
	}
	
	public final Optional<Person> getPersonById(String strPersonCod){
		
		Optional<Person>optPerson = Optional.empty();
		
		if ( Checker.checkDigits(strPersonCod) ) {
			
			optPerson = Optional.ofNullable( personDaoImpl.getById(Long.parseLong(strPersonCod)) );
		}
		
		return optPerson;
	}
	
	public final Optional<Person> savePerson(String strName, String strSurname){
		
		Optional<Person>optPerson = Optional.empty();
		
		if ( Checker.checkName(strName)
				&& Checker.checkSurname(strSurname) ){
			
			Person person = new Person();
			person.setName(strName);
			person.setSurname(strSurname);
			optPerson = Optional.ofNullable(personDaoImpl.save(person));
			
		}
		
		return optPerson;
		
	}
	
	public final Optional<Person> updatePerson(String strPersonCod, String strName, String strSurname){

		Optional<Person>optPersonSaved = Optional.empty();
		
		if ( Checker.checkDigits(strPersonCod) &&
				Checker.checkName(strName)
				&& Checker.checkSurname(strSurname) ){
						
			Optional<Person> optPersonFound = Optional.ofNullable(personDaoImpl.getById(Long.parseLong(strPersonCod)));
			
			if ( optPersonFound.isPresent() ) {
				
				Person person = new Person();
				person.setName(strName);
				person.setSurname(strSurname);
				optPersonSaved = Optional.ofNullable(personDaoImpl.update(Long.parseLong(strPersonCod), person));
				
			}
			
		}
		
		return optPersonSaved;
	}
	
	public final int deletePerson(String strPersonCod) {
		
		int returnValue = -1;
		
		if ( Checker.checkDigits(strPersonCod) ) {
						
			Optional<Person>optPerson = Optional.ofNullable( personDaoImpl.getByIdEagger(Long.parseLong(strPersonCod)) );
		
			if (optPerson.isPresent() ) {
				
					if ( optPerson.get().getFlixPersonPosition().isEmpty() ) {
					
						personDaoImpl.delete( Long.parseLong(strPersonCod) );
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
