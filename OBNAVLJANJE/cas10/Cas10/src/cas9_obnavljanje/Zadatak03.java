package cas9_obnavljanje;

import java.sql.*;
import java.util.Scanner;

public class Zadatak03 {
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
	
	private static void pronadjiIObrisi(Connection conn) throws SQLException{
		String query = "SELECT INDEKS, (SELECT NAZIV FROM DA.PREDMET P WHERE P.ID = I.IDPREDMETA) " + 
				"FROM DA.ISPIT I " + 
				"WHERE OCENA = 5 AND STATUS = 'o' " + 
				"ORDER BY INDEKS";
		
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	
		ResultSet result = stmt.executeQuery(query);
		
		int prethodni_indeks = -1;
		
		while (result.next()) {
			
		}
		
		stmt.close();
		result.close();
	}
	
}
