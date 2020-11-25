package service;

import dao.DAO;
import model.vehicle.LandVehicle;

public class LandVehicleService extends Service<LandVehicle> {

    public LandVehicleService() {
        super(new DAO<>(LandVehicle.class));
    }

    @Override
    protected DAO<LandVehicle> getDAO() {
        return this.dao;
    }
}
