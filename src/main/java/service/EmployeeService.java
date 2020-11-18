package service;

import dao.DAO;
import dao.EmployeeDAO;
import model.people.Employee;

public class EmployeeService extends Service<Employee> {

    public EmployeeService() {
        super(new EmployeeDAO());
    }

    public boolean checkExist(String login) {
        this.dao.startSession();
        Employee res = this.getDAO().findByLogin(login);
        this.dao.closeSession();

        if(res != null)
            return true;
        else
            return false;
    }

    public boolean checkPassword(String login, String password) {
        this.dao.startSession();
        Employee res = this.getDAO().checkPassword(login,password);
        this.dao.closeSession();

        if(res != null)
            return true;
        else
            return false;
    }

    @Override
    protected EmployeeDAO getDAO() {
        return (EmployeeDAO) this.dao;
    }
}
