package be.iesca.aeroglide.usecaseimpl;

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.usecase.GestionPilotes;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.daoimpl.DaoFactory;

/**
 * @author Julien
 * @version 1
 */
public class GestionPilotesImpl implements GestionPilotes {
    private PiloteDao piloteDao;

    public GestionPilotesImpl(){
        this.piloteDao = (PiloteDao) DaoFactory.getInstance().getDao();
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
        }else if(pilote.getRue() == null || pilote.getRue().isEmpty()){
            message = "Ajout échoué : la rue du pilote n'a pas été spécifiée.";
        }else if(pilote.getNumero() == null || pilote.getNumero().isEmpty()){
            message = "Ajout échoué : le numéro de maison / appartement du pilote n'a pas été spécifié.";
        }else if(pilote.getLocalite() == null || pilote.getLocalite().isEmpty()){
            message = "Ajout échoué : la ville du pilote n'a pas été spécifiée.";
        }else if(pilote.getCodePostal() <= 0){
            message = "Ajout échoué : le code postal du pilote n'a pas été spécifié.";
        }else if(pilote.getNoGsm() == null || pilote.getNoGsm().isEmpty()){
            message = "Ajout échoué : le numéro de gsm du pilote n'a pas été spécifié.";
        }else if(pilote.getSolde() <= 0){
            message = "Ajout échoué : le solde du pilote n'a pas été spécifié.";
        }else{
            ajoutReussi = this.piloteDao.ajouterPilote(pilote);
            if(ajoutReussi)
                message = "Ajout effectué avec succès.";
            else
                message = "Ajout échoué.";
        }

        bundle.put(Bundle.MESSAGE, message);
        bundle.put(Bundle.OPERATION_REUSSIE, ajoutReussi);
    }
}
