package model.vehicle;

abstract class AirVehicle extends Vehicle {
    private static final float TYPEVAL = 1.4f; // valeur du type de véhicule
    private int cruisingSpeed;
    private int nbMotors;
    private int flightHours;

    /* Constructeur */
    AirVehicle(String brand, String model, int horsePower, int maxSpeed,
               int cruisingSpeed, int nbMotors, int flightHours) {
        super(brand, model, horsePower, maxSpeed);
        this.cruisingSpeed = cruisingSpeed;
        this.nbMotors = nbMotors;
        this.flightHours = flightHours;
    }


    /* Protected methods */
    @Override
    protected float getCoeff() {
        return ((float) 1/(getHorsePower() * getSate().valeur)) + TYPEVAL;
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
