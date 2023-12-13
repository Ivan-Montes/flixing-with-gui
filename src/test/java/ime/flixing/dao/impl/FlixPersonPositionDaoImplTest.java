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

import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.FlixPersonPositionId;
import ime.flixing.entity.Genre;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import ime.flixing.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
class FlixPersonPositionDaoImplTest {
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<FlixPersonPosition> query;
	
	@Mock
	private Transaction transTest;
	
	@InjectMocks
	private FlixPersonPositionDaoImpl flixPersonPositionDaoImpl;
	
	private Flix flixTest;
	private Long flixTestId = 1L;
	private Person personTest;
	private Long personTestId = 1L;
	private Position positionTest;
	private Long positionTestId = 1L;
	private FlixPersonPosition flixPersonPositionTest;
	private FlixPersonPositionId flixPersonPositionIdTest;
	
	@BeforeEach
	private void createObjects() {
		
		flixTest = new Flix();
		flixTest.setFlixId(flixTestId);
		flixTest.setTitle("Test Title");
		flixTest.setGenre(new Genre());
		flixTest.setFlixPersonPosition(null);

		personTest = new Person();
		personTest.setPersonId(personTestId);
		personTest.setName("Taro");
		personTest.setSurname("Misaki");
		
		positionTest = new Position();
		positionTest.setPositionId(positionTestId);
		positionTest.setName("Master");
		positionTest.setDescription("Leader in dungeon");
		
		flixPersonPositionIdTest = new FlixPersonPositionId(flixTestId, personTestId, positionTestId);
		flixPersonPositionTest = new FlixPersonPosition(flixPersonPositionIdTest,
														flixTest,
														personTest,
														positionTest);
		
	}
	
	@Test
	void flixPersonPosition_saveFlixPersonPosition_ReturnObject() {
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);	
			doReturn(personTest).when(session).get(Person.class, personTestId);	
			doReturn(positionTest).when(session).get(Position.class, positionTestId);			
			doNothing().when(session).persist(Mockito.any(FlixPersonPosition.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doReturn(flixPersonPositionTest).when(session).get(FlixPersonPosition.class, flixPersonPositionIdTest);
			doNothing().when(session).close();
			
			FlixPersonPosition flixPersonPositionSaved = flixPersonPositionDaoImpl.saveFlixPersonPosition(flixTestId, personTestId, positionTestId);
			
			assertAll(
					()->assertNotNull(flixPersonPositionSaved),
					()->Assertions.assertThat(flixPersonPositionSaved).isEqualTo(flixPersonPositionTest)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).persist(Mockito.any(FlixPersonPosition.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).get(FlixPersonPosition.class, flixPersonPositionIdTest);
			verify(session,times(1)).close();
		}
	}	

	@Test
	void flixPersonPosition_deleteFlixPersonPosition_ReturnObject() {		
	
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doReturn(flixTest).when(session).get(Flix.class, flixTestId);	
			doReturn(personTest).when(session).get(Person.class, personTestId);	
			doReturn(positionTest).when(session).get(Position.class, positionTestId);			
			doNothing().when(session).remove(Mockito.any(FlixPersonPosition.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			flixPersonPositionDaoImpl.deleteFlixPersonPosition(flixTestId, personTestId, positionTestId);			
			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).remove(Mockito.any(FlixPersonPosition.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();
		}
	}
	
	@Test
	void flixPersonPosition_getAllFlixPersonPosition_ReturnList() {	
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();		
			doReturn(query).when(session).createQuery(Mockito.anyString(), Mockito.any());	
			doReturn(List.of(flixPersonPositionTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<FlixPersonPosition>list = flixPersonPositionDaoImpl.getAllFlixPersonPosition();
			
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
	void flixPersonPosition_getFlixPersonPositionById_ReturnObject() {	
		
		try ( MockedStatic<HibernateUtil>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtil.class) ){
			hibernateUtilAsserts.when(HibernateUtil::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();
			doReturn(flixPersonPositionTest).when(session).get(FlixPersonPosition.class, flixPersonPositionIdTest);	
			doNothing().when(session).close();
			
			FlixPersonPosition flixPersonPositionFound = flixPersonPositionDaoImpl.getFlixPersonPositionById(flixTestId,
																											personTestId, 
																											positionTestId);			

			assertAll(
					()->assertNotNull(flixPersonPositionFound),
					()->Assertions.assertThat(flixPersonPositionFound).isEqualTo(flixPersonPositionTest)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(FlixPersonPosition.class, flixPersonPositionIdTest);
			verify(session,times(1)).close();
		}	
		
	}
}
