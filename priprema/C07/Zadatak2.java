package C07;

/*
* Napisati Java program u kojem se naredbe izvršavaju dinamički koji u
* tabelu PREDMET unosi podatak o predmetu čiji je identifikator 2001, oznaka Pred1, naziv Predmet 1 i nosi 5 ESPB bodova.
*
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Zadatak2 {
    static  {
        try {
            Class.forName("com.ibm.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] argv) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa")) {

            String query = "INSERT INTO DA.PREDMET VALUES(2001, 'Pred1', 'Predmet 1', 5)";

            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(query);
            System.out.println("Broj unetih redova je: " + result);
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
