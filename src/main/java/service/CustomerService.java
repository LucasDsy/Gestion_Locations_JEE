package service;

import dao.DAO;
import model.people.Customer;
import model.people.Person;

public class CustomerService extends Service<Customer> {

    public CustomerService() {
        super(new DAO<Customer>(Customer.class));
    }

    @Override
    protected DAO<Customer> getDAO() {
        return this.dao;
    }
}
