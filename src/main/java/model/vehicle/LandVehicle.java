package model.vehicle;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy= InheritanceType.JOINED)
abstract class LandVehicle extends Vehicle {
    private static final float TYPEVAL = 0.8f; // valeur du type de véhicule
    private int kilometers;

    /* Constructeur */
    public LandVehicle(String brand, String model, int horsePower, int maxSpeed, int kilometers) {
        super(brand, model, horsePower, maxSpeed);
        this.kilometers = kilometers;
    }

    public LandVehicle() {}

    /* Protected methods */
    @Override
    @Transient
    protected float getCoeff() {
        return ((float) 1/(getHorsePower() * getState().valeur)) + TYPEVAL;
    }


    /* Getters / Setters */
    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
}