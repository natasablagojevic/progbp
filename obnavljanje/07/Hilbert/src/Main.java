import java.sql.*;

public class Main {

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection con = DriverManager.getConnection(url, "natasa", "12345@Natasa"))  {

            // ovde sve radimo!!!

            String query = "SELECT oznaka, naziv " +
                    "FROM da.predmet " +
                    "WHERE espb > 20";

            Statement naredba = con.createStatement();
            ResultSet kursor = naredba.executeQuery(query);

            while (kursor.next()) {
                String oznaka = kursor.getString(1);
                String naziv = kursor.getString(2);

                System.out.print(oznaka + " " + naziv);
            }

            kursor.close();
            naredba.close();

        } catch (SQLException e) {
            e.printStackTrace();

            System.err.println("SQLCODE: " + e.getErrorCode() + ", message: " + e.getMessage() + ", SQLSTATE: " + e.getSQLState());

            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(2);
        }

    }
}