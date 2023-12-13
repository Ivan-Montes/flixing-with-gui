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

import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
class PositionDaoImplTest {

	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<Position> query;
	
	@Mock
	private Transaction transTest;
	
	@InjectMocks
	private PositionDaoImpl positionDaoImpl;
	
	private Position positionTest;
	private Long positionTestId = 1L;
	
	@BeforeEach
	private void createObjects() {
		positionTest = new Position();
		positionTest.setPositionId(positionTestId);
		positionTest.setName("Master");
		positionTest.setDescription("Leader in dungeon");
	}
	
	@Test
	void positionDaoImpl_getAllFlix_ReturnList() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(positionTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Position>list = positionDaoImpl.getAllPosition();
			
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
	void positionDaoImpl_getPositionById_ReturnPosition() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(positionTest).when(session).get(Position.class, positionTestId);
			doNothing().when(session).close();
			
			Position flix = positionDaoImpl.getPositionById(1L);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(positionTest),
					()->Assertions.assertThat(flix.getPositionId()).isEqualTo(positionTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Position.class, positionTestId);
			verify(session,times(1)).close();
		}		
	}
	
	@Test
	void positionDaoImpl_getPositionById_ReturnNull() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(null).when(session).get(Position.class, positionTestId);
			doNothing().when(session).close();
			
			Position flix = positionDaoImpl.getPositionById(1L);
			
			assertAll(
					()->assertNull(flix)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Position.class, positionTestId);
			verify(session,times(1)).close();
		}		
	}

	@Test
	void positionDaoImpl_getPositionByIdEagger_ReturnPosition() {

		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(), Mockito.any());	
			doReturn(positionTest).when(query).uniqueResult();
			doNothing().when(session).close();
			
			Position flix = positionDaoImpl.getPositionByIdEagger(1L);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(positionTest),
					()->Assertions.assertThat(flix.getPositionId()).isEqualTo(positionTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).close();
		}		
	}
	
	@Test
	void positionDaoImpl_savePosition_ReturnPosition() {
				
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doNothing().when(session).persist(Mockito.any(Position.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doReturn(positionTest).when(session).get(Position.class, positionTestId);
			doNothing().when(session).close();
		
			Position flix = positionDaoImpl.savePosition(positionTest);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(positionTest),
					()->Assertions.assertThat(flix.getPositionId()).isEqualTo(positionTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).persist(Mockito.any(Position.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).get(Position.class, positionTestId);
			verify(session,times(1)).close();
			
		}
	}
		
	@Test
	void positionDaoImpl_updatePosition_ReturnPosition() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(positionTest).when(session).get(Position.class, positionTestId);	
			doNothing().when(session).persist(Mockito.any(Position.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			Position flix = positionDaoImpl.updatePosition(positionTestId, positionTest);
			
			assertAll(
					()->assertNotNull(flix),
					()->Assertions.assertThat(flix).isEqualTo(positionTest),
					()->Assertions.assertThat(flix.getPositionId()).isEqualTo(positionTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(2)).get(Position.class, positionTestId);	
			verify(session,times(1)).persist(Mockito.any(Position.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();			
		}
	}	
		
	@Test
	void positionDaoImpl_deletePosition_ReturnVoid() {		
	
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(positionTest).when(session).get(Position.class, positionTestId);
			doNothing().when(session).remove(Mockito.any(Position.class));
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			positionDaoImpl.deletePosition(positionTestId);
			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(1)).get(Position.class, positionTestId);
			verify(session,times(1)).remove(Mockito.any(Position.class));
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();
		}		
	}	

	@Test
	void positionDaoImpl_getPositionByNameId_ReturnList() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(positionTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Position>list = positionDaoImpl.getPositionByNameId(positionTest.getName());
			
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
