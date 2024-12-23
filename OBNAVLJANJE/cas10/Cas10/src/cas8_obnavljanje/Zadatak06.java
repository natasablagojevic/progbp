package cas8_obnavljanje;

import java.sql.*;

public class Zadatak06 {
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
			
			String query = "SELECT SKGODINA, OZNAKAROKA, NAZIV, DATPOCETKA, DATKRAJA " + 
					"FROM DA.ISPITNIROK " + 
					"ORDER BY NAZIV";
			
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet result = stmt.executeQuery(query);
			
			result.afterLast();
			
			while (result.previous()) {
				int skgodina = result.getInt(1);
				String oznakaroka = result.getString(2);
				String naziv = result.getString(3);
				String datpocetka = result.getString(4);
				String datkraja = result.getString(5);
				
				System.out.println(skgodina + " " + oznakaroka + " " + naziv + " " + 
				datpocetka + " " + datkraja);
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
