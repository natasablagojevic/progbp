package C07;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Zadatak4 {

    /*
     * Napisati Java program u kojem se naredbe izvršavaju dinamički koji sa standardnog ulaza učitava ceo broj N i izdvaja indeks, ime, prezime
     *  i naziv studijskog programa svih studenata koji su položili tačno N predmeta, kao i spisak tih predmeta (naziv i ocena).
     * */
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

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            // ucitavanje upita iz datoteke
            int x = sc.nextInt();
            String upit = ucitajUpit("./C07/upit.sql");
            PreparedStatement stmt = conn.prepareStatement(upit);
            stmt.setInt(1, x);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " +
                        result.getString(3) + " " + result.getString(4) + " " +
                        result.getString(5) + " " + result.getShort(6)) ;
            }

            result.close();
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

    private static String ucitajUpit(String putanja) throws FileNotFoundException {

        StringBuilder upit = new StringBuilder("");

        try (Scanner sc = new Scanner(new File(putanja))) {
            while (sc.hasNextLine()) {
                String linija = sc.nextLine();
                upit.append(linija).append(" ");
            }
        }

        return upit.toString();
    }
}
