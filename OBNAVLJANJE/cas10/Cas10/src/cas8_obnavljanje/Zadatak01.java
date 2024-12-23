package cas8_obnavljanje;

import java.sql.*;

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
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa")) {
			
			String query = "SELECT OZNAKA, NAZIV "
					+ "FROM DA.PREDMET "
					+ "WHERE ESPB > 20";
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				
				String oznaka = result.getString(1);
				String naziv = result.getString(2);
				
				System.out.println(oznaka + " " + naziv);
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
