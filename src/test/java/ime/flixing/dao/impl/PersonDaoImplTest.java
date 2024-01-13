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

import ime.flixing.entity.Person;
import ime.flixing.util.HibernateUtilTest;

@ExtendWith(MockitoExtension.class)
class PersonDaoImplTest {
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Query<Person> query;
	

	@Mock
	private Transaction transTest;
	
	@InjectMocks
	private PersonDaoImpl personDaoImpl;
	
	private Person personTest;
	private Long personTestId = 1L;

	@BeforeEach
	private void createObjects() {
		personTest = new Person();
		personTest.setPersonId(personTestId);
		personTest.setName("Taro");
		personTest.setSurname("Misaki");
	}
	
	@Test
	void personDaoImpl_getAllPerson_ReturnList() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(personTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Person>list = personDaoImpl.getAll();
			
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
	void personDaoImpl_getPersonById_ReturnPerson() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(personTest).when(session).get(Person.class, personTestId);
			doNothing().when(session).close();
			
			Person person = personDaoImpl.getById(personTestId);
			
			assertAll(
					()->assertNotNull(person),
					()->Assertions.assertThat(person).isEqualTo(personTest),
					()->Assertions.assertThat(person.getPersonId()).isEqualTo(personTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Person.class, personTestId);
			verify(session,times(1)).close();
		}		
	}
	@Test
	void personDaoImpl_getPersonById_ReturnNull() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(null).when(session).get(Person.class, personTestId);
			doNothing().when(session).close();
			
			Person person = personDaoImpl.getById(personTestId);
			
			assertAll(
					()->assertNull(person)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).get(Person.class, personTestId);
			verify(session,times(1)).close();
		}		
	}

	@Test
	void personDaoImpl_getPersonByIdEagger_ReturnPerson() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();		
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(personTest).when(query).uniqueResult();
			doNothing().when(session).close();
			
			Person person = personDaoImpl.getByIdEagger(personTestId);
			
			assertAll(
					()->assertNotNull(person),
					()->Assertions.assertThat(person).isEqualTo(personTest),
					()->Assertions.assertThat(person.getPersonId()).isEqualTo(personTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).close();
			
		}		
	}		
			
	@Test
	void personDaoImpl_savePerson_ReturnPerson() {
				
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();	
			doNothing().when(session).persist(Mockito.any(Person.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doReturn(personTest).when(session).get(Person.class, personTestId);
			doNothing().when(session).close();
		
			Person person = personDaoImpl.save(personTest);
			
			assertAll(
					()->assertNotNull(person),
					()->Assertions.assertThat(person).isEqualTo(personTest),
					()->Assertions.assertThat(person.getPersonId()).isEqualTo(personTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();	
			verify(session,times(1)).persist(Mockito.any(Person.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).get(Person.class, personTestId);
			verify(session,times(1)).close();
			
		}
	}
		
	@Test
	void personDaoImpl_updatePerson_ReturnPerson() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(personTest).when(session).get(Person.class, personTestId);	
			doNothing().when(session).persist(Mockito.any(Person.class));			
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			Person person = personDaoImpl.update(personTestId, personTest);
			
			assertAll(
					()->assertNotNull(person),
					()->Assertions.assertThat(person).isEqualTo(personTest),
					()->Assertions.assertThat(person.getPersonId()).isEqualTo(personTestId)
					);
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(2)).get(Person.class, personTestId);	
			verify(session,times(1)).persist(Mockito.any(Person.class));	
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();			
		}
	}	
		
	@Test
	void personDaoImpl_deletePerson_ReturnVoid() {		
	
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(transTest).when(session).beginTransaction();
			doReturn(personTest).when(session).get(Person.class, personTestId);
			doNothing().when(session).remove(Mockito.any(Person.class));
			doReturn(transTest).when(session).getTransaction();		
			doNothing().when(transTest).commit();
			doNothing().when(session).close();
			
			personDaoImpl.delete(personTestId);
			
			verify(sessionFactory,times(1)).openSession();
			verify(session,times(1)).beginTransaction();
			verify(session,times(1)).get(Person.class, personTestId);
			verify(session,times(1)).remove(Mockito.any(Person.class));
			verify(session,times(1)).getTransaction();
			verify(transTest,times(1)).commit();
			verify(session,times(1)).close();
		}		
	}


	@Test
	void personDaoImpl_getPersonByNameId_ReturnList() {
		
		try ( MockedStatic<HibernateUtilTest>hibernateUtilAsserts = Mockito.mockStatic(HibernateUtilTest.class) ){
			hibernateUtilAsserts.when(HibernateUtilTest::getSession).thenReturn(sessionFactory);
			doReturn(session).when(sessionFactory).openSession();	
			doReturn(query).when(session).createQuery(Mockito.anyString(),Mockito.any());	
			doReturn(List.of(personTest)).when(query).list();	
			doNothing().when(session).close();
			
			List<Person>list = personDaoImpl.getByName(personTest.getName());
			
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
