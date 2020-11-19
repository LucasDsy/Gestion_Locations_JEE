package service;

import dao.DAO;
import model.vehicle.Vehicle;

public class VehicleService extends Service<Vehicle> {

    public VehicleService() {
        super(new DAO<>(Vehicle.class));
    }

    @Override
    protected DAO<Vehicle> getDAO() {
        return this.dao;
    }
}
