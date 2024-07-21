import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kostur {

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
