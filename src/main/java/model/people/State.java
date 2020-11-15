package model.people;

public enum State {
    Booked("Réservée"),
    InProgress("En cours"),
    Completed("Terminée"),
    Cancelled("Annulée");

    private final String wording;

    State(String wording) {
        this.wording = wording;
    }

    public String getWording() {
        return wording;
    }
}
