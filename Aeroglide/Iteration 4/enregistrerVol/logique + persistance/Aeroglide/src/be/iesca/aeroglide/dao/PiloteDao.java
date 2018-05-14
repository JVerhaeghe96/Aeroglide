/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.dao;
import java.util.List;

import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.Vol;

public interface PiloteDao extends Dao{
	boolean ajouterPilote(Pilote Pilote);
	List<Pilote> listerPilotes();
	List<Pilote> listerPilotesSoldeNegatif();
	boolean modifierPilote(Pilote pilote);
	
}
