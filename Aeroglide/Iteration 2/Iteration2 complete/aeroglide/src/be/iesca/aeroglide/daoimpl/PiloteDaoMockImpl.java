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
	private static int currentId=1;
	private Map<Integer, Pilote> mapPilotes;
	
	/*
		constructeur
		TreeMap triée sur l'id des pilotes
	*/
	public PiloteDaoMockImpl(){
		Comparator<Integer> comp = new ComparateurPilotes();
		this.mapPilotes = new TreeMap<Integer, Pilote>(comp);
	}
	
	@Override
	public boolean ajouterPilote(Pilote pilote) {
		try {
			//	On vérifie si le pilote n'est pas déjà présent
			if (this.mapPilotes.containsKey(pilote.getIdPilote()))
				return false;
			//	Puisqu'il n'est pas présent, on lui donne un id
			pilote.setIdPilote(currentId);
			//	Ajout du pilote
			this.mapPilotes.put(pilote.getIdPilote(), pilote);
			//	On incrémente l'id
			currentId++;
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
