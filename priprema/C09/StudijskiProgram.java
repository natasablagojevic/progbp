package C09;

public class StudijskiProgram {
  private int id;
  private String naziv;
  private short obimEspb;
  private String zvanje;

  public StudijskiProgram(int id, String naziv, short obimEspb, String zvanje) {
    this.id = id;
    this.naziv = naziv;
    this.obimEspb = obimEspb;
    this.zvanje = zvanje;
  }

  public int getId() {
    return id;
  }

  public String getNaziv() {
    return naziv;
  }

  public short getObimEspb() {
    return obimEspb;
  }

  public String getZvanje() {
    return zvanje;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public void setObimEspb(short obimEspb) {
    this.obimEspb = obimEspb;
  }

  public void setZvanje(String zvanje) {
    this.zvanje = zvanje;
  }

  @Override
  public String toString() {
    return this.id + " " + this.naziv + " " + this.obimEspb + " " + this.zvanje;
  }
}
