package cas08;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Zadatak8 {

	private static class Statistika {
		public int idpredmeta;
		public int broj_polozenih;
		
		public Statistika(int idpredmeta, int broj_polozenih) {
			this.idpredmeta = idpredmeta;
			this.broj_polozenih = broj_polozenih;
		}
		
		@Override
		public String toString() {
			return this.idpredmeta + " " + this.broj_polozenih;
		}
	}
	
	
	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String []args) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa");
				Scanner sc = new Scanner(System.in)) {
			
			ArrayList<Statistika> statistike = new ArrayList<>();
			
			if (napraviTabelu(conn)) 
				System.out.println("Uspesno ste napravili tabelu!");
			else
				System.out.println("Tabela nije uspesno napravljena!");
			
			obradiPredmet(conn, statistike);
			unesiStatistiku(conn, sc, statistike);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println(
							"SQLCODE: " + e.getErrorCode() + "\n" + 
							"SQLSTATE: " + e.getSQLState() + "\n" + 
							"MESSAGE: " + e.getMessage()
							);
			System.exit(1);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static boolean napraviTabelu(Connection conn) throws SQLException{
		String query = "CREATE TABLE IF NOT EXISTS DA.UNETIPREDMETI ( "
				+ "IDPREDMETA INT NOT NULL PRIMARY KEY, "
				+ "BROJ_POLOZENIH_ISPITA INT, "
				+ "CONSTRAINT FK_PREDMET FOREIGN KEY(IDPREDMETA) REFERENCES PREDMET(ID) "
				+ ")";
		
		Statement stmt = conn.createStatement();
		
		int res = stmt.executeUpdate(query);
		
		stmt.close();
		
		return res == 1 ? true : false;
	}
	
	private static void obradiPredmet(Connection conn, ArrayList<Statistika> statistika) throws SQLException{
		String query = "SELECT IDPREDMETA, COUNT(*) BROJ_POLOZENIH " + 
				"FROM DA.ISPIT I " + 
				"WHERE OCENA > 5 AND STATUS = 'o' AND " + 
				"      IDPREDMETA NOT IN ( " + 
				"        SELECT UP.IDPREDMETA " + 
				"        FROM DA.UNETIPREDMETI UP " + 
				"      ) " + 
				"GROUP BY IDPREDMETA";
		
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		
		while (result.next()) {
			int idpredmeta = result.getInt(1);
			int broj_polozenih = result.getInt(2);
			
//			System.out.println(idpredmeta + " " + broj_polozenih);
			
		    statistika.add(new Statistika(idpredmeta, broj_polozenih));
			
//			System.out.println();
		}
		
		stmt.close();
		result.close();
	} 
	
	private static void unesiStatistiku(Connection conn, Scanner sc, ArrayList<Statistika> statistika) throws SQLException{
		String query = "SELECT * "
				+ "FROM DA.UNETIPREDMETI";
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		ResultSet result = stmt.executeQuery(query);
		
		for (Statistika stat : statistika) {
			result.moveToInsertRow();
			result.updateInt(1, stat.idpredmeta);
			result.updateInt(2, stat.broj_polozenih);
			
			System.out.println("Da li zelite da unesete statistiku? [da/ne] ");
			String odg = sc.next();
			
			if (odg.equalsIgnoreCase("da")) {
				result.insertRow();
				System.out.println("\tUspesno ste uneli statistiku!");
			} else {
				System.out.println("\tPonistili ste unos!");
			}
			
			result.moveToCurrentRow();
		}
		
		stmt.close();
		result.close();
	}
	

}
