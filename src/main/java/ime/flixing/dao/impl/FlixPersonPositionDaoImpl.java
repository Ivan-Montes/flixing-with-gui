package ime.flixing.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ime.flixing.dao.FlixPersonPositionDao;
import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.FlixPersonPositionId;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

public class FlixPersonPositionDaoImpl implements FlixPersonPositionDao {

	private final SessionFactory sessionFactory;
	
	public FlixPersonPositionDaoImpl() {
		super();
		this.sessionFactory = HibernateUtil.getSession();
	}

	public FlixPersonPositionDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FlixPersonPosition saveFlixPersonPosition(Long flixId, Long personId, Long positionId) {
		
		try(Session session = sessionFactory.openSession()){
			
			session.beginTransaction();		
			FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);		
			Flix flixFound = session.get(Flix.class, flixId);
			Person personFound = session.get(Person.class, personId);
			Position positionFound = session.get(Position.class, positionId);
			FlixPersonPosition flixPersonPosition = new FlixPersonPosition(flixPersonPositionId,flixFound,personFound,positionFound);
			session.persist(flixPersonPosition);
			session.getTransaction().commit();
			return session.get(FlixPersonPosition.class, flixPersonPositionId);
		
	}}

	@Override
	public void deleteFlixPersonPosition(Long flixId, Long personId, Long positionId) {
		
		try(Session session = sessionFactory.openSession()){
			
			session.beginTransaction();		
			FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);	
			Flix flixFound = session.get(Flix.class, flixId);
			Person personFound = session.get(Person.class, personId);
			Position positionFound = session.get(Position.class, positionId);
			FlixPersonPosition flixPersonPosition = new FlixPersonPosition(flixPersonPositionId,flixFound,personFound,positionFound);
			session.remove(flixPersonPosition);
			session.getTransaction().commit();
		
	}}

	@Override
	public List<FlixPersonPosition> getAllFlixPersonPosition() {
		
		try(Session session = sessionFactory.openSession()){
			
			return session.createQuery("FROM FlixPersonPosition", FlixPersonPosition.class).list();
		
		}
	}

	@Override
	public FlixPersonPosition getFlixPersonPositionById(Long flixId, Long personId, Long positionId) {
		
		try(Session session = sessionFactory.openSession()){
			
			FlixPersonPositionId flixPersonPositionId = new FlixPersonPositionId(flixId, personId, positionId);
			return session.get(FlixPersonPosition.class, flixPersonPositionId);
	
		}
	}

}
