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
    		message="aucun vol n'a �t� sp�cifi�";
    	}else if(vol.getCout()<=0){
    		message="Le co�t ne peut pas �tre inf�rieur � 0";
    	}else if(vol.getDuree()<=0){
    		message="La dur�e ne peut pas �tre inf�rieure � 0";
    	}else if(vol.getPilote()==null){
    		message="Aucun pilote n'a �t� s�lectionn�";
    	}else if(vol.getPlaneur()==null){
    		message="Aucun planeur n'a �t� s�lectionn�";
    	}else{
    		reussite=this.volDao.enregistrerVol(vol);
    		if(reussite){
    			message="vol ajout�";
    		}else{
    			message="�chec de l'ajout";
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
