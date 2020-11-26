package service;

import dao.DAO;
import dao.LocationDAO;
import model.Location;


public class LocationService extends Service<Location> {

    public LocationService() {
        super(new LocationDAO());
    }

    @Override
    protected LocationDAO getDAO() {
        return (LocationDAO) this.dao;
    }

}

