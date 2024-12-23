package cas8_obnavljanje;

import java.sql.*;
import java.util.Scanner;

public class Zadatak08 {
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
			
			napraviTabelu(conn);
			
			sakupiStatistiku(conn, sc);
			
			
			
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
	
	private static void napraviTabelu(Connection conn) throws SQLException, Exception{
		String query = "CREATE TABLE IF NOT EXISTS DA.UNETIPREDMETI ( " + 
				"  IDPREDMETA INT NOT NULL PRIMARY KEY, " + 
				"  BROJ_POLOZENIH INT,  " + 
				"  CONSTRAINT fk_unetipredmeti FOREIGN KEY(IDPREDMETA) REFERENCES DA.PREDMET(ID) " + 
				")";
		
		Statement stmt = conn.createStatement();
		
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new SQLException("TABELA NIJE NAPRAVLJENA!");
		}
		
		stmt.close();
	}
	
	private static void sakupiStatistiku(Connection conn, Scanner sc) throws SQLException, Exception{
		String query = "SELECT I.IDPREDMETA, COUNT(*) POLOZENO " + 
				"FROM DA.ISPIT I " + 
				"WHERE I.STATUS = 'o' AND I.OCENA > 5 AND I.IDPREDMETA NOT IN ( " + 
				"  SELECT UP.IDPREDMETA " + 
				"  FROM DA.UNETIPREDMETI UP  " + 
				") " + 
				"GROUP BY I.IDPREDMETA ";
		
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while (result.next()) {
			int idpredmeta = result.getInt(1);
			int polozeno = result.getInt(2);
			
			System.out.println(idpredmeta + " " + polozeno);
			
			System.out.println("Da li zelite da uneste statistiku u DA.UNETIPREDMETI? [da/ne]");
			String odgovor = sc.next();
			
			if (odgovor.equalsIgnoreCase("da")) {
				unesiStatistiku(conn, idpredmeta, polozeno);
			}
			
			System.out.println("Nastavi? [da/ne]");
			odgovor = sc.next();
			
			if (odgovor.equalsIgnoreCase("ne")) 
				break;
			
		}
		
		
		stmt.close();
		result.close();		
	}
	
	private static void unesiStatistiku(Connection conn, int idpredmeta, int broj_polozenih) throws SQLException, Exception {
		String query = "INSERT INTO DA.UNETIPREDMETI "
				+ "VALUES (?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, idpredmeta);
		stmt.setInt(2, broj_polozenih);
		
		try {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("NIJE UNETA NOVA VREDNOST!");
		}
		
		stmt.close();
	}

}
