package be.iesca.aeroglide.usecaseimpl;

import be.iesca.aeroglide.daoimpl.DaoFactory;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.usecase.GestionPilotes;
import be.iesca.aeroglide.domaine.Pilote;

import java.util.List;

import be.iesca.aeroglide.dao.PiloteDao;

/**
 * @author Julien
 * @version 1
 */
public class GestionPilotesImpl implements GestionPilotes {
    private PiloteDao piloteDao;

    public GestionPilotesImpl(){
        this.piloteDao = (PiloteDao) DaoFactory.getInstance().getDaoImpl(PiloteDao.class);
    }

    @Override
    public void ajouterPilote(Bundle bundle) {
        String message = "";
        boolean ajoutReussi = false;
        this.cvPilote(bundle);
        if((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)){
            Pilote pilote = (Pilote) bundle.get(Bundle.PILOTE);
            ajoutReussi = this.piloteDao.ajouterPilote(pilote);
            if(ajoutReussi)
                message = "Ajout effectué avec succès.";
            else
                message = "Ajout échoué : ce pilote a d�j� �t� enregistr�.";
        }else{
            message = "Ajout échoué : "+ bundle.get(Bundle.MESSAGE);
        }


        bundle.put(Bundle.MESSAGE, message);
        bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
    }

	@Override
	public void listerPilotes(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<Pilote> listePilotes = null;
		listePilotes = this.piloteDao.listerPilotes();
		if (listePilotes==null) {
			listeOk = false;
		} else if (listePilotes.isEmpty()){
		    message = "Liste vide";
		    listeOk = false;
        }
		else if (listePilotes.size() == 1)
			message = "Il y a un pilote";
		else
			message = "Il y a " + listePilotes.size() + " pilotes";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listePilotes);
	}

	@Override
	public void listerPilotesSoldeNegatif(Bundle bundle) {
		boolean listeOk = true;
		String message = "";
		List<Pilote> listePilotesSoldeNegatif = null;
		listePilotesSoldeNegatif = this.piloteDao.listerPilotesSoldeNegatif();
		if (listePilotesSoldeNegatif==null) {
			listeOk = false;
		} else if (listePilotesSoldeNegatif.isEmpty()){
		    message = "Liste vide";
		    listeOk = false;
        }
		else if (listePilotesSoldeNegatif.size() == 1)
			message = "Il y a un pilote";
		else
			message = "Il y a " + listePilotesSoldeNegatif.size() + " pilotes";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listePilotesSoldeNegatif);
	}

    @Override
    public void modifierPilote(Bundle bundle) {
        boolean modificationReussie = false;
        String message = "";

        this.cvPilote(bundle);
        if((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)){
            Pilote pilote = (Pilote) bundle.get(Bundle.PILOTE);
            modificationReussie = this.piloteDao.modifierPilote(pilote);
            if(modificationReussie)
                message = "Modification des informations du pilote réussie";
            else
                message = "Modification des informations du pilote échouée";
        }else{
            message = "Modification échouée : "+ bundle.get(Bundle.MESSAGE);
        }

        bundle.put(Bundle.MESSAGE, message);
        bundle.put(Bundle.OPERATION_REUSSIE, modificationReussie);

    }

    private void cvPilote(Bundle bundle){
        Pilote pilote = (Pilote) bundle.get(Bundle.PILOTE);
        String message = "";
        boolean reussite = false;
        if(pilote == null){
            message = "aucun pilote n'a été spécifié.";
        }else if(pilote.getNom() == null || pilote.getNom().isEmpty()){
            message = "le nom du pilote n'a pas été spécifié.";
        }else if(pilote.getPrenom() == null || pilote.getPrenom().isEmpty()){
            message = "le prénom du pilote n'a pas été spécifié.";
        }else if(pilote.getEmail() == null || pilote.getEmail().isEmpty()){
            message = "l'email du pilote n'a pas été spécifiée.";
        }else if(!pilote.getEmail().matches("^[^\\W][a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\.[a-zA-Z]{2,4}$")){
            message = "Erreur : format de l'email invalide.";
        }else if(pilote.getRue() == null || pilote.getRue().isEmpty()){
            message = "la rue du pilote n'a pas été spécifiée.";
        }else if(pilote.getNumero() == null || pilote.getNumero().isEmpty()){
            message = "le numéro de maison / appartement du pilote n'a pas été spécifié.";
        }else if(pilote.getLocalite() == null || pilote.getLocalite().isEmpty()){
            message = "la ville du pilote n'a pas été spécifiée.";
        }else if(pilote.getCodePostal() <= 0){
            message = "le code postal du pilote n'a pas été spécifié.";
        }else if(pilote.getNoGsm() == null){
            message = "le numéro de gsm du pilote n'a pas été spécifié.";
        }else if(!pilote.getNoGsm().isEmpty() && !pilote.getNoGsm().matches("^(((\\+|00)\\d\\d)|0)\\d\\d\\d/\\d\\d\\.\\d\\d\\.\\d\\d$")){
            message = "Erreur : format du numéro de gsm invalide.";
        }else{
            reussite = true;
        }

        bundle.put(Bundle.MESSAGE, message);
        bundle.put(Bundle.OPERATION_REUSSIE, reussite);
    }
}
