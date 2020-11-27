package dao;

import model.people.Customer;
import model.vehicle.Vehicle;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleDAO extends DAO<Vehicle> {

    public VehicleDAO() {
        super(Vehicle.class);
    }

    public List<Vehicle> findAvailable(){

        String str = "from Vehicle v where v.id not in (select l.vehicle.id from Location l where l.status = 'InProgress' or l.status = 'Booked')";

        TypedQuery<Vehicle> query = session.createQuery(str, Vehicle.class);
        List<Vehicle> results = query.getResultList();

        return results;
    }

    @Override
    public boolean delete(Vehicle vehicle) {
        LocationDAO locationDAO = new LocationDAO();

        locationDAO.startSession();

        locationDAO.mergeAll(locationDAO.findByVehicle(vehicle.getId()).stream()
                .peek(l-> l.setClient(null))
                .collect(Collectors.toList()));

        locationDAO.closeSession();

        return super.delete(vehicle);
    }


}
