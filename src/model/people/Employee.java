package model.people;

import java.util.Calendar;

public class Employee extends Person {
    private Role role;

    public Employee(String lastName, String firstName, Calendar birthDate, Role role) {
        super(lastName, firstName, birthDate);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}