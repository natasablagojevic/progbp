package cas10;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DA.NIVOKVALIFIKACIJE")
public class NivoKvalifikacije {
	
	@Id
	private int id;
	
	@Column(name="NAZIV", nullable=true)
	private String naziv;

	
	public NivoKvalifikacije() {
		
	}
		
	public NivoKvalifikacije(int id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "NivoKvalifikacije [id=" + id + ", naziv=" + naziv + "]";
	}
	
}
