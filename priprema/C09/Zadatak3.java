package C09;

import java.sql.*;
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
/* Napisati Java program u kojem se SQL naredbe izvršavaju dinamički koji pronalazi indekse i nazive predmeta za sva
polaganja koja su bila neuspešna. Sortirati podatke po indeksu rastuće. Obezbediti da aplikacija briše podatke o
najviše 10 studenata. Jednu transakciju čine brisanja za sve pronađene studente. Prilikom obrade podataka, ispisati
informacije o indeksu studenta, a zatim prikazati nazive predmeta za obrisana polaganja tog studenta. Nakon brisanja
podataka o jednom studentu, upitati korisnika da li želi da poništi izmene za tog studenta (voditi računa da brisanja
za sve prethodne studente ostanu nepromenjena).*/
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String query = "SELECT INDEKS, (SELECT P.NAZIV FROM DA.PREDMET P WHERE P.ID = I.IDPREDMETA) " +
                    "FROM DA.ISPIT I " +
                    "WHERE OCENA > 5 AND STATUS = 'o' " +
                    "ORDER BY  INDEKS ";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet result = stmt.executeQuery(query);

            int i = 0;
            int prethodniIndeks = 0;
            Savepoint sp = null;

            while (result.next()) {
//                System.out.println(result.getInt(1) + " " + result.getString(2));
                int indeks = result.getInt(1);
                String predmet = result.getString(2);

                if (indeks != prethodniIndeks) {
                    sp = conn.setSavepoint();

                    if (i != 0) {
                        System.out.println("Da li zelite da ponistite brisanja? [da/ne]");
                        String odg = sc.next();

                        if (odg.equalsIgnoreCase("da")) {
                            conn.rollback(sp);
                            System.out.println("Ponistena su brisanja!");
                        } else {
                            conn.releaseSavepoint(sp);
                        }
                    }

                    if (i == 10)
                        break;

                    System.out.println(indeks);
                    i++;
                    sp = conn.setSavepoint();
                }

                System.out.println("\t"+ predmet);

                result.deleteRow();
                System.out.println("Obrisano je polaganje!");

                if (i == 10)
                    break;

                prethodniIndeks = indeks;
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
