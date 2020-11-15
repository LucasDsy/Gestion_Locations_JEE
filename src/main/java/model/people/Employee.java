package model.people;

import java.util.Calendar;
import java.util.Set;

public class Employee extends Person {
    private Set<Role> roles;

    public Employee(String lastName, String firstName, Calendar birthDate, Set<Role> roles) {
        super(lastName, firstName, birthDate);
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}