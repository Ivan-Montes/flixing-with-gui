package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ime.flixing.dao.PersonDao;
import ime.flixing.entity.Person;
import ime.flixing.util.HibernateUtil;

public class PersonDaoImpl implements PersonDao{

	@Override
	public List<Person> getAllPerson() {
		Session session = HibernateUtil.getSession().openSession();
		Query<Person> query = session.createQuery("FROM Person", Person.class);
		List<Person>list = query.list();
		session.close();
		return list;
	}

	@Override
	public Person getPersonById(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Person personFound = session.get(Person.class, id);
		session.close();
		return personFound;
	}

	@Override
	public Person getPersonByIdEagger(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Person> query = session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.flixPersonPosition WHERE p.personId = :id", Person.class);
		query.setParameter("id", id);
		Person personFound = query.uniqueResult();
		session.close();
		return personFound;
	}

	@Override
	public Person savePerson(Person person) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		session.persist(person);
		session.getTransaction().commit();
		Person personFound = session.get(Person.class, person.getPersonId());
		session.close();
		return personFound;
	}

	@Override
	public Person updatePerson(Long id, Person person) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Person personedo = session.get(Person.class, id);
		personedo.setName(person.getName());
		personedo.setSurname(person.getSurname());
		session.persist(personedo);
		session.getTransaction().commit();
		Person personFound = session.get(Person.class, id);
		session.close();
		return personFound;
	}

	@Override
	public void deletePerson(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Person personFound = session.get(Person.class, id);
		session.remove(personFound);
		session.getTransaction().commit();
        session.close();
	}

}
