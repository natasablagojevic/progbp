package C08;

import java.sql.*;
import java.util.Scanner;

public class Zadatak9 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
/*
* Napisati Java program u kojem se naredbe izvršavaju dinamički koji briše sve nepoložene ispite u školskoj godini
* koja se zadaje sa standarnog ulaza. Nakon svakog brisanja ispita, ispisati naredne informacije o njemu na standardni
* izlaz: indeks, oznaku roka, školsku godinu i identifikator predmeta.
* */
    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection conn = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in)) {

            int g = sc.nextInt();

            String query =
                    "SELECT INDEKS, OZNAKAROKA, SKGODINA, IDPREDMETA " +
                    "FROM DA.ISPIT " +
                    "WHERE OCENA = 5 AND STATUS = 'o' AND SKGODINA = ?";

            PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, g);

            ResultSet result = stmt.executeQuery();

            String queryDelete =
                    "DELETE FROM DA.ISPIT WHERE CURRENT OF " + result.getCursorName();
            Statement stmtDelete = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) +
                        " " + result.getShort(3) + " " + result.getInt(4));

//                result.deleteRow();
                stmtDelete.executeUpdate(queryDelete);

                System.out.println("Red je obrisan!");
            }

            stmt.close();
            stmtDelete.close();
            result.close();

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
