package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak19 {

	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void main(String []args) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa");
				Scanner sc = new Scanner(System.in)) {
			
			System.out.println("Unesite oznaku predmeta: ");
			String oznaka = sc.next();
			
			String query = "SELECT NAZIV, ESPB "
					+ "FROM DA.PREDMET "
					+ "WHERE OZNAKA = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, oznaka);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				String naziv = result.getString(1);
				int espb = result.getInt(2);
				
				result.deleteRow();
				
				System.out.println("Obrisali ste: " + naziv + " " + espb + "predmet!");
			}
			
			stmt.close();
			result.close();
			
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
}
