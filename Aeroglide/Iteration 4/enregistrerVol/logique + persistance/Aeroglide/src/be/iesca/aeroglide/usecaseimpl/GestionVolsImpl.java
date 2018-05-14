package be.iesca.aeroglide.usecaseimpl;

import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.daoimpl.DaoFactory;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.usecase.GestionVols;

public class GestionVolsImpl implements GestionVols {
    private VolDao volDao;

    public GestionVolsImpl(){
        this.volDao = (VolDao) DaoFactory.getInstance().getDaoImpl(VolDao.class);
    }

    @Override
    public void enregistrerVol(Bundle bundle) {

    }

    @Override
    public void listerTypePlaneur(Bundle bundle) {

    }
}
