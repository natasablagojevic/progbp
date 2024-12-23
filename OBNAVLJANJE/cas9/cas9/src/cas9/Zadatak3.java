package cas9;

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
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa")) {
			conn.setAutoCommit(false);
			
			try (Scanner sc = new Scanner(System.in)) {
				
				obrisiNeuspesnoPolaganje(conn, sc);
				
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
			
			
			
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
	
	private static void obrisiNeuspesnoPolaganje(Connection conn, Scanner sc) throws SQLException{
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String query = "SELECT      INDEKS, " + 
				"            (SELECT NAZIV FROM DA.PREDMET WHERE ID = I.IDPREDMETA) " + 
				"FROM        DA.ISPIT I " + 
				"WHERE       OCENA = 5 AND " + 
				"            STATUS = 'o'  " + 
				"ORDER BY    INDEKS";
		ResultSet result = stmt.executeQuery(query);
		
		Savepoint s = null;
		int prethodniIndeks = -1;
		int brojStudenata = 0;
		
		while (result.next()) {
			int indeks = result.getInt(1);
			String naziv = result.getString(2);
			
			if (indeks != brojStudenata) {
				if (0 != brojStudenata) {
					System.out.println("Da li zelite da ponistite brisanje? [da/ne]");
					String odg = sc.next();
					
					if (odg.equalsIgnoreCase("da")) {
						conn.rollback(s);
					} else {
						conn.releaseSavepoint(s);
					}
				}
				
				if (10 == brojStudenata) {
					break;
				}
				
				s = conn.setSavepoint();
				prethodniIndeks = indeks;
				brojStudenata++;
				System.out.println("Brisem nepolozena polaganja za " + brojStudenata + " indeks: " + indeks);
				
			}
			
			System.out.printf("    %s\n", naziv);
			result.deleteRow();
		}
		
		stmt.close();
		result.close();
		
	}
	
	
}
