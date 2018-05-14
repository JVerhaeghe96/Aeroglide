package be.iesca.aeroglide.controleur;

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.usecase.GestionPilotes;
import be.iesca.aeroglide.usecase.GestionVols;
import be.iesca.aeroglide.usecaseimpl.GestionPilotesImpl;
import be.iesca.aeroglide.usecaseimpl.GestionVolsImpl;

/**
 * @author Julien
 * @version 1
 */
public class GestionnaireUseCases implements GestionPilotes, GestionVols{
    private static final GestionnaireUseCases INSTANCE = new GestionnaireUseCases();
    private GestionPilotes gestionPilotes;
    private GestionVols gestionVols;

    public static GestionnaireUseCases getINSTANCE() {
        return INSTANCE;
    }

    private GestionnaireUseCases(){
        this.gestionPilotes = new GestionPilotesImpl();
        this.gestionVols = new GestionVolsImpl();
    }

    @Override
    public void ajouterPilote(Bundle bundle) {
        this.gestionPilotes.ajouterPilote(bundle);
    }

	@Override
	public void listerPilotes(Bundle bundle) {
		this.gestionPilotes.listerPilotes(bundle);
	}

	@Override
	public void listerPilotesSoldeNegatif(Bundle bundle) {
		this.gestionPilotes.listerPilotesSoldeNegatif(bundle);
	}

	@Override
	public void modifierPilote(Bundle bundle) {
		this.gestionPilotes.modifierPilote(bundle);
	}

    @Override
    public void enregistrerVol(Bundle bundle) {
        this.gestionVols.enregistrerVol(bundle);
    }

    @Override
    public void listerTypePlaneur(Bundle bundle) {
        this.gestionVols.listerTypePlaneur(bundle);
    }
}
