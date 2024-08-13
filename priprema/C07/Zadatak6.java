package C07;

import com.ibm.db2.jcc.am.ResultSet;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Zadatak6 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*
    * Napisati Java program u kojem se naredbe izvršavaju dinamički koji ispisuje podatke o ispitnim rokovima
    * koristeći kursor kome je omogućeno kretanje i unazad kroz podatke. Podatke urediti po nazivu rastuće,
    *  ali ih ispisivati opadajuće.*/
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa")) {
            String query = "SELECT * FROM DA.ISPITNIROK ORDER BY NAZIV";

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = (ResultSet) stmt.executeQuery(query);

            result.afterLast();

            while (result.previous()) {
                System.out.println(result.getString(2) + " " + result.getString(3));
            }

            stmt.close();
            result.close();

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
