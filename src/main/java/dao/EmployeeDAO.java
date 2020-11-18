package dao;

import model.people.Employee;

public class EmployeeDAO extends DAO<Employee>{

    public EmployeeDAO() {
        super(Employee.class);
    }

    /**
     * Find an Employee with their login
     * @param login login of the Employee
     * @return The Employee (Must be a child of Person)
     */
    public Employee findByLogin(String login){
        // Utilisation de HQL pour faire des requêtes
        Employee res = (Employee) session.createQuery("from Employee e where e.login = :login")
                .setParameter("login", login)
                .setMaxResults(1)
                .setFirstResult(0)
                .uniqueResult();

        return res;
    }

}
