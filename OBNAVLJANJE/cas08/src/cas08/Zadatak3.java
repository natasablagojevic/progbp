package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak3 {

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
			
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			
			String query = "UPDATE DA.PREDMET "
					+ "SET ESPB = ? "
					+ "WHERE ESPB = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setInt(2, x);
			stmt.setInt(1, y);
			
			int result = stmt.executeUpdate();
			
			System.out.println("Azurirano je : " + result);
			
			stmt.close();
			
			
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
