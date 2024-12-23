package cas8_obnavljanje;

import java.sql.*;

public class Zadatak05 {
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
			
			String query = "SELECT IME, PREZIME, DATDIPLOMIRANJA " + 
					"FROM DA.DOSIJE " + 
					"WHERE POL = 'z' AND IDPROGRAMA = 202";
			
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			
			while (result.next()) {
				String ime = result.getString(1);
				String prezime = result.getString(2);
				String datdiplomiranja = result.getString(3);
				
				System.out.print(ime + "\t\t\t" + prezime + "\t\t\t");
				
				if (result.wasNull()) {
					System.out.println("Nije diplomirala");
				} else {
					System.out.println(datdiplomiranja);
				}
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
