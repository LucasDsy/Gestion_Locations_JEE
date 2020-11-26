package service;

import dao.VehicleDAO;
import model.people.Employee;
import model.vehicle.Vehicle;

import java.util.List;

public class VehicleService extends Service<Vehicle> {

    public VehicleService() {
        super(new VehicleDAO());
    }

    @Override
    protected VehicleDAO getDAO() {
        return (VehicleDAO) this.dao;
    }

    public List<Vehicle> getAvailable() {
        this.dao.startSession();
        List<Vehicle> res = this.getDAO().findAvailable();
        this.dao.closeSession();

        return res;
    }
}
