package cas9_obnavljanje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zadatak02 {
	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static class Student {
		public int indeks;
		public String ime;
		public String prezime;
		public int espb;
		
		public Student(int indeks, String ime, String prezime, int espb) {
			this.indeks = indeks;
			this.ime = ime;
			this.prezime = prezime;
			this.espb = espb;
		}
		
		@Override
		public String toString() {
			return this.indeks + " " + this.ime + " " + this.prezime + " " + this.espb;
		}
	}
	
	public static void main(String[] args) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa");
				Scanner sc = new Scanner(System.in)) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			try {
				
				List<Student> studenti = new ArrayList<>();
				
				String query = "SELECT INDEKS, IME, PREZIME, ESPB "
						+ "FROM DA.UKUPNIBODOVI "
						+ "FETCH FIRST 10 ROWS ONLY";
				
				Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				ResultSet result = stmt.executeQuery(query);
				
				while (result.next()) {
					int indeks = result.getInt(1);
					String ime = result.getString(2);
					String prezime = result.getString(3);
					int espb = result.getInt(4);
					
					Student s = new Student(indeks, ime, prezime, espb);
					
					System.out.println(s);
					System.out.println();
					
					System.out.println("Da li studentu [" + s + " ] zelite da dodelite pocasnih 10 espb? [da/ne] ");
					String odg = sc.next();
					
					if (odg.equalsIgnoreCase("da")) {
						result.updateInt(4, espb + 10);
						result.updateRow();
						
						
						System.out.println(s + " USPESNO STE AZURIRALI BODOVE");
						System.out.println();
						
						conn.commit();
					}

					studenti.add(s);
					
					System.out.println("----------------------------------------------------");
				}
				
				for (Student s : studenti) 
					System.out.println(s);
				
				
				
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

}
