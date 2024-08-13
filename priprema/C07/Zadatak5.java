package C07;

import java.sql.*;

public class Zadatak5 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*Napisati Java program u kojem se naredbe izvršavaju dinamički koji izdvaja ime, prezime i datum diplomiranja za
    sve studentkinje (pol = ‘z’) programa čiji je identifikator 202 iz tabele DOSIJE. Ukoliko datum diplomiranja nije
    poznat, ispisati Nije diplomirala
    */

    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa")) {

            String query = "SELECT IME, PREZIME, DATDIPLOMIRANJA " +
                    "FROM DA.DOSIJE " +
                    "WHERE IDPROGRAMA = 202 AND POL = 'z'";

            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                String ime = result.getString(1);
                String prezime = result.getString(2);
                Date datDiplomiranja = result.getDate(3);

                boolean wasNull = result.wasNull();

                System.out.println(ime + " " + prezime + " " + (wasNull ? "Nije diplomirala" : datDiplomiranja));
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
}
