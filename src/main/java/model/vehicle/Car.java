package model.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
class Car extends LandVehicle {
    @Column
    private int seatingCapacity;

    /* Constructeur */
    public Car(String marque, String modele, int puissance, int vitesseMax, int kilometrage, int seatingCapacity) {
        super(marque, modele, puissance, vitesseMax, kilometrage);
        this.seatingCapacity = seatingCapacity;
    }


    /* Rentable methods */
    @Override
    @Transient
    public float getPrixLocJour() {
        final float prixBase = 80.0f;
        return prixBase * getCoeff();
    }


    /* Getters / Setters */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
