package model.people;

public enum Role {
    ClientManager("Gestionnaire client"),
    RentalManager("Responsable location"),
    CommercialManager("Gestionnaire commercial"),
    TechnicalManager("Gestionnaire technique"),
    Administrator("Administrateur");

    private final String wording;

    Role(String wording) {
        this.wording = wording;
    }

    public String getWording() {
        return wording;
    }
}