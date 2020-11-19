package service;

import dao.DAO;
import model.Location;

public class LocationService extends Service<Location> {

    public LocationService() {
        super(new DAO<>(Location.class));
    }

    @Override
    protected DAO<Location> getDAO() {
        return this.dao;
    }
}
