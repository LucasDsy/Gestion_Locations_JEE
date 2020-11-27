
package servlet;

import model.people.Employee;
import model.people.Role;
import org.json.JSONException;
import org.json.JSONObject;
import service.EmployeeService;
import utils.ConvertUtil;
import utils.ErrorUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static servlet.EmployeeLoginServlet.NAME_USER_SESSION;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    /** Attributes **/
    public static final String ID = "id";
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String OLDPASSWORD = "oldpassword";
    public static final String PASSWORD = "password";
    public static final String PASSWORDBIS = "passwordbis";
    public static final String ROLES = "roles";
    public static final String BIRTHDATE = "birthdate";
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String EMPLOYEE_ATTRIBUTE = "employees";

    /** Errors **/
    private static final String INCORRECT_PASSWORD = "Mot de passe incorrect";
    private static final String PASSWORD_UNMATCH = "Les mots de passe ne sont pas les mêmes";
    private static final String LOGIN_ALREADY_EXISTS = "Ce login existe déjà";
    private static final String BIRTHDATE_INVALID = "La date de naissance n'est pas remplie correctement";

    /** Views **/
    private static final String VIEW = "/views/employee/list-employee.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute(EMPLOYEE_ATTRIBUTE, new EmployeeService().findAll());
        request.setAttribute("admin", request.getSession().getAttribute(NAME_USER_SESSION));

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        EmployeeService employeeService = new EmployeeService();

        if (employeeService.checkExist(request.getParameter(LOGIN)))
            errors.add(LOGIN_ALREADY_EXISTS);

        Calendar birthdate = null;
        try {
            birthdate = ConvertUtil.convertDateString(request.getParameter(BIRTHDATE));
        } catch (ParseException e) {
            errors.add(BIRTHDATE_INVALID);
        }

        String password = request.getParameter(PASSWORD);
        String passwordBis = request.getParameter(PASSWORDBIS);

        if (!password.equals(passwordBis))
            errors.add(PASSWORD_UNMATCH);

        Set<Role> roles = ConvertUtil.convertArrayStringToSet(Arrays.stream(Role.values())
                .map(Enum::toString)
                .filter(role -> request.getParameter(role) != null)
                .collect(Collectors.toList()));

        Employee employee = new Employee(request.getParameter(LASTNAME),
                request.getParameter(FIRSTNAME),
                request.getParameter(EMAIL),
                birthdate,
                roles,
                request.getParameter(LOGIN),
                password);

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(employee)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        String result;
        if (errors.isEmpty()) {
            employeeService.create(employee);
            response.sendRedirect(request.getRequestURL().toString());
        } else {
            result = "Impossible de créer l'utilisateur " + employee.getFirstName() + " " + employee.getLastName();
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        EmployeeService employeeService = new EmployeeService();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());
        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt(ID);
        Employee employee = employeeService.findById(id);

        String password = null;

        try {
            password = jsonObject.getString(PASSWORD);
        } catch (JSONException ignored) {}

        if (password == null) { // update utilisateur

            String login = jsonObject.getString(LOGIN);
            if (!employeeService.findById(id).getLogin().equals(login) && employeeService.checkExist(login))
                errors.add(LOGIN_ALREADY_EXISTS);

            Calendar birthdate = null;
            try {
                birthdate = ConvertUtil.convertDateString(jsonObject.getString(BIRTHDATE));
            } catch (ParseException e) {
                errors.add(BIRTHDATE_INVALID);
            }

            Set<Role> roles = ConvertUtil.convertArrayStringToSet(Arrays.stream(Role.values())
                    .map(Enum::toString)
                    .filter(role -> request.getParameter(role) != null)
                    .collect(Collectors.toList()));

            employee.setLastName(jsonObject.getString(LASTNAME));
            employee.setFirstName(jsonObject.getString(FIRSTNAME));
            employee.setEmail(jsonObject.getString(EMAIL));
            employee.setBirthDate(birthdate);
            employee.setRoles(roles);
            employee.setLogin(jsonObject.getString(LOGIN));

        } else { // change password
            String login = employee.getLogin();
            String passwordBis = jsonObject.getString(PASSWORDBIS);
            String oldPassword = jsonObject.getString(OLDPASSWORD);

            if (employeeService.checkPassword(login, oldPassword)) {
                if (password.equals(passwordBis))
                    employee.setPassword(password);
                else
                    errors.add(PASSWORD_UNMATCH);
            } else {
                errors.add(INCORRECT_PASSWORD);
            }
        }

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(employee)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if (errors.isEmpty()) {
            employeeService.update(employee);
        } else {
            String result = "Impossible de modifier l'utilisateur " + employee.getFirstName() + " " + employee.getLastName();
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        EmployeeService employeeService = new EmployeeService();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());

        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt(ID);

        Employee employee = employeeService.findById(id);

        if (!employeeService.delete(employee)) {
            String result = "Impossible de supprimer l'utilisateur " + employee.getFirstName() + " " + employee.getLastName();
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }
}
