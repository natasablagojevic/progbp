package C08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak10 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*Napisati Java program u kojem se naredbe izvršavaju dinamički koji izdvaja indeks, ime, prezime i naziv studijskog
     programa svih studenata koji su položili tačno N predmeta, kao i spisak tih predmeta (naziv i ocena). Broj N
     se učitava sa standardnog ulaza. Za svakog studenta napraviti posebnu sekciju izveštaja.*/

    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            String queryStudent =
                    "SELECT D.INDEKS, IME, PREZIME, NAZIV " +
                    "FROM DA.DOSIJE D JOIN DA.STUDIJSKIPROGRAM SP ON D.IDPROGRAMA = SP.ID " +
                    "JOIN DA.ISPIT I ON D.INDEKS = I.INDKES " +
                    "WHERE OENA > 5 AND STATUS = 'o' " +
                    "GROUP BY D.INDEKS, IME, PREZIME, NAZIV " +
                    "HAVING COUNT(*) = ?"
                    ;
            int n = sc.nextInt();

            PreparedStatement stmtStudent = conn.prepareStatement(queryStudent);
            stmtStudent.setInt(1, n);
            ResultSet resultStudent = stmtStudent.executeQuery();

            String queryPredmet =
                    "SELECT NAZIV, OCENA " +
                    "FROM DA.ISPIT I JOIN DA.PREDMET P ON I.IDPREDMETA = P.ID " +
                    "WHERE INDEKS = ? AND OCENA > 5 AND STATUS = 'o' "
                    ;

            while (resultStudent.next()) {
                System.out.println(resultStudent.getInt(1) + " " + resultStudent.getString(2) +
                        " " + resultStudent.getString(3) + " " + resultStudent.getString(4));

                PreparedStatement stmtPredmet = conn.prepareStatement(queryPredmet);
                stmtPredmet.setInt(1, resultStudent.getInt(1));
                ResultSet resultPredmet = stmtPredmet.executeQuery();

                while (resultPredmet.next()) {
                    System.out.println("\t" + resultPredmet.getString(1) + " " + resultPredmet.getShort(2));
                }

                stmtPredmet.close();
                resultPredmet.close();
            }

            stmtStudent.close();
            resultStudent.close();


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
