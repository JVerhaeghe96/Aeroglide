package be.iesca.aeroglide.usecase;

import be.iesca.aeroglide.domaine.Bundle;

public interface GestionVols {
    void enregistrerVol(Bundle bundle);
    void listerTypePlaneur(Bundle bundle);
}
