package model.vehicle;

import javax.persistence.*;

/**
 * Classe Vehicule
 * Cette classe représente un véhicule sans prendre en compte son type
 */
@Entity
@Table
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Vehicle implements Rentable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private int horsePower;

    @Column
    private int maxSpeed;

    @Enumerated(EnumType.STRING)
    private State state;


    /* Constructeur */
    public Vehicle(String brand, String model, int horsePower, int maxSpeed) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.maxSpeed = maxSpeed;
    }

    public Vehicle() {}


    /* abstract methods */
    @Transient
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
