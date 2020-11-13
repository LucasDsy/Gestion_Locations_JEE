package model.vehicle;

class MotorBike extends LandVehicle {

    /* Constructeur */
    public MotorBike(String marque, String modele, int puissance, int vitesseMax, int kilometrage) {
        super(marque, modele, puissance, vitesseMax, kilometrage);
    }


    /* Rentable methods */
    @Override
    public float getPrixLocJour() {
        final float prixBase = 45.0f;
        return prixBase * getCoeff();
    }
}
