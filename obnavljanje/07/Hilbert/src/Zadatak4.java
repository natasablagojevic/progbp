import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Zadatak4 {
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

            int n = sc.nextInt();

            String query = loadQuery("~/Desktop/natasa/Fakultet/4.god/8.sem/progbp/obnavljanje/07/1.txt");

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, n);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println("Indeks: " + result.getInt(1) + " " + result.getString(2) + " " + result.getString(3) + " " +
                        "" + result.getString(4) + " " + result.getString(5) + " " + result.getInt(6));

            }




        } catch (SQLException e) {
            e.printStackTrace();

            System.err.println("SQLCODE: " + e.getErrorCode() + ", message: " + e.getMessage() + ", SQLSTATE: " + e.getSQLState());

            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(2);
        }

    }

    private static String loadQuery(String path) throws FileNotFoundException {
        StringBuilder query = new StringBuilder("");

        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                query.append(line).append("\n");
            }
        }

        return query.toString();
    }

}
