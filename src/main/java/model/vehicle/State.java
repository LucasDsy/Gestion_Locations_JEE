package model.vehicle;

import java.util.stream.Stream;

public enum State {

    Neuf(0),
    DegatsMineurs(1),
    DegatsMajeurs(2),
    HorsService(3);

    public final int valeur;

    State(int valeur) {
        this.valeur = valeur;
    }

    public static State fromValue(int value) throws Exception {
        return Stream.of(State.values())
                .filter(s -> s.valeur == value)
                .findFirst()
                .orElseThrow(()-> new Exception("La valeur n'est pas comprise entre 0 et 3"));
    }

}
