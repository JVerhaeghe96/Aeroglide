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
        Pilote pilote = (Pilote) bundle.get(Bundle.PILOTE);

        if(pilote == null){
            message = "Ajout échoué : aucun pilote n'a été spécifié.";
        }else if(pilote.getNom() == null || pilote.getNom().isEmpty()){
            message = "Ajout échoué : le nom du pilote n'a pas été spécifié.";
        }else if(pilote.getPrenom() == null || pilote.getPrenom().isEmpty()){
            message = "Ajout échoué : le prénom du pilote n'a pas été spécifié.";
        }else if(pilote.getEmail() == null || pilote.getEmail().isEmpty()){
            message = "Ajout échoué : l'email du pilote n'a pas été spécifiée.";
        }else if(!pilote.getEmail().matches("^[^\\W][a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*\\.[a-zA-Z]{2,4}$")){
            message = "Erreur : format de l'email invalide.";
        }else if(pilote.getRue() == null || pilote.getRue().isEmpty()){
            message = "Ajout échoué : la rue du pilote n'a pas été spécifiée.";
        }else if(pilote.getNumero() == null || pilote.getNumero().isEmpty()){
            message = "Ajout échoué : le numéro de maison / appartement du pilote n'a pas été spécifié.";
        }else if(pilote.getLocalite() == null || pilote.getLocalite().isEmpty()){
            message = "Ajout échoué : la ville du pilote n'a pas été spécifiée.";
        }else if(pilote.getCodePostal() <= 0){
            message = "Ajout échoué : le code postal du pilote n'a pas été spécifié.";
        }else if(pilote.getNoGsm() == null){
            message = "Ajout échoué : le numéro de gsm du pilote n'a pas été spécifié.";
        }else if(!pilote.getNoGsm().isEmpty() && !pilote.getNoGsm().matches("^(((\\+|00)\\d\\d)|0)\\d\\d\\d/\\d\\d\\.\\d\\d\\.\\d\\d$")){
            message = "Erreur : format du numéro de gsm invalide.";
        }else if(pilote.getSolde() <= 0){
            message = "Ajout échoué : le solde du pilote n'a pas été spécifié.";
        }else{
            ajoutReussi = this.piloteDao.ajouterPilote(pilote);
            if(ajoutReussi)
                message = "Ajout effectué avec succès.";
            else
                message = "Ajout échoué : ce pilote a d�j� �t� enregistr�.";
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
		} else if (listePilotes.isEmpty())
			message = "Liste vide";
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
		} else if (listePilotesSoldeNegatif.isEmpty())
			message = "Liste vide";
		else if (listePilotesSoldeNegatif.size() == 1)
			message = "Il y a un pilote";
		else
			message = "Il y a " + listePilotesSoldeNegatif.size() + " pilotes";
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTE, listePilotesSoldeNegatif);
	}
}
