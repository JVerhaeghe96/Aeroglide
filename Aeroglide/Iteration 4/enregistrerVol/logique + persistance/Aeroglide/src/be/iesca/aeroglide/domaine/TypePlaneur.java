package be.iesca.aeroglide.domaine;

public class TypePlaneur {
    private String planeur;
    private double tarifHoraire;
    private double coutRemorquage;

    public TypePlaneur(String planeur, double tarifHoraire, double coutRemorquage) {
        this.planeur = planeur;
        this.tarifHoraire = tarifHoraire;
        this.coutRemorquage = coutRemorquage;
    }

    public String getPlaneur() {
        return planeur;
    }

    public void setPlaneur(String planeur) {
        this.planeur = planeur;
    }

    public double getTarifHoraire() {
        return tarifHoraire;
    }

    public void setTarifHoraire(double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }

    public double getCoutRemorquage() {
        return coutRemorquage;
    }

    public void setCoutRemorquage(double coutRemorquage) {
        this.coutRemorquage = coutRemorquage;
    }
}
