package model.vehicle;

/**
 * Classe Vehicule
 * Cette classe représente un véhicule sans prendre en compte son type
 */
public abstract class Vehicle implements Rentable {
    private String brand;
    private String model;
    private int horsePower;
    private int maxSpeed;
    private State state;


    /* Constructeur */
    public Vehicle(String brand, String model, int horsePower, int maxSpeed) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.maxSpeed = maxSpeed;
    }


    /* abstract methods */
    abstract float getCoeff();


    /* Getters / Setters */
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public State getEtat() {
        return state;
    }

    public void setEtat(State state) {
        this.state = state;
    }
}
