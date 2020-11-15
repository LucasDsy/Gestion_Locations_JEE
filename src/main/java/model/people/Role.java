package model.people;

enum Role {
    ClientManager("Gestionnaire client"),
    RentalManager("Responsable location"),
    CommercialManager("Gestionnaire commercial"),
    TechnicalManager("Gestionnaire technique");

    private final String wording;

    Role(String wording) {
        this.wording = wording;
    }

    public String getWording() {
        return wording;
    }
}