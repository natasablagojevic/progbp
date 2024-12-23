package cas9;

import java.sql.*;
import java.util.Scanner;

public class Zadatak1 {
	
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
			
			try {	
				
				Integer indeks = pronadjiMaxIndeks(conn);
				System.out.println("indeks: " + indeks);
				
				obrisiIspite(conn, indeks);
				
				indeks = pronadjiMaxIndeks(conn);
				
				System.out.println("indeks: " + indeks);
				
				potvridPonistiIzmene(conn, sc);
				
				indeks = pronadjiMaxIndeks(conn);
				
				System.out.println("indeks: " + indeks);
				
				
				
				
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
			
			
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
	
	private static Integer pronadjiMaxIndeks(Connection conn) throws SQLException, Exception{
		String query = "SELECT MAX(INDEKS) FROM DA.ISPIT";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		Integer indeks = null;
		
		if (result.next()) {
			indeks = result.getInt(1);
		} else {
			throw new Exception("Nema ispita u bazi!");
		}
		
		stmt.close();
		result.close();
		
		return indeks;
	}
	
	private static void obrisiIspite(Connection conn, Integer indeks) throws SQLException {
		String query = "DELETE FROM DA.ISPIT WHERE INDEKS = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, indeks);
		
		stmt.executeUpdate();
		
		System.out.println("Obrisani su ispiti za studenta: " + indeks);	
	}
	
	private static void potvridPonistiIzmene(Connection conn, Scanner sc) throws SQLException{
		System.out.println("Da li zelite da potvrdite izmene? [da/ne]");
		String odg = sc.next();
		
		if (odg.equalsIgnoreCase("da")) {
			conn.commit();
			System.out.println("Izmene su uspesno potvrdjene");
		} else {
			conn.rollback();
			System.out.println("Izmene su ponistene");
		}
	}
	
	


}
