import java.sql.*;

public class Zadatak5 {

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

            String query = "SELECT IME, PREZIME, DATDIPLOMIRANJA " +
                    "FROM DA.DOSIJE " +
                    "WHERE IDPROGRAMA = 202 AND POL='z'";

            Statement statement = con.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String ime = result.getString(1);
                String prezime = result.getString(2);
                String datum = result.getString(3);

                boolean dateExists = result.wasNull();

                System.out.println("Ime: " + ime + ", prezime: " + prezime + ", datum: " + ((dateExists) ? "Nije diplomirala" : datum));
            }

            result.close();
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
