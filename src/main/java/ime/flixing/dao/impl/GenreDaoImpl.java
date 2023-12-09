package ime.flixing.dao.impl;

import java.util.List;

import ime.flixing.dao.GenreDao;
import ime.flixing.entity.Genre;
import ime.flixing.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;


public class GenreDaoImpl implements GenreDao{

	@Override
	public List<Genre> getAllGenre() {
		
		Session session = HibernateUtil.getSession().openSession();
		Query<Genre> query = session.createQuery("FROM Genre", Genre.class);
		List<Genre>list = query.list();
		session.close();
		return list;
	}

	@Override
	public Genre getGenreById(Long id) {
		
		Session session = HibernateUtil.getSession().openSession();
		Genre genreFound = session.get(Genre.class, id);
		session.close();
		return genreFound;
	}

	@Override
	public Genre saveGenre(Genre genre) {
		
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		session.persist(genre);
		session.getTransaction().commit();
		Genre genreFound = session.get(Genre.class, genre.getGenreId());
		session.close();
		return genreFound;
	}

	@Override
	public Genre updateGenre(Long id, Genre genre) {
		
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Genre genredo = session.get(Genre.class, id);
		genredo.setName(genre.getName());
		genredo.setDescription(genre.getDescription());
		session.persist(genredo);
		session.getTransaction().commit();
		Genre genreFound = session.get(Genre.class, id);
		session.close();
		return genreFound;
	}

	@Override
	public void deleteGenre(Long id) {
		
		Session session = HibernateUtil.getSession().openSession();
		session.beginTransaction();
		Genre genreFound = session.get(Genre.class, id);
		session.remove(genreFound);
		session.getTransaction().commit();
        session.close();	
		
	}

	@Override
	public Genre getGenreByIdEagger(Long id) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Genre> query = session.createQuery("SELECT g FROM Genre g LEFT JOIN FETCH g.flixes WHERE g.genreId = :id", Genre.class);
		query.setParameter("id", id);
		Genre genreFound = query.uniqueResult();
		session.close();
		return genreFound;
	}

	@Override
	public List<Genre> getGenreByName(String name) {
		Session session = HibernateUtil.getSession().openSession();
		Query<Genre> query = session.createQuery("FROM Genre g WHERE g.name = :name", Genre.class);
		query.setParameter("name", name);
		List<Genre>list = query.list();
		session.close();
		return list;
	}
}