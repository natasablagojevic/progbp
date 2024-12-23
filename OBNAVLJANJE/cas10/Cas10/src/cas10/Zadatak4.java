package cas10;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Zadatak4 {
	
	public static void main(String []args) {
		System.out.println("POCETAK ...");
		
		
		try (Scanner sc = new Scanner(System.in)) {
			insertPredmet(sc);
			
			System.out.println("Unesite id: predmeta: ");
			int id = sc.nextInt();
			printPredmet(id);
			
			updatePredmet(id, sc);
			
			printPredmet(id);
			
			deletePredmet(id);
			
			printPredmet(id);
		}
		
		
		System.out.println("KRAJ ...");
		
		HibernateUtil.getSessionFactory().close();
	}
	
	
	private static void insertPredmet(Scanner sc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		System.out.println("Unosenje informacija za predmet ....");
		
		System.out.println("ID: ");
		int id = sc.nextInt();
		
		System.out.println("OZNAKA: ");
		String oznaka = sc.next();
		
		System.out.println("NAZIV: ");
		sc.nextLine();
		String naziv = sc.nextLine();
		
		System.out.println("ESPB: ");
		int espb = sc.nextInt();

		Predmet p = new Predmet(id, oznaka, naziv, espb);
		
		try {
			tr = session.beginTransaction();
			
			session.save(p);
			
			System.out.println("\tPREDMET JE USPESNO SACUVAN!");
			
			tr.commit();		
		} catch (Exception e) {
			System.out.println("NEUSPESNO DODAVANJE PREDMETA!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();
		}
	}
	
	private static void printPredmet(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Predmet p = session.get(Predmet.class, id);
		
		if (p != null) {
			System.out.println(p);
		} else {
			System.out.println("PREDMET NE POSTOJI!");
		}
		
		session.close();
	}
	
	private static void updatePredmet(int id, Scanner sc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			Predmet p = session.get(Predmet.class, id);
			
			if (p != null) {
				
				System.out.println("Da li zelite da azurirate ESPB za predmet " + id + "? [da/ne]");
				String odg = sc.next();
				
				if (odg.equalsIgnoreCase("da")) {
					System.out.println("Unesite ESPB:");
					int espb = sc.nextInt();
					
					p.setEspb(espb);
				} else {
					System.out.println("\tBODOVI SE NECE AZURIRATI!");
				}
			} else {
				System.out.println("PREDMET NE POSTOJI! AZURIRANJE NIJE USPELO!");
			}
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("AZURIRANJE NIJE USPELO!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();
		}		
	}
	
	private static void deletePredmet(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			Predmet p = session.get(Predmet.class, id);
			
			if (p != null) {
				
				session.delete(p);
				 
			} else {
				System.out.println("PREDMET NE POSTOJI! BRISANJE NIJE USPELO!");
			}
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("BRISANJE NIJE USPELO!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();
		}
	}
	
}
