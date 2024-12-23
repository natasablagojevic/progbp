package cas10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DA.STUDIJSKIPROGRAM")
public class StudijskiProgram {
	
	@Id
	private int id;
	
	@Column(name="OZNAKA", nullable=false)
	private String oznaka;
	
	@Column(name="NAZIV", nullable=false)
	private String naziv;
	
	@Column(name="IDNIVOA", nullable=false)
	private int nivo;
	
	@Column(name="OBIMESPB", nullable=false)
	private int obimespb;
	
	@Column(name="ZVANJE", nullable=false)
	private String zvanje;
	
	@Column(name="OPIS", nullable=true)
	private String opis;

	public StudijskiProgram() {
		
	}
	
	public StudijskiProgram(int id, String oznaka, String naziv, int nivo, int obimespb, String zvanje, String opis) {
		this.id = id;
		this.oznaka = oznaka;
		this.naziv = naziv;
		this.nivo = nivo;
		this.obimespb = obimespb;
		this.zvanje = zvanje;
		this.opis = opis;
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

	public int getNivo() {
		return nivo;
	}

	public void setNivo(int nivo) {
		this.nivo = nivo;
	}

	public int getObimespb() {
		return obimespb;
	}

	public void setObimespb(int obimespb) {
		this.obimespb = obimespb;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "StudijskiProgram [id=" + id + ", oznaka=" + oznaka + ", naziv=" + naziv + ", nivo=" + nivo
				+ ", obimespb=" + obimespb + ", zvanje=" + zvanje + ", opis=" + opis + "]";
	}
}
