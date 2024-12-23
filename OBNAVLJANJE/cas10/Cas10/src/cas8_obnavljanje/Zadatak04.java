package cas8_obnavljanje;

import java.sql.*;
import java.util.Scanner;

public class Zadatak04 {
	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa");
				Scanner sc = new Scanner(System.in)) {
			
		pronadjiStudente(conn, sc);			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println(
					"SQLCODE: " + e.getErrorCode() +
					"\nSQLSTATE: " + e.getSQLState() + 
					"\nMESSAGE: " + e.getMessage()
					);
			
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	private static void pronadjiStudente(Connection conn, Scanner sc) throws SQLException{
		
		System.out.println("Unesite broj N: ");
		int n = sc.nextInt();
		
		String query = "WITH POMOCNA AS ( " + 
				"  SELECT INDEKS " + 
				"  FROM DA.ISPIT  " + 
				"  WHERE OCENA > 5 AND STATUS = 'o' " + 
				"  GROUP BY INDEKS  " + 
				"  HAVING COUNT(*) = ? " + 
				") " + 
				"SELECT INDEKS, IME, PREZIME, SP.NAZIV  " + 
				"FROM DA.DOSIJE D JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA " + 
				"WHERE EXISTS (" + 
				"  SELECT * " + 
				"  FROM POMOCNA P " + 
				"  WHERE P.INDEKS = D.INDEKS " + 
				")";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, n);
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			int indeks = result.getInt(1);
			String ime = result.getString(2);
			String prezime = result.getString(3);
			String naziv = result.getString(4);
			
			System.out.println(indeks + " " + ime + " " + prezime + " " + naziv);
			
			ispisiPredmete(conn, indeks);
			
			System.out.println();
		}
		
		stmt.close();
		result.close();
	}
	
	private static void ispisiPredmete(Connection conn, int indeks) throws SQLException {
		String query = "SELECT NAZIV " + 
				"FROM DA.ISPIT I JOIN DA.PREDMET P ON P.ID = I.IDPREDMETA " + 
				"WHERE OCENA > 5 AND STATUS = 'o' AND I.INDEKS = ?";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, indeks);
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			System.out.println("\t" + result.getString(1));
		}
		
		stmt.close();
		result.close();
	}
}

