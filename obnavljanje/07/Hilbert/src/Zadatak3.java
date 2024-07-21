import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Zadatak3 {

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:db2://localhost:50000/stud2020";

        try (Connection con = DriverManager.getConnection(url, "natasa", "12345@Natasa");
             Scanner sc = new Scanner(System.in))  {

            int x, y;
            System.out.println("Unesite x: ");
            x = sc.nextInt();

            System.out.println("Unesite y: ");
            y = sc.nextInt();

            String query = "UPDATE da.predmet " +
                    "SET espb = ? " +
                    "WHERE espb = ?";

            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(2, x);
            statement.setInt(1, y);

            int result = statement.executeUpdate();

            System.out.println("Broj azuriranih redova: " + result);

            statement.close();

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
