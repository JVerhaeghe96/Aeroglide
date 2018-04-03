/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.domaine;

public class Pilote {
	private int idPilote;
	private String nom;
	private String prenom;
	private String email;
	private String rue;
	private String numero;
	private String localite;
	private int codePostal;
	private String noGsm;
	private double solde;
	
	public Pilote(String nom, String prenom, String email, String rue, String numero, String localite, int codePostal,String noGsm, double solde) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.numero = numero;
		this.localite = localite;
		this.codePostal = codePostal;
		this.noGsm = noGsm;
		this.solde = solde;
	}
	
	public Pilote(Pilote pilote){
		this.nom = pilote.nom;
		this.prenom = pilote.prenom;
		this.email = pilote.email;
		this.rue = pilote.rue;
		this.numero = pilote.numero;
		this.localite = pilote.localite;
		this.codePostal = pilote.codePostal;
		this.noGsm = pilote.noGsm;
		this.solde = pilote.solde;
	}

	public int getIdPilote() {
		return idPilote;
	}

	public void setIdPilote(int idPilote) {
		this.idPilote = idPilote;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getNoGsm() {
		return noGsm;
	}

	public void setNoGsm(String noGsm) {
		this.noGsm = noGsm;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Pilote [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", rue=" + rue + ", numero=" + numero
				+ ", localite=" + localite + ", codePostal=" + codePostal + ", noGsm=" + noGsm + ", solde=" + solde
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilote other = (Pilote) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (localite == null) {
			if (other.localite != null)
				return false;
		} else if (!localite.equals(other.localite))
			return false;
		if (codePostal != 0) {
			if (other.codePostal != 0)
				return false;
		} else if (codePostal!=other.codePostal)
			return false;
		if (noGsm == null) {
			if (other.noGsm != null)
				return false;
		} else if (!noGsm.equals(other.noGsm))
			return false;
		if (solde != 0) {
			if (other.solde != 0)
				return false;
		} else if (solde!=other.solde)
			return false;
		
		return true;
	}
}
