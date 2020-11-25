package service;

import dao.DAO;
import model.vehicle.MotorBike;

public class MotorBikeService extends Service<MotorBike> {

    public MotorBikeService() {
        super(new DAO<>(MotorBike.class));
    }

    @Override
    protected DAO<MotorBike> getDAO() {
        return this.dao;
    }
}
