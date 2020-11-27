package dao;

import model.Location;
import model.people.Customer;
import model.vehicle.Vehicle;

import java.util.stream.Collectors;

public class CustomerDAO extends DAO<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }

    @Override
    public boolean delete(Customer customer) {
        LocationDAO locationDAO = new LocationDAO();

        locationDAO.startSession();

        locationDAO.mergeAll(locationDAO.findByCustomer(customer.getId())
            .stream()
            .peek(l-> l.setClient(null))
            .collect(Collectors.toList()));

        locationDAO.closeSession();

        return super.delete(customer);
    }
}
