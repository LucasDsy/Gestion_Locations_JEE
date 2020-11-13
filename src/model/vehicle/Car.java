package model.vehicle;

class Car extends LandVehicle {
    private int seatingCapacity;

    /* Constructeur */
    public Car(String marque, String modele, int puissance, int vitesseMax, int kilometrage, int seatingCapacity) {
        super(marque, modele, puissance, vitesseMax, kilometrage);
        this.seatingCapacity = seatingCapacity;
    }


    /* Rentable methods */
    @Override
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
