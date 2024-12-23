package cas10;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class Zadatak3 {

	public static void main(String[] args) {
		
		System.out.println("POCETAK ....");
		
		insertNK();
		printNK();
		updateNK();
		printNK();
		deleteNK();
		printNK();
		
		
		System.out.println("KRAAJ ....");

		HibernateUtil.getSessionFactory().close();
		
	}
	
	private static void insertNK() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
	
		NivoKvalifikacije nk = new NivoKvalifikacije(42, "Novi nivo");
		
		try {
			tr = session.beginTransaction();
						
			session.save(nk);
			
			System.out.println("Uspesno je unet novi nivo kvalifikacije!");
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("Ne moze da se unese zadati nivo kvalifikacije!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();			
		}
		
	}
	
	private static void printNK() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		NivoKvalifikacije nk = session.get(NivoKvalifikacije.class, 42);
		
		if (nk != null) {
			System.out.println(nk);
		} else {
			System.out.println("Ne postoji zadati nivo kvalifikacije");
		}
		
		session.close();
	}
	
	private static void updateNK() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			NivoKvalifikacije nk = session.get(NivoKvalifikacije.class, 42);
			
			if (nk != null) {
				
//				nk.setId(43);
				nk.setNaziv("Novi nivo kvalifikacije");
				
				
				
			} else {
				System.out.println("Ne postoji");
			}
			
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("NK se ne moze azurirati!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();			
		}
	}
	
	private static void deleteNK() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			NivoKvalifikacije nk = session.get(NivoKvalifikacije.class, 42);
			
			if (nk != null) {
				
				session.delete(nk);
				
				System.out.println("Uspesno set obrisali nk!");
				
			} else {
				System.out.println("Nema nk --> nema brisanje!");
			}
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("Doslo je do greske! Ne moze se obrisati NK!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();
		}
		
		
		
	}

}
