package cas10;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DA.ISPITNIROK")
public class IspitniRok {
	
	@Id
	private IspitniRokPK id;
	
	@Column(name="NAZIV", nullable=false)
	private String naziv;
	
	@Column(name="DATPOCETKA", nullable=false)
	private String datpocetka;
	
	@Column(name="DATKRAJA", nullable=false)
	private String datkraja;

	
	public IspitniRok() {
		
	}
		
	public IspitniRok(int skgodina, String oznakaroka, String naziv, String datpocetka, String datkraja) {
		this.id = new IspitniRokPK(skgodina, oznakaroka);
		this.naziv = naziv;
		this.datpocetka = datpocetka;
		this.datkraja = datkraja;
	}

	public IspitniRokPK getId() {
		return id;
	}

	public void setId(IspitniRokPK id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getDatpocetka() {
		return datpocetka;
	}

	public void setDatpocetka(String datpocetka) {
		this.datpocetka = datpocetka;
	}

	public String getDatkraja() {
		return datkraja;
	}

	public void setDatkraja(String datkraja) {
		this.datkraja = datkraja;
	}

	@Override
	public String toString() {
		return "IspitniRok [id=" + id + ", naziv=" + naziv + ", datpocetka=" + datpocetka + ", datkraja=" + datkraja
				+ "]";
	}
	
	
	
	
}
