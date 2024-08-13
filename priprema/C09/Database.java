package C09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database implements AutoCloseable{
  protected Connection conn;
  protected String name;
  protected String username;
  protected String password;
  protected String url;

  public void connect() throws SQLException {
    if (this.conn != null) {
      this.conn = DriverManager.getConnection(url, username, password);
      this.conn.setAutoCommit(false);

      System.out.println("Uspesno povezivanje na bazu!");
    }
  }

  public void disconnect(boolean success) throws SQLException {
    if (this.conn != null) {
      if (success)
        this.conn.commit();
      else
        this.conn.rollback();

      this.conn.close();
      this.conn = null;
    }
  }

  @Override
  public void close() throws Exception {
    if (this.conn != null)
      this.conn.close();
  }

  public void commit() throws SQLException {
    if (this.conn != null) {
      this.conn.commit();
      System.out.println("Izmene su potvrdjene!");
    }
  }
}
