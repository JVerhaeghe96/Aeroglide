/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.daoimpl;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.domaine.Pilote;

public class PiloteDaoMockImpl implements PiloteDao {
	private Map<Integer, Pilote> mapPilotes;
	
	public PiloteDaoMockImpl(){
		Comparator<Integer> comp = new ComparateurPilotes();
		this.mapPilotes = new TreeMap<Integer, Pilote>(comp);
	}

	@Override
	public boolean ajouterPilote(Pilote pilote) {
		try {
			if (this.mapPilotes.containsKey(pilote.getNom()))
				return false;
			this.mapPilotes.put(pilote.getIdPilote(), pilote);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	private class ComparateurPilotes implements Comparator<Integer> {
		@Override
		public int compare(Integer id1, Integer id2) {
			return id2-id1;
		}
	}
}
