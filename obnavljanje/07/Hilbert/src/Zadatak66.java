import java.sql.*;

public class Zadatak66 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jcc:db2://localhost:50000/stud2020";

        try (Connection connection = DriverManager.getConnection(url)) {

            String query = "SELECT * FROM DA.ISPITNIROK ORDER BY NAZIV";

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet result = statement.executeQuery(query);

            result.afterLast();

            while (result.previous()) {
                int godina = result.getInt(1);
                String oznaka = result.getString(2);
                String naziv = result.getString(3);
                Date datPocetka = result.getDate(4);
                Date datKraja = result.getDate(5);

                System.out.println(godina + " " + oznaka + " " + naziv + " " + datPocetka + " " + datKraja + "");
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("SQLCODE: " + e.getErrorCode() + "\n" + "SQLSTATE: " + e.getSQLState() + "\n"
                    + "PORUKA: " + e.getMessage());

            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
