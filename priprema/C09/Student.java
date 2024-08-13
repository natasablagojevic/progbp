package C09;

public class Student {
  private int indeks;
  private String ime;
  private String prezime;

  public int getIndeks() {
    return indeks;
  }

  public void setIndeks(int indeks) {
    this.indeks = indeks;
  }

  public String getIme() {
    return ime;
  }

  public void setIme(String ime) {
    this.ime = ime;
  }

  public String getPrezime() {
    return prezime;
  }

  public void setPrezime(String prezime) {
    this.prezime = prezime;
  }

  @Override
  public String toString() {
    return this.indeks + " " + this.ime + " " + this.prezime;
  }
}
