package service;

import dao.DAO;
import dao.EmployeeDAO;
import model.people.Employee;

import java.util.HashMap;
import java.util.Map;

import static servlet.Employee.LOGIN;
import static servlet.Employee.SQL;

public class EmployeeService extends Service<Employee> {

    public EmployeeService() {
        super(new EmployeeDAO());
    }

    public Employee getEmployee(String login) {
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

    public Map<String, String> addEmployee(Employee employee){
        Map<String, String> errors = new HashMap<>();

        if(checkExist(employee.getLogin())){
            errors.put(LOGIN, "Login déjà existant");
        }

        if(errors.isEmpty()){
            try{
                DAO<Employee> dao = new DAO<>(Employee.class);
                dao.startSession();
                dao.persist(employee);
                dao.closeSession();
            }
            catch (Exception e){
                errors.put(SQL, "Impossible de sauvegarder les données");
            }
        }
        return errors;
    }

    @Override
    protected EmployeeDAO getDAO() {
        return (EmployeeDAO) this.dao;
    }
}
