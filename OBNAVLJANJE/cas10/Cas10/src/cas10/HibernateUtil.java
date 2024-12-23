package cas10;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	
	static {
		try {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
			
			sessionFactory = new MetadataSources(registry)
					.addAnnotatedClass(StudijskiProgram.class)
					.addAnnotatedClass(IspitniRok.class)
					.addAnnotatedClass(NivoKvalifikacije.class)
					.addAnnotatedClass(Predmet.class)
					.buildMetadata().buildSessionFactory();
			
			
		} catch (Throwable e) {
			e.printStackTrace();
			
			System.out.println("Doslo je do greske prilikom pravljenja fabrike!");
			System.exit(1);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
