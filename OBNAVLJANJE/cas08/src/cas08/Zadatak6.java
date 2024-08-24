package cas08;

import java.sql.*;

public class Zadatak6 {

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
			
			String query = "SELECT SKGODINA, OZNAKAROKA, NAZIV, DATPOCETKA, DATKRAJA "
					+ "FROM DA.ISPITNIROK";
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery(query);
			
			result.afterLast();
			
			while (result.previous()) {
				int skgodina = result.getInt(1);
				String oznakaroka = result.getString(2);
				String naziv = result.getString(3);
				Date datpocetka = result.getDate(4);
				Date datkraja = result.getDate(5);
				
				System.out.println(skgodina + " " + oznakaroka + " " + 
				naziv + " " + datpocetka + " " + datkraja);
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
