package service;

import dao.DAO;
import dao.PersonDAO;
import model.people.Person;

public class PersonService extends Service<Person> {

    public PersonService() {
        super(new PersonDAO());
    }

    public Person findByEmail(String email) {
        this.dao.startSession();
        Person res = this.getDAO().findByEmail(email);
        this.dao.closeSession();
        return res;
    }

    @Override
    protected PersonDAO getDAO() {
        return (PersonDAO) this.dao;
    }
}
