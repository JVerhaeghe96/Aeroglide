/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.daoimpl;
import java.util.*;
import java.util.stream.Collectors;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

public class DaoMockImpl implements PiloteDao, VolDao {
	private static int currentIdPilote=1;
	private static int currentIdVol=1;
	private Map<String, Pilote> mapPilotes;
	private Map<Integer, Vol> mapVol;

	/*
		constructeur
		TreeMap triée sur l'id des pilotes
	*/
	public DaoMockImpl(){
		Comparator<String> comp = new ComparateurPilotes();
		this.mapPilotes = new TreeMap<>(comp);
		this.mapVol = new TreeMap<>();
	}
	
	@Override
	public boolean ajouterPilote(Pilote pilote) {
		try {
			//	On vérifie si le pilote n'est pas déjà présent
			if (this.mapPilotes.containsKey(pilote.getEmail()))
				return false;
			//	Puisqu'il n'est pas présent, on lui donne un id
			pilote.setIdPilote(currentIdPilote);
			//	Ajout du pilote
			this.mapPilotes.put(pilote.getEmail(), pilote);
			//	On incrémente l'id
			currentIdPilote++;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	@Override
	public List<Pilote> listerPilotes(){
		Collection<Pilote> liste = this.mapPilotes.values();
		
		return new ArrayList<>(liste);
	}

	@Override
	public List<Pilote> listerPilotesSoldeNegatif() {
		Collection<Pilote> liste = this.mapPilotes.values();

		liste = liste.stream().filter(p -> p.getSolde() < 0).sorted().collect(Collectors.toList());
		
		return new ArrayList<>(liste);
	}
	
	@Override
	public boolean modifierPilote(Pilote pilote){
		try {
			if (this.mapPilotes.remove(pilote.getEmail()) == null)
				return false;
			this.mapPilotes.put(pilote.getNom(),pilote);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean enregistrerVol(Vol vol) {
		try{
			vol.setId(currentIdVol);

			this.mapVol.put(currentIdVol, vol);

			if(!modifierSoldePilote(vol.getPilote()))
				return false;

			currentIdVol++;
		}catch(Exception e){
			return false;
		}

		return true;
	}

	@Override
	public boolean modifierSoldePilote(Pilote pilote) {
		return this.modifierPilote(pilote);
	}

	@Override
	public List<TypePlaneur> listerTypePlaneur() {
		List<TypePlaneur> liste = new ArrayList<>();

		liste.add(new TypePlaneur("bois & toile", 17, 25));
		liste.add(new TypePlaneur("biplace", 30, 25));
		liste.add(new TypePlaneur("plastique", 27, 25));

		return liste;
	}

	private class ComparateurPilotes implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}
}
