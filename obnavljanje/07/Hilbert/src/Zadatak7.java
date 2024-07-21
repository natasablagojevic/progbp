import java.sql.*;
import java.util.Scanner;

public class Zadatak7 {
    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jcc:db2://loclalhost:50000/stud2020";

        try (Connection connection = DriverManager.getConnection(url);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Unesite X i Y: ");
            int x = sc.nextInt();
            int y = sc.nextInt();

            String query = "SELECT * " +
                    "FROM DA.PREDMET " +
                    "FOR UPDATE OF ESPB";

            Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                if (result.getInt(4) == x) {
                    result.updateInt(4, y);
                    result.updateRow();
                }
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("CODE: " + e.getErrorCode() + "\nMessage: " + e.getMessage() + "\nSTATE" + e.getSQLState());

            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
