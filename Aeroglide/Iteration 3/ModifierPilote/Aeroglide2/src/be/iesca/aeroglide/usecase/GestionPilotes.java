package be.iesca.aeroglide.usecase;

import be.iesca.aeroglide.domaine.Bundle;

/**
 * @author Julien
 * @version 1
 */
public interface GestionPilotes {
    void ajouterPilote(Bundle bundle);
    void listerPilotes(Bundle bundle);
    void listerPilotesSoldeNegatif(Bundle bundle);
    void modifierPilote(Bundle bundle);
}
