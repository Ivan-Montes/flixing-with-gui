package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ime.flixing.dao.GenericDao;
import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

public class PositionDaoImpl implements GenericDao<Position>{

	private final SessionFactory sessionFactory;	
	
	public PositionDaoImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSession();
	}

	public PositionDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Position> getAll() {
		try( Session session = sessionFactory.openSession() ){
			Query<Position> query = session.createQuery("FROM Position", Position.class);
			return query.list();	
		}
	}

	@Override
	public Position getById(Long id) {
		try( Session session = sessionFactory.openSession() ){
			return session.get(Position.class, id);
		}
	}

	@Override
	public Position getByIdEagger(Long id) {
		try( Session session = sessionFactory.openSession() ){
			Query<Position> query = session.createQuery("SELECT p FROM Position p LEFT JOIN FETCH p.flixPersonPosition WHERE p.positionId = :id", Position.class);
			query.setParameter("id", id);
			return query.uniqueResult();
		}
	}

	@Override
	public Position save(Position entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			return session.get(Position.class, entity.getPositionId());
		}
	}

	@Override
	public Position update(Long id, Position entity) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Position positioni = session.get(Position.class, id);
			positioni.setName(entity.getName());
			positioni.setDescription(entity.getDescription());
			session.persist(positioni);
			session.getTransaction().commit();
			return session.get(Position.class, id);
		}
	}

	@Override
	public void delete(Long id) {
		try( Session session = sessionFactory.openSession() ){
			session.beginTransaction();
			Position positionFound = session.get(Position.class, id);
			session.remove(positionFound);
			session.getTransaction().commit();
		}
	}

	@Override
	public List<Position> getByName(String name) {
		try( Session session = sessionFactory.openSession() ){
			Query<Position> query = session.createQuery("FROM Position p WHERE p.name = :name", Position.class);
			query.setParameter("name", name);
			return query.list();
		}
	}
}