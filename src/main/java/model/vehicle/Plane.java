package model.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
class Plane extends AirVehicle {

    /* Constructeur */
    public Plane(String brand, String model, int horsePower, int maxSpeed, State state, int cruisingSpeed,
                 int nbMotors, int flightHours) {
        super(brand, model, horsePower, maxSpeed, state, cruisingSpeed, nbMotors, flightHours);
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
