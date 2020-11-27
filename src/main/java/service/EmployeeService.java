package service;

import dao.EmployeeDAO;
import model.people.Employee;
import model.vehicle.Vehicle;

public class EmployeeService extends Service<Employee> {

    public EmployeeService() {
        super(new EmployeeDAO());
    }

    public Employee getWithLogin(String login) {
        this.dao.startSession();
        Employee res = this.getDAO().findByLogin(login);
        this.dao.closeSession();

        return res;
    }

    public boolean checkExist(String login) {
        this.dao.startSession();
        Employee res = this.getDAO().findByLogin(login);
        this.dao.closeSession();

        return res != null;
    }

    public boolean checkPassword(String login, String password) {
        this.dao.startSession();
        Employee res = this.getDAO().checkPassword(login,password);
        this.dao.closeSession();

        return res != null;
    }

    @Override
    public boolean delete(Employee employee) {
        boolean res;
        this.dao.startSession();
        res = getDAO().delete(employee);
        this.dao.closeSession();
        return res;
    }

    @Override
    protected EmployeeDAO getDAO() {
        return (EmployeeDAO) this.dao;
    }
}
