package C09;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    /*
    * Napisati Java program u kojem se SQL naredbe izvršavaju dinamički koji iz tabele UKUPNIBODOVI (videti ispod)
    * izdvaja 10 najuspešnijih studenata. Za svakog studenta ispisati podatke iz te tabele i upitati korisnika da li
    * želi da dodeli tom studentu počasnih 10 ESPB. Ukoliko želi, izvršiti odgovarajuću izmenu. Nakon svih izmena,
    * ispisati izveštaj rada u kojem se vide izmene. Sve izmene i prikaz izveštaja implementirati kao jednu transakciju.
    * Omogućiti da nijedan drugi korisnik ne može da vidi izmene tokom rada ovog programa.
    * */
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String query = "SELECT * " +
                    "FROM DA.UKUPNIBODOVI " +
                    "FETCH FIRST 10 ROWS ONLY";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                int espb = result.getInt(4);

                System.out.println(result.getInt(1) + " " + result.getString(2) + " " +
                        result.getString(3) + " " + espb);

                System.out.println("Da li zelite da dodelite pocasne espb? [da/ne]");
                String odg = sc.next();

                if (odg.equalsIgnoreCase("da")) {
                    result.updateInt(4, espb + 10);
                    result.updateRow();

                    System.out.println("Uspesno azuriranje!");
                }
            }

            result.close();

            result = stmt.executeQuery(query);

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " +
                        result.getString(3) + " " + result.getInt(4));
            }

            result.close();
            stmt.close();

            conn.commit();

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
