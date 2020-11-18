package servlet;

import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class EmployeeLogin extends HttpServlet {

    public static final String VIEW = "/EmployeeLogin.jsp";

    /** Attributes **/
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    /** Messsages **/
    public static final String USER_NOT_FOUND = "Utilisateur introuvable";
    public static final String WRONG_CREDENTIALS = "Identifiants incorrects";
    public static final String LOGIN_SUCCESS = "Connexion réussie";
    public static final String INVALID_FIELS = "Champs invalides";

    public EmployeeService employeeService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String login = "";
        String password = "";
        String message = "";

        try {
            validateField(request.getParameter(LOGIN));
            login = request.getParameter(LOGIN);
        }
        catch (Exception e) {
            errors.put(LOGIN, "invalid field");
        }

        try {
            validateField(request.getParameter(PASSWORD));
            password = request.getParameter(PASSWORD);
        }
        catch (Exception e) {
            errors.put(PASSWORD, "invalid field");
        }

        this.employeeService = new EmployeeService();

        if(errors.isEmpty()) {

            if (this.employeeService.checkExist(login)) {
                if (this.employeeService.checkPassword(login, password)) {
                    message = LOGIN_SUCCESS;
                    /**
                     * TODO : gestion des sessions utilisateurs
                     **/
                } else {
                    message = WRONG_CREDENTIALS;
                }
            } else {
                message = USER_NOT_FOUND;
            }

            request.setAttribute( "msgLogin", message );
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        } else {
            message = INVALID_FIELS;
            request.setAttribute( "msgLogin", message );
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }
    }

    public void validateField(String field) throws Exception {
        if(field==null || field == ""){
            throw new Exception("not valid field");
        }
    }

}
