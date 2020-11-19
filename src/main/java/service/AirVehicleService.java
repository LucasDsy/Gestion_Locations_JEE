package service;

import dao.DAO;
import model.vehicle.AirVehicle;

public class AirVehicleService extends Service<AirVehicle> {

    public AirVehicleService() {
        super(new DAO<>(AirVehicle.class));
    }

    @Override
    protected DAO<AirVehicle> getDAO() {
        return this.dao;
    }
}
