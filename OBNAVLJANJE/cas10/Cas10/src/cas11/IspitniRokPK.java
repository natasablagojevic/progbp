package cas11;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class IspitniRokPK implements Serializable{
	private static final long serialVersionUID = 1l;
	
	private int skgodina;
	private String oznakaroka;
	
	public IspitniRokPK() {
		
	}
	
	public IspitniRokPK(int skgodina, String oznakaroka) {
		this.skgodina = skgodina;
		this.oznakaroka = oznakaroka;
	}

	public int getSkgodina() {
		return skgodina;
	}

	public void setSkgodina(int skgodina) {
		this.skgodina = skgodina;
	}


	public String getOznakaroka() {
		return oznakaroka;
	}

	public void setOznakaroka(String oznakaroka) {
		this.oznakaroka = oznakaroka;
	}
	

	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		if (!(o instanceof IspitniRokPK))
			return false;
		
		IspitniRokPK irpk = (IspitniRokPK)o;
		
		return Objects.equals(irpk.getSkgodina(), this.getSkgodina()) && Objects.equals(irpk.getOznakaroka(), this.getOznakaroka());			
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.skgodina, this.oznakaroka);
	}

	@Override
	public String toString() {
		return "IspitniRokPK [skgodina=" + skgodina + ", oznakaroka=" + oznakaroka + "]";
	}
}
