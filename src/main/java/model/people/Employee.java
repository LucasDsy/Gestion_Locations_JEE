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

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    public Employee(String lastName, String firstName, String email, Calendar birthDate, Set<Role> roles,
                    String login, String password) {
        super(lastName, firstName, email, birthDate);
        this.roles = roles;
        this.login = login;
        this.password = password;
    }

    public Employee() {}

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}