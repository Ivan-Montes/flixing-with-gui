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
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.flixing.entity.Flix;
import ime.flixing.entity.Genre;
import ime.flixing.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
class FlixDaoImplTest {
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<Flix> query;
	
	@Mock
	private Transaction transTest;
	
	@InjectMocks
	private FlixDaoImpl flixDaoImpl;
	
	private Flix flixTest;
	private Long flixTestId = 1L;
	
	@BeforeEach
	private void createObjects() {
		flixTest = new Flix();
		flixTest.setFlixId(flixTestId);
		flixTest.setTitle("Test Title");
		flixTest.setGenre(new Genre());
		flixTest.setFlixPersonPosition(null);
	}
	
	@Test
	void flixDaoImpl_getAllFlix_ReturnList() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(flixTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Flix>list = flixDaoImpl.getAllFlix();
			
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
	void flixDaoImpl_getFlixById_ReturnFlix() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);
			doNothing().when(session).close();
			
			Flix flix = flixDaoImpl.getFlixById(1L);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(flixTest),
					()->Assertions.assertThat(flix.getFlixId()).isEqualTo(flixTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Flix.class, flixTestId);
			verify(session,times(1)).close();
		}		
	}
	@Test
	void flixDaoImpl_getFlixById_ReturnNull() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(null).when(session).get(Flix.class, flixTestId);
			doNothing().when(session).close();
			
			Flix flix = flixDaoImpl.getFlixById(1L);
			
			assertAll(
					()->assertNull(flix)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Flix.class, flixTestId);
			verify(session,times(1)).close();
		}		
	}
	
	@Test
	void flixDaoImpl_getFlixByIdEagger_ReturnFlix() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());			
			doReturn(flixTest).when(query).uniqueResult();
			doNothing().when(session).close();
			
			Flix flix = flixDaoImpl.getFlixByIdEagger(1L);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(flixTest),
					()->Assertions.assertThat(flix.getFlixId()).isEqualTo(flixTestId)
					);			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).close();
		}
	}
	
	@Test
	void flixDaoImpl_saveFlix_ReturnFlix() {
				
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doNothing().when(session).persist(Mockito.any(Flix.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);
			doNothing().when(session).close();
		
			Flix flix = flixDaoImpl.saveFlix(flixTest);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(flixTest),
					()->Assertions.assertThat(flix.getFlixId()).isEqualTo(flixTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).persist(Mockito.any(Flix.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).get(Flix.class, flixTestId);
			verify(session,times(1)).close();
			
		}
	}
		
	@Test
	void flixDaoImpl_updateFlix_ReturnFlix() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);	
			doNothing().when(session).persist(Mockito.any(Flix.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			Flix flix = flixDaoImpl.updateFlix(flixTestId, flixTest);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(flixTest),
					()->Assertions.assertThat(flix.getFlixId()).isEqualTo(flixTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(2)).get(Flix.class, flixTestId);	
			verify(session,times(1)).persist(Mockito.any(Flix.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();			
		}
	}	
		
	@Test
	void flixDaoImpl_deleteFlix_ReturnVoid() {		
	
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);
			doNothing().when(session).remove(Mockito.any(Flix.class));
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			flixDaoImpl.deleteFlix(flixTestId);
			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(1)).get(Flix.class, flixTestId);
			verify(session,times(1)).remove(Mockito.any(Flix.class));
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();
		}		
	}		
	

}
