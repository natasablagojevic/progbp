package cas8_obnavljanje;

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
			
			System.out.println("Unesite X:");
			int x = sc.nextInt();
			
			System.out.println("Unesite Y:");
			int y = sc.nextInt();
			
			String query = "UPDATE DA.PREDMET "
					+ "SET ESPB = ? "
					+ "WHERE ESPB = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, y);
			stmt.setInt(2, x);
			
			int result = stmt.executeUpdate();
			
			System.out.println(result);
			
			
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
