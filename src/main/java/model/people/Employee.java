package model.people;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table
public class Employee extends Person {
    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class)
    private Set<Role> roles;

    public Employee(String lastName, String firstName, Calendar birthDate, Set<Role> roles) {
        super(lastName, firstName, birthDate);
        this.roles = roles;
    }

    public Employee() {}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}