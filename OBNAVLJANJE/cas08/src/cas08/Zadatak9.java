package cas08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak9 {


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
				Scanner sc= new Scanner(System.in)) {
			
			int skgodina = sc.nextInt();
			
			String query = "SELECT INDEKS, OZNAKAROKA, SKGODINA, IDPREDMETA " + 
					"FROM DA.ISPIT " + 
					"WHERE OCENA = 5 AND STATUS = 'o' AND SKGODINA = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			stmt.setInt(1, skgodina);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				int indeks = result.getInt(1);
				String oznakaroka = result.getString(2);
				int godina = result.getInt(3);
				int idpredmeta = result.getInt(4);
				
				result.deleteRow();
				
				System.out.println(indeks + " " + oznakaroka + " " + 
				godina + " " + idpredmeta);
			}
			
			
			
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
