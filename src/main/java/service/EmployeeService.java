package service;

import com.google.inject.Inject;
import dao.DAO;
import dao.EmployeeDAO;
import dao.PersonDAO;
import model.people.Employee;
import model.people.Role;
import service.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeService extends Service<Employee>{

    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLES = "roles";
    public static final String BIRTHDAY = "birthday";
    public static final String SQL = "sql";

    public EmployeeService(){
        super(new EmployeeDAO());
    }

    public Map<String, String> addEmployee(String lastNameField, String firstNameField, String emailField, String birthDateField, String[] rolesField,
                            String loginField, String passwordField){
        Map<String, String> errors = new HashMap<>();

        String lastName = "";
        try{
            validateField(lastNameField);
            lastName = lastNameField;
        }
        catch (Exception e){
            errors.put(LASTNAME, "invalid field");
        }

        String firstName = "";
        try{
            validateField(firstNameField);
            firstName = firstNameField;
        }
        catch (Exception e){
            errors.put(FIRSTNAME, "invalid field");
        }

        String email = "";
        try{
            validateEmail(emailField);
            email = emailField;
        }
        catch (Exception e){
            errors.put(EMAIL, "invalid field");
        }

        String login = "";
        try{
            validateLogin(loginField);
            login = loginField;
        }
        catch (Exception e){
            errors.put(LOGIN, "login already used");
        }

        String password = "";
        try{
            validateField(passwordField);
            password = passwordField;
        }
        catch (Exception e){
            errors.put(LOGIN, "invalid field");
        }

        Set<Role> roles = new HashSet<>();
        try {
            String[] stringRoles = rolesField;
            for (String role : stringRoles) {
                roles.add(Role.valueOf(role));
            }
        }
        catch (Exception e) {
            errors.put(ROLES, "invalid role");
        }

        Calendar birthdate = Calendar.getInstance();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthdate.setTime(dateFormat.parse(birthDateField));
        }
        catch (Exception e){
            errors.put(BIRTHDAY, "invalid date format");
        }

        if(errors.isEmpty()){
            try{
                Employee employee = new Employee(lastName, firstName, email, birthdate, roles, login, password);
                DAO<Employee> dao = new DAO<>(Employee.class);
                dao.startSession();
                dao.persist(employee);
                dao.closeSession();
            }
            catch (Exception e){
                errors.put(SQL, e.getMessage());
            }
        }

        return errors;
    }

    public void validateField(String field) throws Exception {
        if(field==null || field == ""){
            throw new Exception("not valid field");
        }
    }

    public void validateEmail(String email) throws Exception{
        validateField(email);
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            throw new Exception("invalid mail address");
        }
    }

    public void validateLogin(String login) throws Exception {
        validateField(login);
        EmployeeDAO employeeDAO = getDAO();
        employeeDAO.startSession();
        Employee employee = employeeDAO.findByLogin(login);
        employeeDAO.closeSession();
        if(employee != null){
            throw new Exception("login already used");
        }
    }

    @Override
    protected EmployeeDAO getDAO() {
        return (EmployeeDAO) this.dao;
    }
}
