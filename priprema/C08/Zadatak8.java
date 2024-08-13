package C08;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Zadatak8 {

    private static class Statistike {
        public int IDPREDMETA;
        public int BROJPOLOZENIH;


        public Statistike(int idPredmeta, int brojPolozenih) {
            this.IDPREDMETA = idPredmeta;
            this.BROJPOLOZENIH = brojPolozenih;
        }

        @Override
        public String toString() {
            return this.IDPREDMETA + " " + this.BROJPOLOZENIH;
        }
    }

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

/*
*  Napisati Java program u kojem se naredbe izvršavaju dinamički koji:

Kreira tabelu UNETIPREDMETI čije su kolone: (1) identifikator predmeta i (2) broj položenih ispita za taj predmet.
Postaviti odgovarajuće primarne i strane ključeve.
Za svaki predmet koji nije prethodno obrađen (tj. koji se ne nalazi u tabeli UNETIPREDMETI) pronalazi statistiku
koja se sastoji od njegovog identifikator i broj položenih ispita.
Za svaku pronađenu statistiku ispisuje podatke na standardni izlaz i pita korisnika da li želi da unete statistiku
u tabelu UNETIPREDMETI. Ukoliko korisnik potvrdi, potrebno je uneti statistiku u datu tabelu i ispisati poruku
o uspehu. U suprotnom, ispisati poruku da je korisnik poništio unos.
*/
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";
        ArrayList<Statistike> statistika = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            kreirajtabelu(conn);
            sakupiStatistiku(conn, statistika);




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

    private static void kreirajtabelu(Connection conn) throws SQLException {
        String query =
                "CREATE TABLE DA.UNETIPREDMETI ( " +
                "    IDPREDMETA INTEGER NOT NULL, " +
                "    BROJ_POLOZENIH INTEGER NOT NULL, " +
                "    PRIMARY KEY (IDPREDMETA), " +
                "    FOREIGN KEY (IDPREDMETA) REFERENES DA.PREDMET " +
                ")";

        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            if (e.getErrorCode() != -601)
                throw e;
        }

        stmt.close();
    }

    private static void sakupiStatistiku(Connection conn, ArrayList<Statistike> stat) throws SQLException {
        String query =
                "SELECT IDPREDMETA, COUNT(*) " +
                "FROM DA.ISPIT  " +
                "WHERE OCENA > 5 AND STAUS = 'o' AND IDPREDMETA NOT IN (SELECT IDPREDMETA FROM DA.UNETIPREDMETI) " +
                "GROUP BY IDPREDMETA ";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            stat.add(new Statistike(result.getInt(1), result.getInt(2)));
//            System.out.println(result.getInt(1) + " " + result.getInt(2));
        }
    }

    private static void unesiStatistike(Connection conn, ArrayList<Statistike> stat, Scanner sc) throws SQLException {

        String query = "SELECT * FROM DA.UNETIPREDMETI";
        Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ResultSet result = stmt.executeQuery(query);

        for (Statistike s: stat) {
            System.out.println(s);

            System.out.println("Da li zelite da unesete statistiku? [da/ne]");
            String odg = sc.next();

            result.moveToInsertRow();

            result.updateInt(1, s.IDPREDMETA);
            result.updateInt(2, s.BROJPOLOZENIH);

            if (odg.equalsIgnoreCase("da")) {
                result.insertRow();
                System.out.println("Uspesan unos!");
            }

            System.out.println("Nastavi? [da/ne]");
            odg = sc.next();

            if (odg.equalsIgnoreCase("ne"))
                break;
        }

        stmt.close();
    }
}
