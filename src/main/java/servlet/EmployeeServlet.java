package servlet;

import model.people.Role;
import service.EmployeeService;
import utils.ConvertUtil;
import utils.HibernateUtil;

import javax.persistence.Convert;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Employee extends HttpServlet {
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLES = "roles";
    public static final String BIRTHDAY = "birthday";
    public static final String SQL = "sql";
    public static final String BASIC_ERROR = "invalid field";
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String VIEW = "/WEB-INF/EmployeeCreation.jsp";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // conversion des champs string vers des objets
        Calendar birthdate = new GregorianCalendar();
        try {
            birthdate = ConvertUtil.convertDateString(request.getParameter(BIRTHDAY));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Role> roles = ConvertUtil.convertArrayStringToSet(request.getParameterValues(ROLES));

        model.people.Employee employee = new model.people.Employee(request.getParameter(LASTNAME), request.getParameter(FIRSTNAME), request.getParameter(EMAIL), birthdate, roles, request.getParameter(LOGIN), request.getParameter(PASSWORD));

        //vérification par hibernate que les champs sont valides
        Set<ConstraintViolation<model.people.Employee>> violations =  HibernateUtil.getValidator().validate(employee);

        violations.stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);

        Map<String, String> errors = new HashMap<>();
        EmployeeService service = new EmployeeService();
        if(violations.isEmpty()) {
            errors = service.addEmployee(employee);
        }
        String result = errors.isEmpty() ? "Utilisateur ajouté" : "Impossible d'ajouter l'utilisateur";

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, result);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
