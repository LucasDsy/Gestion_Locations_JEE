package model.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
class MotorBike extends LandVehicle {

    /* Constructeur */
    public MotorBike(String brand, String model, int horsePower, int maxSpeed, State state, int kilometers) {
        super(brand, model, horsePower, maxSpeed, state, kilometers);
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
