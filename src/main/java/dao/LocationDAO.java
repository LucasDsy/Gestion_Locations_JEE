package dao;

import model.Location;
import model.people.Person;

import java.util.List;

public class LocationDAO extends DAO<Location> {

    public LocationDAO() {
        super(Location.class);
    }

    public List<Location> findByCustomer(Integer customerId) {
        return session.createQuery("select l from Location l join l.customer as c where c.id = :id")
                .setParameter("id", customerId)
                .getResultList();
    }

    public List<Location> findByVehicle(Integer vehicleId) {
        return (List<Location>) session.createQuery("select l from Location l join l.vehicle as v where v.id = :id")
                .setParameter("id", vehicleId)
                .getResultList();
    }

    public List<Location> findByEmployee(Integer employeeId) {
        return (List<Location>) session.createQuery("select l from Location l join l.employee as e where e.id = :id")
                .setParameter("id", employeeId)
                .getResultList();
    }
}
