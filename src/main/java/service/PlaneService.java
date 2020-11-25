package service;

import dao.DAO;
import model.vehicle.Plane;

public class PlaneService extends Service<Plane> {

    public PlaneService() {
        super(new DAO<>(Plane.class));
    }

    @Override
    protected DAO<Plane> getDAO() {
        return this.dao;
    }
}
