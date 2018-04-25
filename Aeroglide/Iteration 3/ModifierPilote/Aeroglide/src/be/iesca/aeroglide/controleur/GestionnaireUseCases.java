package be.iesca.aeroglide.controleur;

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.usecase.GestionPilotes;
import be.iesca.aeroglide.usecaseimpl.GestionPilotesImpl;

/**
 * @author Julien
 * @version 1
 */
public class GestionnaireUseCases implements GestionPilotes{
    private static final GestionnaireUseCases INSTANCE = new GestionnaireUseCases();
    private GestionPilotes gestionPilotes;

    public static GestionnaireUseCases getINSTANCE() {
        return INSTANCE;
    }

    private GestionnaireUseCases(){
        this.gestionPilotes = new GestionPilotesImpl();
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

    }
}
