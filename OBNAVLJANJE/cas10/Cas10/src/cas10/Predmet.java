package cas10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DA.PREDMET")
public class Predmet {
	
	@Id
	private int id;
	
	@Column(name="OZNAKA", nullable=false)
	private String oznaka;
	
	@Column(name="NAZIV", nullable=false)
	private String naziv;
	
	@Column(name="ESPB", nullable=false)
	private int espb;

	public Predmet() {
		
	}
	
	public Predmet(int id, String oznaka, String naziv, int espb) {
		this.id = id;
		this.oznaka = oznaka;
		this.naziv = naziv;
		this.espb = espb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	@Override
	public String toString() {
		return "Predmet [id=" + id + ", oznaka=" + oznaka + ", naziv=" + naziv + ", espb=" + espb + "]";
	}
	
	
}
