package cas9_obnavljanje;

import java.sql.*;
import java.util.Scanner;

public class Zadatak01 {
	
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
			conn.setAutoCommit(false);
			
			try {
				
				int indeks = pronadjiMaxIndeks(conn);
				
				if (indeks != -1)
					obrisiStudenta(conn, indeks);
				
				indeks = pronadjiMaxIndeks(conn);
				
				System.out.println("Da li zelite da potvrdite ili ponistite izmene? [da/ne] ");
				String odg = sc.next();
				
				if (odg.equalsIgnoreCase("da")) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
				indeks = pronadjiMaxIndeks(conn);
				
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
	
	private static int pronadjiMaxIndeks(Connection conn) throws SQLException{
		String query = "SELECT MAX(INDEKS) "
				+ "FROM DA.ISPIT";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		int indeks = -1;
		
		if (result.next()) {
			indeks = result.getInt(1);
			
			System.out.println(indeks);
		} else {
			throw new SQLException("Nema ispita u bazi");
		}
		
		stmt.close();
		result.close();
		
		return indeks;
	}
	
	private static void obrisiStudenta(Connection conn, int indeks) throws SQLException{
		String query = "DELETE FROM DA.ISPIT WHERE INDEKS = ?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, indeks);
		
		try {
			stmt.executeUpdate();
			System.out.println("USPESNO STE OBRISALI STUDENTA!");
		} catch (SQLException e) {
			throw new SQLException("STUDENT NE POSTOJI!");
		}
		
		stmt.close();
	}
	
	
	
}
