package model.vehicle;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class LandVehicle extends Vehicle {
    private static final float TYPEVAL = 0.8f; // valeur du type de véhicule
    private int kilometers;

    /* Constructeur */
    public LandVehicle(String brand, String model, int horsePower, int maxSpeed, State state, int kilometers) {
        super(brand, model, horsePower, maxSpeed, state);
        this.kilometers = kilometers;
    }

    public LandVehicle() {}

    /* Getters / Setters */
    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
}