package cas08;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Zadatak1 {
	
	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String args[]) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa")) {
			
			String query = "SELECT OZNAKA, NAZIV "
					+ "FROM DA.PREDMET "
					+ "WHERE ESPB > 20";
			
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				System.out.println(result.getString(1) + " " + result.getString(2));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(
					"SQLCODE: " + e.getErrorCode() + "\n" +
					"SQLSTATE: " + e.getSQLState() + "\n" +
					"MESSAGE: " + e.getMessage()) ;
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
