package cas10;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Zadatak2 {

	public static void main(String []args) {
		System.out.println("Pocetak ....");
		
		try (Scanner sc = new Scanner(System.in)) {
			
			unesiIR(sc);
			ispisiIR(sc);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		System.out.println("Kraj .....");
		
		HibernateUtil.getSessionFactory().close();
	}
	
	private static void unesiIR(Scanner sc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Unesite ispitni rok: ");
		int skgodina = sc.nextInt();
		String oznakaroka = sc.next();
		sc.nextLine();
		String naziv = sc.nextLine();
		String datpocetka = sc.next();
		String datkraja = sc.next();
		
		IspitniRok ir = new IspitniRok(skgodina, oznakaroka, naziv, datpocetka, datkraja);
		
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			session.save(ir);
			
			tr.commit();
			
			System.out.println("Ispitni rok je unet!");
		} catch (Exception e) {
			System.out.println("Nije unet IR. Ponistavanje transakcije");
			
			if (tr != null)
				tr.rollback();
			
		} finally {
			session.close();
		}
	}
	
	private static void ispisiIR(Scanner sc)  {
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		System.out.println("Unesi skgodina i oznakaroka: ");
		int skgodina = sc.nextInt();
		String oznakaroka = sc.next();
		
		IspitniRokPK pk = new IspitniRokPK(skgodina, oznakaroka);
		
		IspitniRok ir = session.get(IspitniRok.class, pk);
		
		if (ir != null) {
			System.out.println(ir);
		} else {
			System.out.println("Nije pronadjen!");
		}
		
		session.close();
	}
	
	private static void obrisiIR(Scanner sc) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			tr = session.beginTransaction();
			
			System.out.println("Unesite skgodina i oznakaroka");
			int skgodina = sc.nextInt();
			String oznakaroka = sc.next();
			
			IspitniRokPK pk = new IspitniRokPK(skgodina, oznakaroka);
			
			IspitniRok ir = session.get(IspitniRok.class, pk);
			
			if (ir != null) {
				session.delete(ir);
			} else {
				System.out.println("Ne postoji ispitni rok");
			}
			
			tr.commit();
		} catch (Exception e) {
			System.out.println("Neuspelo brisanje!");
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();			
		}
	}
	
}
