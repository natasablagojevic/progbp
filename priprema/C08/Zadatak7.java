package C08;

import com.ibm.db2.jcc.am.ResultSet;

import java.sql.*;
import java.util.Scanner;

public class Zadatak7 {
    /*
    * Napisati Java program u kojem se naredbe izvršavaju dinamički koji ispisuje sadržaj tabele PREDMET i, u istoj
    * iteraciji, ukoliko je broj bodova jednak X, postavlja se broj bodova na Y i ispisuje se poruka da je promena
    * izvršena, zajedno sa ispisom novih podataka o tom predmetu. Brojevi X i Y se učitavaju sa standardnog ulaza.*/

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
//            System.exit(1);
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Unesite x i y: ");
            int x = sc.nextInt();
            int y = sc.nextInt();

            String query = "SELECT * FROM DA.PREDMET";
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = (ResultSet) stmt.executeQuery(query);

            String queryUpdate = "UPDATE DA.PREDMET " +
                    "SET ESPB = ? " +
                    "WHERE CURRENT OF " + result.getCursorName();

            PreparedStatement stmt1 = conn.prepareStatement(queryUpdate);
            stmt1.setInt(1, y);

            while (result.next()) {
                System.out.println("ID: " + result.getInt(1) + ", oznaka: " + result.getString(2) +
                        ", naziv: " + result.getString(3) + ", espb: " + result.getShort(4));

                if (result.getInt(4) == x) {
//                    result.updateInt(4, y);
                    stmt1.executeUpdate();
                    System.out.println("\tESPB je azurirano: " + result.getShort(4));
                }
            }

            result.close();
            stmt1.close();
            stmt.close();



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
