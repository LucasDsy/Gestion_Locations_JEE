package model.vehicle;

class Plane extends AirVehicle {

    /* Constructeur */
    public Plane(String marque, String modele, int puissance, int vitesseMax, int vitesseCroisiere, int nbMoteurs, int heuresVol) {
        super(marque, modele, puissance, vitesseMax, vitesseCroisiere, nbMoteurs, heuresVol);
    }


    /* Rentable methods */
    @Override
    public float getPrixLocJour() {
        final float prixBase = 150.0f;
        return prixBase * getCoeff();
    }
}
