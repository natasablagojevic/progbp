package cas9;

import java.sql.*;

public class Kostur {

	static {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void main(String []args) {
		String url = "jdbc:db2://localhost:25002/stud2020";
		
		try (Connection conn = DriverManager.getConnection(url, "db2inst3", "12345@Natasa")) {
			conn.setAutoCommit(false);
			
			try {
				
				
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
