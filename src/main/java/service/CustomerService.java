package service;

import dao.DAO;
import model.people.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerService extends Service<Customer> {

    public CustomerService() {
        super(new DAO<Customer>(Customer.class));
    }

    public Map<String, String> deleteCustomer(Customer customer){
        Map<String, String> errors = new HashMap<>();
        try{
            boolean isDeleted = super.delete(customer);
            if (!isDeleted) errors.put("deletionError", "Impossible de supprimer le client de la base de données");
        }
        catch (Exception e){
            errors.put("dataBaseConnexionError", "Impossible de se connecter à la base de données");
        }
        return errors;
    }

    @Override
    protected DAO<Customer> getDAO() {
        return this.dao;
    }
}
