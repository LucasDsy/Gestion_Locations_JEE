package service;

import dao.VehicleDAO;
import model.vehicle.Vehicle;

public class VehicleService extends Service<Vehicle> {

    public VehicleService() {
        super(new VehicleDAO());
    }

    @Override
    protected VehicleDAO getDAO() {
        return (VehicleDAO) this.dao;
    }
}
