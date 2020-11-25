package service;

import dao.DAO;
import model.vehicle.Car;

public class CarService extends Service<Car> {

    public CarService() {
        super(new DAO<>(Car.class));
    }

    @Override
    protected DAO<Car> getDAO() {
        return this.dao;
    }
}
