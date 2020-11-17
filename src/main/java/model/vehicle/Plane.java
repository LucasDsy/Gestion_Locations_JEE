package model.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
class Plane extends AirVehicle {

    /* Constructeur */
    public Plane(String marque, String modele, int puissance, int vitesseMax, int vitesseCroisiere, int nbMoteurs, int heuresVol) {
        super(marque, modele, puissance, vitesseMax, vitesseCroisiere, nbMoteurs, heuresVol);
    }

    public Plane() {}


    /* Rentable methods */
    @Override
    @Transient
    public float getPrixLocJour() {
        final float prixBase = 150.0f;
        return prixBase * getCoeff();
    }
}
