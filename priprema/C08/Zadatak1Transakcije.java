package C08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak1Transakcije {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
/*Napisati Java program u kojem se SQL naredbe izvršavaju dinamički koji redom:

    - Pronalazi i ispisuje najveći indeks iz tabele ISPIT.
    - Briše studenta sa pronađenim indeksom iz tabele ISPIT i ispisuje poruku korisniku o uspešnosti brisanja.
    - Ponovo pronalazi i ispisuje najveći indeks iz tabele ISPIT.
    - Pita korisnika da li želi da potvrdi ili poništi izmene. U zavisnosti od korisnikovog odgovora,
      aplikacija potvrđuje ili poništava izmene uz ispisivanje poruke korisniku.
    - Ponovo pronalazi i ispisuje najveći indeks iz tabele ISPIT.
*/
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {
            conn.setAutoCommit(false);

            try {
                Integer indeks = pronadjiMaxIndeks(conn);
                obrisi(conn, indeks);

                indeks = pronadjiMaxIndeks(conn);

                potvrdiIliPonisti(conn, sc);

                indeks = pronadjiMaxIndeks(conn);

                System.out.println();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

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

    private static Integer pronadjiMaxIndeks(Connection conn) throws SQLException, Exception {
        String query = "SELECT MAX(INDEKS) FROM DA.ISPIT";
        Integer indeks = null;

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        if (result.next()) {
            indeks = result.getInt(1);
            System.out.println(indeks.toString());
        } else {
            System.out.println("Nema studenta");
            throw new Exception("Nema studenata!");
        }

        stmt.close();
        result.close();

        return indeks;
    }

    private static void obrisi(Connection conn, Integer indeks) throws SQLException {
        String query = "DELETE FROM DA.ISPIT WHERE INDEKS = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, indeks);

        int obrisanoRedova = stmt.executeUpdate();

        System.out.println("Uspesno je obrisano " + obrisanoRedova);

        stmt.close();
    }

    private static void potvrdiIliPonisti(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Potvrdi? [da/ne] ");
        String odg = sc.next();

        if (odg.equalsIgnoreCase("da")) {
            conn.commit();
            System.out.println("Potvrdjeno");
        } else {
            conn.rollback();
            System.out.println("Ponisteno");
        }
    }
}
