/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.dao;
import be.iesca.aeroglide.domaine.Pilote;

public interface PiloteDao extends Dao{
	boolean ajouterPilote(Pilote Pilote);
	
	
}
