package ime.flixing.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.flixing.entity.Genre;
import ime.flixing.util.HibernateUtilTest;

@ExtendWith(MockitoExtension.class)
class GenreDaoImplTest {
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<Genre> query;
	
	@Mock
	private Transaction transTest;
	
	@InjectMocks
	private GenreDaoImpl genreDaoImpl;
	
	private Genre genreTest;
	private Long genreTestId = 1L;
	
	@BeforeEach
	private void createObjects() {
		
		genreTest = new Genre();
		genreTest.setGenreId(genreTestId);
		genreTest.setName("Undertake de manual");
		genreTest.setDescription("Adelantamiento parando antes en boxes");
		
	}

	@Test
	void genreDaoImpl_getAllGenre_ReturnList() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(), Mockito.any());	
			doReturn(List.of(genreTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Genre>list = genreDaoImpl.getAll();
			
			assertAll(
					()->assertNotNull(list),
					()->Assertions.assertThat(list).hasSize(1)					
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).createQuery(Mockito.anyString(), Mockito.any());
			verify(query,times(1)).list();
			verify(session,times(1)).close();
		}
	}
	
	@Test
	void genreDaoImpl_getGenreById_ReturnGenre() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(genreTest).when(session).get(Genre.class, genreTestId);
			doNothing().when(session).close();
			
			Genre genre = genreDaoImpl.getById(genreTestId);
			
			assertAll(
					()->assertNotNull(genre),
					()->Assertions.assertThat(genre).isEqualTo(genreTest),
					()->Assertions.assertThat(genre.getGenreId()).isEqualTo(genreTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Genre.class, genreTestId);
			verify(session,times(1)).close();
		}		
	}
	@Test
	void genreDaoImpl_getGenreById_ReturnNull() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(null).when(session).get(Genre.class, genreTestId);
			doNothing().when(session).close();
			
			Genre genre = genreDaoImpl.getById(genreTestId);
			
			assertAll(
					()->assertNull(genre)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Genre.class, genreTestId);
			verify(session,times(1)).close();
		}		
	}

	@Test
	void genreDaoImpl_getGenreByIdEagger_ReturnGenre() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();		
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(genreTest).when(query).uniqueResult();
			doNothing().when(session).close();
			
			Genre genre = genreDaoImpl.getByIdEagger(genreTestId);
			
			assertAll(
					()->assertNotNull(genre),
					()->Assertions.assertThat(genre).isEqualTo(genreTest),
					()->Assertions.assertThat(genre.getGenreId()).isEqualTo(genreTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).close();
		}		
	}
	
	@Test
	void genreDaoImpl_saveGenre_ReturnGenre() {
				
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doNothing().when(session).persist(Mockito.any(Genre.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doReturn(genreTest).when(session).get(Genre.class, genreTestId);
			doNothing().when(session).close();
		
			Genre genre = genreDaoImpl.save(genreTest);
			
			assertAll(
					()->assertNotNull(genre),
					()->Assertions.assertThat(genre).isEqualTo(genreTest),
					()->Assertions.assertThat(genre.getGenreId()).isEqualTo(genreTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).persist(Mockito.any(Genre.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).get(Genre.class, genreTestId);
			verify(session,times(1)).close();
			
		}
	}
		
	@Test
	void genreDaoImpl_updateGenre_ReturnGenre() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(genreTest).when(session).get(Genre.class, genreTestId);	
			doNothing().when(session).persist(Mockito.any(Genre.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			Genre genre = genreDaoImpl.update(genreTestId, genreTest);
			
			assertAll(
					()->assertNotNull(genre),
					()->Assertions.assertThat(genre).isEqualTo(genreTest),
					()->Assertions.assertThat(genre.getGenreId()).isEqualTo(genreTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(2)).get(Genre.class, genreTestId);	
			verify(session,times(1)).persist(Mockito.any(Genre.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();			
		}
	}	
		
	@Test
	void genreDaoImpl_deleteGenre_ReturnVoid() {		
	
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(genreTest).when(session).get(Genre.class, genreTestId);
			doNothing().when(session).remove(Mockito.any(Genre.class));
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			genreDaoImpl.delete(genreTestId);
			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(1)).get(Genre.class, genreTestId);
			verify(session,times(1)).remove(Mockito.any(Genre.class));
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();
		}		
	}	
	
	@Test
	void genreDaoImpl_getGenreByName_ReturnList(){
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();			
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(genreTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Genre>list = genreDaoImpl.getByName(genreTest.getName());
			
			assertAll(
					()->assertNotNull(list),
					()->Assertions.assertThat(list).hasSize(1)					
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).createQuery(Mockito.anyString(), Mockito.any());
			verify(query,times(1)).list();
			verify(session,times(1)).close();
		}
	}
}
