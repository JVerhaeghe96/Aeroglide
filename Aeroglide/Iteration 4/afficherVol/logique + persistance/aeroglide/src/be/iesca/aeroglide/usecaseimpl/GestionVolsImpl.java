package be.iesca.aeroglide.usecaseimpl;

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
    		message="Ajout échoué : aucun vol n'a �t� sp�cifi�";
    	}else if(vol.getCout()<=0){
    		message="Ajout échoué : le co�t ne peut pas �tre inf�rieur � 0";
    	}else if(vol.getDuree()<=0){
    		message="Ajout échoué : la dur�e ne peut pas �tre inf�rieure � 0";
    	}else if(vol.getPilote()==null){
    		message="Ajout échoué : aucun pilote n'a �t� s�lectionn�";
    	}else if(vol.getPlaneur()==null){
    		message="Ajout échoué : aucun planeur n'a �t� s�lectionn�";
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
		List<TypePlaneur> listeTypePlaneur = null;
		listeTypePlaneur = this.volDao.listerTypePlaneur();
		bundle.put(Bundle.LISTE, listeTypePlaneur);
    }

	@Override
	public void listerVols(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listerDates(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}
}
