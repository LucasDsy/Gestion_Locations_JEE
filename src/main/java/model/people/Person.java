package model.people;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
//Le nom de la table est le nom de l'entité, sinon @Table(name = "Person")
@Table
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    @Column
    @Email
    private String email;

    public Person(String lastName, String firstName, String email, Calendar birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Person() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + new SimpleDateFormat("dd MMM yyyy").format(birthDate.getTime()) +
                '}';
    }
}