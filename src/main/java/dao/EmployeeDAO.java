package dao;

import model.people.Customer;
import model.people.Employee;

import java.util.stream.Collectors;

public class EmployeeDAO extends DAO<Employee> {

    public EmployeeDAO() {
        super(Employee.class);
    }

    /**
     * Find a Employee with their login
     * @param login Email of the Employee
     * @return The Employee
     */
    public Employee findByLogin(String login){
        // Utilisation de HQL pour faire des requêtes
        Employee res = (Employee) session.createQuery("from Employee p inner join p.roles where p.login = :login")
                .setParameter("login", login)
                .setMaxResults(1)
                .setFirstResult(0)
                .uniqueResult();

        return res;
    }

    /**
     * Check a Employee credentials
     * @param login Login of the Employee
     * @param password Email of the Employee
     * @return The Employee
     */
    public Employee checkPassword(String login, String password){
        // Utilisation de HQL pour faire des requêtes
        Employee res = (Employee) session.createQuery("from Employee p where p.login = :login and p.password = :password")
                .setParameter("login", login)
                .setParameter("password", password)
                .setMaxResults(1)
                .setFirstResult(0)
                .uniqueResult();

        return res;
    }

    @Override
    public boolean delete(Employee employee) {
        LocationDAO locationDAO = new LocationDAO();

        locationDAO.startSession();

        locationDAO.mergeAll(locationDAO.findByEmployee(employee.getId())
                .stream()
                .peek(l-> l.setClient(null))
                .collect(Collectors.toList()));

        locationDAO.closeSession();

        return super.delete(employee);
    }
}
