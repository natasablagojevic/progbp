package cas11;


import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class Zadatak1 {

	public static void main(String[] args) {
		
		System.out.println("POCETAK ....");
		
		
		readIspitniRokovi();
		
		System.out.println("KRAJ ...");
		HibernateUtil.getSessionFactory().close();

	}

	private static void readIspitniRokovi() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		
		try {
			String hql = "FROM IspitniRokW";
			
			Query<IspitniRok> upit = session.createQuery(hql, IspitniRok.class);
			List<IspitniRok> ispitniRokovi = upit.list();
			
			for (IspitniRok ir : ispitniRokovi) {
				System.out.println(ir);
			}
			
		} catch (Exception e) {
			System.out.println("FAILURE");
			e.printStackTrace();
			if (tr != null)
				tr.rollback();
		} finally {
			session.close();
		}
	}
	
}
