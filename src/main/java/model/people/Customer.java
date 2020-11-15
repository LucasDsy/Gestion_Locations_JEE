package model.people;

import java.util.Calendar;
import java.util.Date;

public class Customer extends Person {

    public Customer(String lastName, String firstName, Calendar birthDate) {
        super(lastName, firstName, birthDate);
    }
}