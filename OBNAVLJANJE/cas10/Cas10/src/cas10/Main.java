package cas10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Pocetak rada....");
		
		unesiSP();
		ispisiSPGet();
		azurirajSP();
		ispisiSPLoad();
		izbrisiSP();
		
		System.out.println("Kraj rada....");
		
		HibernateUtil.getSessionFactory().close();
	}
	
	private static void unesiSP() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		StudijskiProgram sp = new StudijskiProgram();
		Transaction TR = null;
		
		sp.setId(102);
		sp.setOznaka("MATF_2020");
		sp.setNaziv("NOVI MATF studijski program u 2020. godini");
		sp.setObimespb(240);
		sp.setNivo(1);
		sp.setZvanje("Diplomirani infomraticar");
		sp.setOpis("Novi studijski program na Matematickom fakultetu");
		
		
		try {
			TR = session.beginTransaction();
			session.save(sp);
			TR.commit();
			
			System.out.println("Studijski program je unet!");
			
		} catch (Exception e) {
			System.out.println("Studijski program nije unet");
			
			if (TR != null)
				TR.rollback();
		} finally {
			session.close();			
		}
		
	}
	
	private static void azurirajSP() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			StudijskiProgram sp = session.get(StudijskiProgram.class, 102);
			
			if (sp != null) {
				
				sp.setObimespb(180);
				sp.setOpis("Nov opis novog SP");
				System.out.println("Azuriran sp");
			} else {
				System.out.println("Nije pronadjen sp");
			}
			
			
			tr.commit();
		} catch (Exception e) {
			
			System.out.println("SP nije unet!");
			
			if (tr != null)
				tr.rollback();
			
		} finally {
			session.close();
		}	
	}
	
	private static void ispisiSPLoad() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		StudijskiProgram sp = new StudijskiProgram();
		
		try {
			session.load(sp, 102);
			
			System.out.println(sp);
		} catch (Exception e) {
			System.out.println("Nije pronadjen red!");
		} finally {
			session.close();			
		}
		
	}
	
	private static void ispisiSPGet() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		StudijskiProgram sp = session.get(StudijskiProgram.class, 102);
		
		if (sp != null) {
			System.out.println(sp);
		} else {
			System.out.println("Niej pronadjen sp");
		}
		
		
		session.close();
	}
	
	private static void izbrisiSP() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			StudijskiProgram sp = session.get(StudijskiProgram.class, 102);
			
			if (sp != null) {
				
				session.delete(sp);
				System.out.println("Obrisan sp");
			} else {
				System.out.println("Ne postoji sp");
			}
			
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("Failed");
			
			if (tr != null)
				tr.rollback();
			
		} finally {
			session.close();
		}
	}
}
