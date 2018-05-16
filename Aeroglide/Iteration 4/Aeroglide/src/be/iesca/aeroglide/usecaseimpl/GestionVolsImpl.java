package be.iesca.aeroglide.usecaseimpl;

import java.sql.Date;
import java.util.List;

import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.daoimpl.DaoFactory;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;
import be.iesca.aeroglide.usecase.GestionVols;

public class GestionVolsImpl implements GestionVols {
    private VolDao volDao;

    public GestionVolsImpl(){
        this.volDao = (VolDao) DaoFactory.getInstance().getDaoImpl(VolDao.class);
    }

    @Override
    public void enregistrerVol(Bundle bundle) {
    	Vol vol=(Vol) bundle.get(Bundle.VOL);
    	String message="";
    	boolean reussite=false;
    	if(vol==null){
    		message="Ajout échoué : aucun vol n'a été spécifié";
    	}else if(vol.getCout()<=0){
    		message="Ajout échoué : le coût ne peut pas tre inférieur à 0";
    	}else if(vol.getDuree()<=0){
    		message="Ajout échoué : la durée ne peut pas être inférieure à 0";
    	}else if(vol.getPilote()==null){
    		message="Ajout échoué : aucun pilote n'a été sélectionné";
    	}else if(vol.getPlaneur()==null){
    		message="Ajout échoué : aucun planeur n'a été sélectionné";
    	}else{
    		double solde=vol.getPilote().getSolde();
    		solde-=vol.getCout();
    		vol.getPilote().setSolde(solde);
    		reussite=this.volDao.enregistrerVol(vol);
    		if(reussite){
    			message="Ajout effectué avec succès";
    		}else{
    			message="Ajout échoué";
    		}
    	}
    	
    	bundle.put(Bundle.MESSAGE,message);
    	bundle.put(Bundle.OPERATION_REUSSIE,reussite);
    }

    @Override
    public void listerTypePlaneur(Bundle bundle) {
		List<TypePlaneur> listeTypePlaneur = this.volDao.listerTypePlaneur();
		bundle.put(Bundle.LISTE, listeTypePlaneur);
    }

	@Override
	public void listerVols(Bundle bundle) {
		Date date = (Date) bundle.get(Bundle.DATE);
		String message = "";
		boolean listeOk = false;
		List<Vol> listeVols = null;

		if(date == null){
			message = "Échec : aucune date n'a été sélectionnée";
			listeOk = false;
		}else{
			listeVols = this.volDao.listerVols(date);
			if(listeVols == null){
				listeOk = false;
			}else if(listeVols.isEmpty()){
				listeOk = false;
				message = "Liste vide";
			}else if(listeVols.size() == 1){
				listeOk = true;
				message = "Il y a un vol";
			}else{
				listeOk = true;
				message = "Il y a " + listeVols.size() + " vols";
			}
		}

		bundle.put(Bundle.LISTE, listeVols);
		bundle.put(Bundle.OPERATION_REUSSIE, listeOk);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void listerDates(Bundle bundle) {
		List<Date> listeDates = this.volDao.listerDates();
		if(listeDates.size()==0){
			bundle.put(Bundle.MESSAGE,"Aucun vol n'a été enregistré");
			bundle.put(Bundle.OPERATION_REUSSIE,false);
		}
		else{
			bundle.put(Bundle.LISTE, listeDates);	
			bundle.put(Bundle.OPERATION_REUSSIE,true);
		}

	}
}
