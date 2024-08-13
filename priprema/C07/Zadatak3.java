package C07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    /*
    *  Napisati Java program u kojem se naredbe izvršavaju dinamički koji učitava dva cela broja, X i Y,
    * a zatim svim predmetima koji imaju X ESPB bodova, postavlja broj bodova na Y. Nakon toga ispisati broj ažuriranih redova.
    * */
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Unesite x: ");
            int x = sc.nextInt();

            System.out.println("Unesite y: ");
            int y = sc.nextInt();

            String query = "UPDATE DA.PREDMET " +
                    "SET ESPB = ? " +
                    "WHERE ESPB = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(2, x);
            stmt.setInt(1, y);

            int brojAzuriranihRedova = stmt.executeUpdate();
            System.out.println("Azurirano je: " + brojAzuriranihRedova);

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
