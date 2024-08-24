package cas08;

import java.sql.*;
import java.sql.Connection;

public class Zadatak2 {

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
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa")) {
			
			String query = "INSERT INTO DA.PREDMET VALUES (2002, 'Pred2', 'Predmet 2', 5)";
			
			Statement stmt = conn.createStatement();
			
//			PreparedStatement stmt = conn.prepareStatement(query);
			
//			stmt.setInt(1, 2001);
//			stmt.setString(2, "Pred1");
//			stmt.setString(3, "Predmet 1");
//			stmt.setInt(4, 5);
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Azurirano je : " + result);
			
			
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
