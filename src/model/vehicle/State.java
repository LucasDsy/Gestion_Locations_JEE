package model.vehicle;

enum State {
    Neuf(0),
    DegatsMineurs(1),
    DegatsMajeurs(2),
    HorsService(3);

    final int valeur;

    State(int valeur) {
        this.valeur = valeur;
    }
}
