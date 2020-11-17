package model.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
class MotorBike extends LandVehicle {

    /* Constructeur */
    public MotorBike(String marque, String modele, int puissance, int vitesseMax, int kilometrage) {
        super(marque, modele, puissance, vitesseMax, kilometrage);
    }

    public MotorBike() {}


    /* Rentable methods */
    @Override
    @Transient
    public float getPrixLocJour() {
        final float prixBase = 45.0f;
        return prixBase * getCoeff();
    }
}
