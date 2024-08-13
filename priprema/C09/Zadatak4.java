package C09;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Zadatak4 {
  static {
    try {
      Class.forName("com.ibm.db2.jcc.DB2Driver");
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  /*
   * Napisati Java program u kojem se SQL naredbe izvršavaju dinamički koji za svaki predmet koji je obavezan na
   * studijskom programu čiji je identifikator 103, pita korisnika da li želi da poveća broj ESPB bodova za 1. Ukoliko
   * je odgovor korisnika ”da”, izvršava se odgovarajuća naredba. Zadatak uraditi tako da aplikacija radi u višekorisničkom
   * okruženju. Obrada jednog predmeta treba da predstavlja jednu transakciju. Postaviti istek vremena na 5 sekundi.
   * Omogućiti da drugi korisnici mogu da pristupaju predmetima koje ovaj program trenutno obrađuje.*/
  public static void main(String[] args) {
    String url = "jdbc:db2://localhost:50000/stud2020";

    try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
         Scanner sc = new Scanner(System.in)) {
      conn.setAutoCommit(false);
      conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

      Statement katanac = conn.createStatement();
      katanac.execute("SET CURRENT LOCK TIMEOUT 5");

      String query =
              "SELECT NAZIV, ESPB, ID " +
                      "FROM DA.PREDMET P " +
                      "WHERE P.ID IN ( " +
                      "        SELECT IDPREDMETA " +
                      "        FROM DA.PREDMETPROGRAMA PP " +
                      "        WHERE PP.IDPROGRAMA = 103 AND VRSTA = 'obavezan' " +
                      "    )";

      Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
      ResultSet result = otvoriKursor(stmt, query);

      boolean imaSledeci = true;
      ArrayList<Integer> obradjeniPredmeti = new ArrayList<>();

      try {
        while (true) {
          try {
            imaSledeci = result.next();

          } catch (SQLException e) {
            if (e.getErrorCode() >= -913 && e.getErrorCode() <= -911) {
              result.close();

              result = obradiCekanje(conn, stmt, query);
              continue;
            }
            throw e;
          }

          if (!imaSledeci)
            break;

          String naziv = result.getString(1);
          short espb = result.getShort(2);
          int id = result.getInt(3);

          if (!obradjeniPredmeti.contains(id)) {
            System.out.println(naziv + " " + espb);
            System.out.println("Da li selite da uvecat espb? [da/ne]");

            String odg = sc.next();

            if (odg.equalsIgnoreCase("da")) {
              try {
                result.updateShort(2, (short) (espb + 1));
                result.updateRow();
              } catch (SQLException e) {
                if (e.getErrorCode() >= -913 && e.getErrorCode() <= -911) {
                  result.close();
                  result = obradiCekanje(conn, stmt, query);
                  continue;
                }

                throw e;
              }

              System.out.println("Uspesno azuriranje!");
            }

            obradjeniPredmeti.add(id);

            conn.commit();

            System.out.println("Zavrsi obradu? [da/ne]");
            odg = sc.next();

            if (odg.equalsIgnoreCase("da"))
              break;
          }
        }
      } catch (Exception e) {
        conn.rollback();
        throw e;
      } finally {
        katanac.execute("SET CURRENT LOCK TIMEOUT NULL");
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

  private static ResultSet otvoriKursor(Statement stmt, String query) throws SQLException {
    return stmt.executeQuery(query);
  }

  private static ResultSet obradiCekanje(Connection conn, Statement stmt, String query) throws SQLException {

    System.out.println("Objekat je trenutno zakljucan....");

    try {
      conn.rollback();
    } catch (SQLException e) {

    }

    return otvoriKursor(stmt, query);
  }
}
