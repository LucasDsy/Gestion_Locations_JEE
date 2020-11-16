package model.people;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class Customer extends Person {

    public Customer(String lastName, String firstName, Calendar birthDate) {
        super(lastName, firstName, birthDate);
    }

    public Customer() {

    }

    @Override
    public String toString() {
        return "Customer{} " + super.toString();
    }
}