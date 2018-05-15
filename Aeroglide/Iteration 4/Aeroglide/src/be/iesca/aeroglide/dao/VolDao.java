package be.iesca.aeroglide.dao;

import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

import java.sql.Date;
import java.util.List;

public interface VolDao extends Dao{
    boolean enregistrerVol(Vol vol);
    boolean modifierSoldePilote(Pilote pilote);
    List<TypePlaneur> listerTypePlaneur();
    List<Vol> listerVols(Date date);
    List<Date> listerDates();
}
