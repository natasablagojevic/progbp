package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak7 {

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
			
			String query = "SELECT ID, OZNAKA, NAZIV, ESPB "
					+ "FROM DA.PREDMET";
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				int id = result.getInt(1);
				String oznaka = result.getString(2);
				String naziv = result.getString(3);
				int espb = result.getInt(4);
				
				System.out.println(id + " " + oznaka + " " + naziv + " " + espb);
				
				if (espb == x) {
					result.updateInt(4, y);
					result.updateRow();
					System.out.println(id + " " + oznaka + " " + naziv + " " + espb);
					System.out.println("----------------------------------------------------------");
				}
				
			}
			
			
			result.close();
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
