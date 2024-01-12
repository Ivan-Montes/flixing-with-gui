package ime.flixing.dao.impl;

import java.util.List;


import ime.flixing.dao.GenericDao;
import ime.flixing.entity.Flix;
import ime.flixing.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FlixDaoImpl implements GenericDao<Flix>{

	private final SessionFactory sessionFactory;	
	
	public FlixDaoImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSession();
	}

	public FlixDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Flix> getAll() {
		
		try(Session session = sessionFactory.openSession()){
			Query<Flix> query = session.createQuery("FROM Flix", Flix.class);
			return query.list();
		}
	}

	@Override
	public Flix getById(Long id) {
		
		try(Session session = sessionFactory.openSession()){
			return session.get(Flix.class, id);
		}
	}

	@Override
	public Flix getByIdEagger(Long id) {

		try(Session session = sessionFactory.openSession()){
			Query<Flix> query = session.createQuery("SELECT f FROM Flix f LEFT JOIN FETCH f.flixPersonPosition WHERE f.flixId = :id", Flix.class);
			query.setParameter("id", id);
			return query.uniqueResult();
		}
	}

	@Override
	public Flix save(Flix entity) {

		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			return session.get(Flix.class, entity.getFlixId());
		}
	}

	@Override
	public Flix update(Long id, Flix entity) {

		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			Flix flixedo = session.get(Flix.class, id);
			flixedo.setGenre(entity.getGenre());
			flixedo.setTitle(entity.getTitle());
			session.persist(flixedo);
			session.getTransaction().commit();
			return session.get(Flix.class, id);
		}
	}

	@Override
	public void delete(Long id) {

		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();
			Flix flixFound = session.get(Flix.class, id);
			session.remove(flixFound);
			session.getTransaction().commit();
		}
	}

	@Override
	public List<Flix> getByName(String name) {

		try(Session session = sessionFactory.openSession()){
			Query<Flix> query = session.createQuery("FROM Flix f WHERE f.title = :name", Flix.class);
			query.setParameter("name", name);
			return query.list();
		}
	}

}
