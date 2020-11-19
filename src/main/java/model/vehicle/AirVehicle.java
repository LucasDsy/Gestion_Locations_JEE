package model.vehicle;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class AirVehicle extends Vehicle {
    private static final float TYPEVAL = 1.4f; // valeur du type de véhicule
    @Column
    private int cruisingSpeed;
    @Column
    private int nbMotors;
    @Column
    private int flightHours;

    /* Constructeur */
    AirVehicle(String brand, String model, int horsePower, int maxSpeed, State state, int cruisingSpeed,
               int nbMotors, int flightHours) {
        super(brand, model, horsePower, maxSpeed, state);
        this.cruisingSpeed = cruisingSpeed;
        this.nbMotors = nbMotors;
        this.flightHours = flightHours;
    }

    public AirVehicle() {}


    /* Protected methods */
    @Override
    @Transient
    protected float getCoeff() {
        return ((float) 1/(getHorsePower() * getState().valeur)) + TYPEVAL;
    }


    /* Getters / Setters */
    public int getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(int cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public int getNbMotors() {
        return nbMotors;
    }

    public void setNbMotors(int nbMotors) {
        this.nbMotors = nbMotors;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }
}
