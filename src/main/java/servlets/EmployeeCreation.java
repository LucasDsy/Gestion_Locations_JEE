package servlets;

import dao.DAO;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String lastName = "";
        try{
            validateField(request.getParameter(LASTNAME));
            lastName = request.getParameter(LASTNAME);
        }
        catch (Exception e){
            errors.put(LASTNAME, "invalid field");
        }

        String firstName = "";
        try{
            validateField(request.getParameter(FIRSTNAME));
            firstName = request.getParameter(FIRSTNAME);
        }
        catch (Exception e){
            errors.put(FIRSTNAME, "invalid field");
        }

        String email = "";
        try{
            validateEmail(request.getParameter(EMAIL));
            email = request.getParameter(EMAIL);
        }
        catch (Exception e){
            errors.put(EMAIL, "invalid field");
        }

        String login = "";
        try{
            validateField(request.getParameter(LOGIN));
            login = request.getParameter(LOGIN);
        }
        catch (Exception e){
            errors.put(LOGIN, "invalid field");
        }

        String password = "";
        try{
            validateField(request.getParameter(PASSWORD));
            password = request.getParameter(PASSWORD);
        }
        catch (Exception e){
            errors.put(LOGIN, "invalid field");
        }

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

        if(errors.isEmpty()){
            try{
                Employee employee = new Employee(lastName, firstName, email, birthdate, roles, login, password);
                DAO<Employee> dao = new DAO<>(Employee.class);
                dao.startSession();
                dao.persist(employee);
                dao.closeSession();
            }
            catch (Exception e){
                errors.put(SQL, "error while saving data");
            }
        }

        String result = errors.isEmpty() ? "Utilisateur ajouté" : "Impossible d'ajouter l'utilisateur";

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, result);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
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
}
