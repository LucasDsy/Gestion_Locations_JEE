package servlets;

import dao.DAO;
import model.people.Employee;
import model.people.Role;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeCreation extends HttpServlet {
    public static final String VIEW = "/WEB-INF/EmployeeCreation.jsp";
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Role[] roles = Role.values();
        System.out.println(roles);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();
        String lastName = request.getParameter(LASTNAME);
        String firstName = request.getParameter(FIRSTNAME);
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        Set<Role> roles = new HashSet<>();
        try {
            String[] stringRoles = request.getParameterValues(ROLES);
            for (String role : stringRoles) {
                roles.add(Role.valueOf(role));
            }
        }
        catch (Exception e) {
            errors.put(ROLES, "invalid role");
        }

        Calendar birthdate = Calendar.getInstance();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            birthdate.setTime(dateFormat.parse(request.getParameter(BIRTHDAY)));
        }
        catch (Exception e){
            errors.put(BIRTHDAY, "invalid date format");
        }

        try{
            Employee employee = new Employee(lastName, firstName, email, birthdate, roles, login, password);
            DAO<Employee> dao = new DAO<>(Employee.class);
            dao.merge(employee);
        }
        catch (Exception e){
            errors.put(SQL, "error while saving data");
        }

        String result = errors.isEmpty() ? "success" : "failure";

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, result);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
