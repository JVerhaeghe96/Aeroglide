/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.dao;
import java.util.List;

import be.iesca.aeroglide.domaine.Pilote;

public interface PiloteDao extends Dao{
	boolean ajouterPilote(Pilote Pilote);
	List<Pilote> listerPilotes();
	
}
