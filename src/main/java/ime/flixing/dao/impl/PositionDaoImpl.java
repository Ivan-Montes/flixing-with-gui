package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ime.flixing.dao.PositionDao;
import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

public class PositionDaoImpl implements PositionDao{

	@Override
	public List<Position> getAllPosition() {
		
		Session session = HibernateUtil.getSession().openSession();
		Query<Position> query = session.createQuery("FROM Position", Position.class);
		List<Position>list = query.list();
		session.close();
		return list;
		
	}

	@Override
	public Position getPositionById(Long id) {

		Session session = HibernateUtil.getSession().openSession();
		Position positionFound = session.get(Position.class, id);
		session.close();
		return positionFound;
		
	}

	@Override
	public Position savePosition(Position position) {

		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		session.persist(position);
		session.getTransaction().commit();
		Position positionFound = session.get(Position.class, position.getPositionId());
		session.close();
		return positionFound;
		
	}

	@Override
	public Position updatePosition(Long id, Position position) {

		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Position positioni = session.get(Position.class, id);
		positioni.setName(position.getName());
		positioni.setDescription(position.getDescription());
		session.persist(positioni);
		session.getTransaction().commit();
		Position positionFound = session.get(Position.class, id);
		session.close();
		return positionFound;
		
	}

	@Override
	public void deletePosition(Long id) {

		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Position positionFound = session.get(Position.class, id);
		session.remove(positionFound);
		session.getTransaction().commit();
        session.close();	
		
	}

	@Override
	public Position getPositionByIdEagger(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Position> query = session.createQuery("SELECT p FROM Position p LEFT JOIN FETCH p.flixPersonPosition WHERE p.positionId = :id", Position.class);
		query.setParameter("id", id);
		Position positionFound =  query.uniqueResult();
		session.close();
		return positionFound;
	}

	@Override
	public List<Position> getPositionByNameId(String name) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Position> query = session.createQuery("FROM Position p WHERE p.name = :name", Position.class);
		query.setParameter("name", name);
		List<Position>list = query.list();
		session.close();
		return list;
	}
}