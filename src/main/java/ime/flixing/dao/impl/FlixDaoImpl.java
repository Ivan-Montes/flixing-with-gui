package ime.flixing.dao.impl;

import java.util.List;

import ime.flixing.dao.FlixDao;
import ime.flixing.entity.Flix;
import ime.flixing.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class FlixDaoImpl implements FlixDao{

	@Override
	public List<Flix> getAllFlix() {
		Session session = HibernateUtil.getSession().openSession();
		Query<Flix> query = session.createQuery("FROM Flix", Flix.class);
		List<Flix>list = query.list();
		session.close();
		return list;
	}

	@Override
	public Flix getFlixById(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Flix flixFound = session.get(Flix.class, id);
		session.close();
		return flixFound;
	}

	@Override
	public Flix saveFlix(Flix flix) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		session.persist(flix);
		session.getTransaction().commit();
		Flix flixFound = session.get(Flix.class, flix.getFlixId());
		session.close();
		return flixFound;
	}

	@Override
	public Flix updateFlix(Long id, Flix flix) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Flix flixedo = session.get(Flix.class, id);
		flixedo.setGenre(flix.getGenre());
		flixedo.setTitle(flix.getTitle());
		session.persist(flixedo);
		session.getTransaction().commit();
		Flix flixFound = session.get(Flix.class, id);
		session.close();
		return flixFound;
	}

	@Override
	public void deleteFlix(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Flix flixFound = session.get(Flix.class, id);
		session.remove(flixFound);
		session.getTransaction().commit();
        session.close();		
	}

	@Override
	public Flix getFlixByIdEagger(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Flix> query = session.createQuery("SELECT f FROM Flix f LEFT JOIN FETCH f.flixPersonPosition WHERE f.flixId = :id", Flix.class);
		query.setParameter("id", id);
		Flix flixFound = query.uniqueResult();
		session.close();
		return flixFound;
	}

}
