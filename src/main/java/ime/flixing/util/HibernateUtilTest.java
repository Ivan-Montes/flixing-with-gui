package ime.flixing.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import ime.flixing.entity.Flix;
import ime.flixing.entity.FlixPersonPosition;
import ime.flixing.entity.FlixPersonPositionId;
import ime.flixing.entity.Genre;
import ime.flixing.entity.Person;
import ime.flixing.entity.Position;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor( access= AccessLevel.PRIVATE )
public class HibernateUtilTest {

	private static final SessionFactory sessionFactory;
	
	static {
		
			final ServiceRegistry registry = new StandardServiceRegistryBuilder()
																	.applySetting("hibernate.connection.driver_class", "org.h2.Driver")
																	.applySetting("hibernate.connection.url", "jdbc:h2:mem:testdb")
																	.applySetting("hibernate.connection.username", "sa")
													                .applySetting("hibernate.connection.password", "")
													                .applySetting("hibernate.show_sql", "true")
													                .applySetting("hibernate.hbm2ddl.auto", "create")
													                .applySetting("hibernate.hbm2ddl.import_files", "data.sql")
																	.configure()
														            .build();
			try {
				
		        Metadata metadata = new MetadataSources(registry)
		            .addAnnotatedClass(Flix.class)
		            .addAnnotatedClass(Position.class)
		            .addAnnotatedClass(Person.class)
		            .addAnnotatedClass(Genre.class)
		            .addAnnotatedClass(FlixPersonPosition.class)
		            .addAnnotatedClass(FlixPersonPositionId.class)
		            .buildMetadata();		        
		        sessionFactory = metadata.buildSessionFactory();
		        
			
			} catch (RuntimeException  ex) {
				StandardServiceRegistryBuilder.destroy( registry );
				throw new ExceptionInInitializerError(ex + " ##==== Inicio de HibernateUtilTest erroneo => RuntimeException ====##\n ");
		}
	}

	public static SessionFactory getSession() {
		return sessionFactory;
	}
}
