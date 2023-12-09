package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ime.flixing.dao.FlixPersonPositionDao;
import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.FlixPersonPositionId;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

public class FlixPersonPositionDaoImpl implements FlixPersonPositionDao {

	@Override
	public FlixPersonPosition saveFlixPersonPosition(Long flixId, Long personId, Long positionId) {
		
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();		
		FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);		
		Flix flixFound = session.get(Flix.class, flixId);
		Person personFound = session.get(Person.class, personId);
		Position positionFound = session.get(Position.class, positionId);
		FlixPersonPosition flixPersonPosition = new FlixPersonPosition(flixPersonPositionId,flixFound,personFound,positionFound);
		session.persist(flixPersonPosition);
		session.getTransaction().commit();
		FlixPersonPosition flixPersonPositionSaved = session.get(FlixPersonPosition.class, flixPersonPositionId);
		session.close();
		return flixPersonPositionSaved;
		
	}

	@Override
	public void deleteFlixPersonPosition(Long flixId, Long personId, Long positionId) {
		
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();		
		FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);	
		Flix flixFound = session.get(Flix.class, flixId);
		Person personFound = session.get(Person.class, personId);
		Position positionFound = session.get(Position.class, positionId);
		FlixPersonPosition flixPersonPosition = new FlixPersonPosition(flixPersonPositionId,flixFound,personFound,positionFound);
		session.remove(flixPersonPosition);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<FlixPersonPosition> getAllFlixPersonPosition() {
		Session session = HibernateUtil.getSession().openSession();
		Query<FlixPersonPosition> query = session.createQuery("FROM FlixPersonPosition", FlixPersonPosition.class);
		List<FlixPersonPosition> list = query.list();	
		session.close();	
		return list;
	}

	@Override
	public FlixPersonPosition getFlixPersonPositionById(Long flixId, Long personId, Long positionId) {
		Session session = HibernateUtil.getSession().openSession();
		FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);
		FlixPersonPosition flixPersonPosition = session.get(FlixPersonPosition.class, flixPersonPositionId);
		session.close();
		return flixPersonPosition;
	}

}
