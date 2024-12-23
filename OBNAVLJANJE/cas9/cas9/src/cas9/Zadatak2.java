package cas9;

import java.sql.*;
import java.util.Scanner;

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
			conn.setAutoCommit(false);
			
			try (Scanner sc = new Scanner(System.in)) {
				
				najuspesnijiStudenti(conn, sc);
				
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
	
	private static void najuspesnijiStudenti(Connection conn, Scanner sc) throws SQLException {
		String query = "SELECT INDEKS, IME, PREZIME, ESPB "
				+ "FROM DA.UKUPNIBODOVI "
				+ "FETCH FIRST 10 ROWS ONLY";
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		ResultSet result = stmt.executeQuery(query);
		
		int i = 1;
		
		while (result.next()) {
			int indeks = result.getInt(1);
			String ime = result.getString(2);
			String prezime = result.getString(3);
			int espb = result.getInt(4);
			
			System.out.println("Da li zelite da dodelite 10espb pocasnih? [da/ne]");
			String odg = sc.next();
			
			if (odg.equalsIgnoreCase("da")) {
				result.updateInt(4,  espb+10);
				result.updateRow();
				System.out.println("Uspesno ste azurirali!");
			}
			
			i++;
		}
		
		result.close();
		
		result = stmt.executeQuery(query);
		
		while (result.next()) {
			int indeks = result.getInt(1);
			String ime = result.getString(2);
			String prezime = result.getString(3);
			int espb = result.getInt(4);
			
			System.out.println(indeks + " " + ime + " " + prezime + " " + espb);
		}
		
		stmt.close();
		result.close();
	}

	
}
