package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ime.flixing.dao.GenericDao;
import ime.flixing.entity.Genre;
import ime.flixing.util.HibernateUtil;

public class GenreDaoImpl implements GenericDao<Genre> {

	private final SessionFactory sessionFactory;	
	
	public GenreDaoImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSession();
	}

	public GenreDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Genre> getAll() {
		try( Session session = sessionFactory.openSession() ){
			Query<Genre> query = session.createQuery("FROM Genre", Genre.class);
			return query.list();
		}
	}

	@Override
	public Genre getById(Long id) {
		try( Session session = sessionFactory.openSession() ){
		return session.get(Genre.class, id);
		}
	}

	@Override
	public Genre getByIdEagger(Long id) {
		try( Session session = sessionFactory.openSession() ){
			Query<Genre> query = session.createQuery("SELECT g FROM Genre g LEFT JOIN FETCH g.flixes WHERE g.genreId = :id", Genre.class);
			query.setParameter("id", id);
			return query.uniqueResult();
		}		
	}

	@Override
	public Genre save(Genre entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			return session.get(Genre.class, entity.getGenreId());
		}
	}

	@Override
	public Genre update(Long id, Genre entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Genre genredo = session.get(Genre.class, id);
			genredo.setName(entity.getName());
			genredo.setDescription(entity.getDescription());
			session.persist(genredo);
			session.getTransaction().commit();
			return session.get(Genre.class, id);
		}		
	}

	@Override
	public void delete(Long id) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Genre genreFound = session.get(Genre.class, id);
			session.remove(genreFound);
			session.getTransaction().commit();
		}		
	}

	@Override
	public List<Genre> getByName(String name) {
		try( Session session = sessionFactory.openSession() ){
			Query<Genre> query = session.createQuery("FROM Genre g WHERE g.name = :name", Genre.class);
			query.setParameter("name", name);
			return query.list();
		}
	}


}
