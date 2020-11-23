package servlet;

import model.people.Employee;
import model.people.Role;
import service.EmployeeService;
import utils.ConvertUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    /** Attributes **/
    private static final String LASTNAME = "lastName";
    private static final String FIRSTNAME = "firstName";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLES = "roles";
    private static final String BIRTHDAY = "birthday";
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";

    /** Errors **/
    private static final String LOGIN_ALREADY_EXISTS = "Ce login existe déjà";
    private static final String BIRTHDATE_INVALID = "La date de naissance n'est pas remplie correctement";

    /** Views **/
    private static final String VIEW = "/views/employee-creation.jsp";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService();
        HashSet<String> errors = new HashSet<>();

        if(service.checkExist(request.getParameter(LOGIN)))
            errors.add(LOGIN_ALREADY_EXISTS);

        Calendar birthdate = null;
        try {
            birthdate = ConvertUtil.convertDateString(request.getParameter(BIRTHDAY));
        } catch (ParseException e) {
            errors.add(BIRTHDATE_INVALID);
        }

        Set<Role> roles = ConvertUtil.convertArrayStringToSet(request.getParameterValues(ROLES));
        Employee employee = new Employee(request.getParameter(LASTNAME), request.getParameter(FIRSTNAME), request.getParameter(EMAIL), birthdate, roles, request.getParameter(LOGIN), request.getParameter(PASSWORD));

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(employee)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if(errors.isEmpty()) {
            service.create(employee);
        }

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, errors.isEmpty());

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
