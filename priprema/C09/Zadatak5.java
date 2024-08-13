package C09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Zadatak5 {
  static {
    try {
      Class.forName("com.ibm.db2.jcc.DB2Driver");
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public static void main(String[] args) {
    String url = "jdbc:db2://localhost:50000/stud2020";

    try (Stud2020 stud2020 = new Stud2020()) {
      stud2020.ispisiStudijskePrograme();
      stud2020.commit();
    } catch (SQLException e) {
      e.printStackTrace();

      System.out.println(
              "SQLCODE: " + e.getErrorCode() + "\n" +
                      "SQLSTATE: " + e.getSQLState() + "\n" +
                      "MESSAGE: " + e.getMessage()
      );

      System.exit(2);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(3);
    }
  }
}
