package C07;

import java.sql.*;

/*
*  Napisati Java program u kojem se naredbe izvršavaju dinamički koji izlistava oznake i nazive svih predmeta koji imaju više od 20 ESPB bodova.
* */

public class Zadatak1 {
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

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa")) {

            String query = "SELECT OZNAKA, NAZIV " +
                    "FROM DA.PREDMET " +
                    "WHERE ESPB >= 20";

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                String oznaka = result.getString(1);
                String naziv = result.getString(2);

                System.out.println(oznaka + " " + naziv);
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
