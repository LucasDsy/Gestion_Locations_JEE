package service;

import dao.CustomerDAO;
import dao.DAO;
import model.people.Customer;
import model.people.Person;

public class CustomerService extends Service<Customer> {

    public CustomerService() {
        super(new CustomerDAO());
    }

    @Override
    protected CustomerDAO getDAO() {
        return (CustomerDAO) this.dao;
    }

    @Override
    public boolean delete(Customer customer) {
        boolean res;
        this.dao.startSession();
        res = getDAO().delete(customer);
        this.dao.closeSession();
        return res;
    }
}
