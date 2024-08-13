package C09;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stud2020 extends Database{
    public Stud2020() throws SQLException {
      this.name = "stud2020";
      this.url = "jdbc:db2://localhost:50000/stud2020";
      this.username = "natasa";
      this.password = "12345@Natasa";
      connect();
    }

    public void ispisiStudijskePrograme() throws SQLException {
      String query = "SELECT ID, NAZIV, OBIMESPB, ZVANJE " +
              "FROM DA.STUDIJSKIPROGRAM ";
      Statement stmt = this.conn.createStatement();
      ResultSet result = stmt.executeQuery(query);

      while (result.next()) {
        StudijskiProgram sp = new StudijskiProgram(result.getInt(1), result.getString(2), result.getShort(3), result.getString(4));
        System.out.println(sp);
      }
    }


}
