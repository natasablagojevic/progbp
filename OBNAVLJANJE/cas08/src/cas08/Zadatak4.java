package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak4 {

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
			
			int N = sc.nextInt();
			
			String query = "WITH POMOCNA AS ( " + 
					"  SELECT INDEKS, COUNT(*) BROJPOLOZENIH " + 
					"  FROM DA.ISPIT I " + 
					"  WHERE OCENA > 5 AND STATUS = 'o' " + 
					"  GROUP BY INDEKS " + 
					"  HAVING COUNT(*) > ? " + 
					") " + 
					"SELECT D.INDEKS, IME, PREZIME, SP.NAZIV, P.NAZIV, I.OCENA " + 
					"FROM  DA.DOSIJE D JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA " + 
					"      JOIN POMOCNA POM ON POM.INDEKS = D.INDEKS " + 
					"      JOIN DA.ISPIT I ON I.INDEKS = POM.INDEKS " + 
					"      JOIN DA.PREDMET P ON P.ID = I.IDPREDMETA " + 
					"WHERE I.OCENA > 5 AND I.STATUS = 'o'  " + 
					"ORDER BY D.INDEKS, IME, PREZIME, SP.NAZIV, P.NAZIV";
			
			
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,  N);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				System.out.println(
						result.getInt(1) + " " + result.getString(2) + " " +
						result.getString(3) + " " + result.getString(4) + " " + 
						result.getString(5) + " " + result.getInt(6)
						);
			}
			
			stmt.close();
			result.close();
			
			
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
	
}
