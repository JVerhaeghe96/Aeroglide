package be.iesca.aeroglide.domaine;

import java.sql.Date;

public class Vol {
    private int id;
    private int duree;
    private Date date;
    private double cout;
    private Pilote pilote;
    private TypePlaneur planeur;

    public Vol(int duree, Date date, double cout, Pilote pilote, TypePlaneur planeur) {
        this.duree = duree;
        this.date = date;
        this.cout = cout;
        this.pilote = pilote;
        this.planeur = planeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public TypePlaneur getPlaneur() {
        return planeur;
    }

    public void setPlaneur(TypePlaneur planeur) {
        this.planeur = planeur;
    }

	@Override
	public String toString() {
		return "Vol [id=" + id + ", duree=" + duree + ", date=" + date + ", cout=" + cout + ", pilote=" + pilote
				+ ", planeur=" + planeur + "]";
	}
    
    
}
