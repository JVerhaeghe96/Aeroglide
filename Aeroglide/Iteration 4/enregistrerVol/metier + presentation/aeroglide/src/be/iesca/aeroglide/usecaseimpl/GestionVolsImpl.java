package be.iesca.aeroglide.usecaseimpl;

import java.util.List;

import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.daoimpl.DaoFactory;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
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
    		message="aucun vol n'a été spécifié";
    	}else if(vol.getCout()<=0){
    		message="Le coût ne peut pas être inférieur à 0";
    	}else if(vol.getDuree()<=0){
    		message="La durée ne peut pas être inférieure à 0";
    	}else if(vol.getPilote()==null){
    		message="Aucun pilote n'a été sélectionné";
    	}else if(vol.getPlaneur()==null){
    		message="Aucun planeur n'a été sélectionné";
    	}else{
    		reussite=this.volDao.enregistrerVol(vol);
    		if(reussite){
    			message="vol ajouté";
    		}else{
    			message="échec de l'ajout";
    		}
    	}
    	
    	bundle.put(Bundle.MESSAGE,message);
    	bundle.put(Bundle.OPERATION_REUSSIE,reussite);
    }

    @Override
    public void listerTypePlaneur(Bundle bundle) {
		List<TypePlaneur> listeTypePlaneur = null;
		listeTypePlaneur = this.volDao.listerTypePlaneur();
		bundle.put(Bundle.LISTE, listeTypePlaneur);
    }
}
