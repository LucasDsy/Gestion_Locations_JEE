package service;

import dao.DAO;
import dao.EmployeeDAO;
import model.people.Employee;

public class EmployeeService extends Service<Employee> {

    public EmployeeService() {
        super(new DAO<Employee>(Employee.class));
    }

    public Employee checkExist(String login) {
        this.dao.startSession();
        Employee res = this.getDAO().findByLogin(login);
        this.dao.closeSession();
        return res;
    }

    public Employee checkPassword(String login, String password) {
        this.dao.startSession();
        Employee res = this.getDAO().checkPassword(login,password);
        this.dao.closeSession();
        return res;
    }

    @Override
    protected EmployeeDAO getDAO() {
        return (EmployeeDAO) this.dao;
    }
}
