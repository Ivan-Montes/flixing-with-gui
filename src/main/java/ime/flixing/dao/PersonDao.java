package ime.flixing.dao;

import java.util.List;
import ime.flixing.entity.Person;

public interface PersonDao {

	List<Person>getAllPerson();
	Person getPersonById(Long id);
	Person getPersonByIdEagger(Long id);
	Person savePerson(Person person);
	Person updatePerson(Long id, Person person);
	void deletePerson(Long id);
	
}
