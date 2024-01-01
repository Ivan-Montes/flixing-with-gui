package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ime.flixing.dao.GenericDao;
import ime.flixing.entity.Person;
import ime.flixing.util.HibernateUtil;

public class PersonDaoImpl implements GenericDao<Person>{

	private final SessionFactory sessionFactory;	
	
	public PersonDaoImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSession();
	}

	public PersonDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Person> getAll() {
		try( Session session = sessionFactory.openSession() ){
			Query<Person> query = session.createQuery("FROM Person", Person.class);
			return query.list();
		}
	}

	@Override
	public Person getById(Long id) {
		try( Session session = sessionFactory.openSession() ){
			return session.get(Person.class, id);
		}
	}

	@Override
	public Person getByIdEagger(Long id) {
		try( Session session = sessionFactory.openSession() ){
			Query<Person> query = session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.flixPersonPosition WHERE p.personId = :id", Person.class);
			query.setParameter("id", id);
			return query.uniqueResult();	
		}
	}

	@Override
	public Person save(Person entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			return session.get(Person.class, entity.getPersonId());
		}
	}

	@Override
	public Person update(Long id, Person entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Person personedo = session.get(Person.class, id);
			personedo.setName(entity.getName());
			personedo.setSurname(entity.getSurname());
			session.persist(personedo);
			session.getTransaction().commit();
			return session.get(Person.class, id);	
		}
	}

	@Override
	public void delete(Long id) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Person personFound = session.get(Person.class, id);
			session.remove(personFound);
			session.getTransaction().commit();
		}
	}

	@Override
	public List<Person> getByName(String name) {
		try( Session session = sessionFactory.openSession() ){
			Query<Person> query = session.createQuery("FROM Person p WHERE p.name = :name", Person.class);
			query.setParameter("name", name);
			return query.list();
		}
	}

}
