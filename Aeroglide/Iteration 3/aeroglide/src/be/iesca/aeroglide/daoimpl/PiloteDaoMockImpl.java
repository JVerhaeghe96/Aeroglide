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
	private Map<String, Pilote> mapPilotes;
	
	/*
		constructeur
		TreeMap triée sur l'id des pilotes
	*/
	public PiloteDaoMockImpl(){
		Comparator<String> comp = new ComparateurPilotes();
		this.mapPilotes = new TreeMap<>(comp);
	}
	
	@Override
	public boolean ajouterPilote(Pilote pilote) {
		try {
			//	On vérifie si le pilote n'est pas déjà présent
			if (this.mapPilotes.containsKey(pilote.getEmail()))
				return false;
			//	Puisqu'il n'est pas présent, on lui donne un id
			pilote.setIdPilote(currentId);
			//	Ajout du pilote
			this.mapPilotes.put(pilote.getEmail(), pilote);
			//	On incrémente l'id
			currentId++;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	private class ComparateurPilotes implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			return s2.compareTo(s1);
		}
	}
}
