package model.vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Car extends LandVehicle {
    @Column
    private int seatingCapacity;

    /* Constructeur */
    public Car(String brand, String model, int horsePower, int maxSpeed, State state, int kilometers, int seatingCapacity) {
        super(brand, model, horsePower, maxSpeed, state, kilometers);
        this.seatingCapacity = seatingCapacity;
    }

    public Car() {}


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
