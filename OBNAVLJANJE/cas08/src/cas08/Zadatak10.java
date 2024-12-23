package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak10 {

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
			
			
		prodjiKrozSpoljasnjiKursor(conn, sc);
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(
					"SQLCODE: " + e.getErrorCode() + "\n" + 
					"SQLSTATE: " + e.getSQLState()  + "\"n" + 
					"MESSAGE: " + e.getMessage()
					);
			
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void prodjiKrozSpoljasnjiKursor(Connection conn, Scanner sc) throws SQLException {
		
		System.out.println("Unesite broj N: ");
		int N = sc.nextInt();
		
		String query = "SELECT D.INDEKS, D.IME, D.PREZIME, SP.NAZIV  " + 
				"FROM DA.DOSIJE D JOIN DA.ISPIT I ON  D.INDEKS = I.INDEKS " + 
				"     JOIN DA.STUDIJSKIPROGRAM SP ON SP.ID = D.IDPROGRAMA " + 
				"WHERE I.OCENA > 5 AND I.STATUS = 'o' " + 
				"GROUP BY D.INDEKS, D.IME, D.PREZIME, SP.NAZIV " + 
				"HAVING COUNT(*) = ? ";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		
		stmt.setInt(1, N);
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			int indeks = result.getInt(1);
			String ime = result.getString(2);
			String prezime = result.getString(3);
			String nazivsp = result.getString(4);
			
			System.out.println(indeks + " " + ime + " " + prezime + " " + nazivsp + " :");
			
			prodjiKrozUnutrasnjiKursor(conn, indeks);
		}
		
		stmt.close();
		result.close();
	}
	
	private static void prodjiKrozUnutrasnjiKursor(Connection conn, int indeks) throws SQLException{
		String query = "SELECT NAZIV, OCENA " + 
				"FROM DA.ISPIT I JOIN DA.PREDMET P ON P.ID = I.IDPREDMETA " + 
				"WHERE I.INDEKS = ? AND OCENA > 5 AND STATUS = 'o'";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, indeks);
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			String naziv = result.getString(1);
			int ocena = result.getInt(2);
			
			System.out.println("\t" + naziv + "\t" + ocena);
		}
		
		stmt.close();
		result.close();
		
	}
	
	
}
