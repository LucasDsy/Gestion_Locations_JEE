package dao;

import model.vehicle.Vehicle;

import javax.persistence.TypedQuery;
import java.util.List;

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


}
