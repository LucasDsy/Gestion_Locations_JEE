package servlet;

import service.EmployeeService;
import utils.URLUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;

import static servlet.HomeServlet.INDEX_VIEW;

@WebServlet("/login")
public class EmployeeLoginServlet extends HttpServlet {

    /** Views **/
    public static final String LOGIN_VIEW = "/views/employee-login.jsp";

    /** Attributes **/
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    public static final String MESSAGE = "msgLogin";

    /** Messsages **/
    private static final String USER_NOT_FOUND = "Utilisateur introuvable";
    private static final String WRONG_CREDENTIALS = "Identifiants incorrects";
    private static final String LOGIN_SUCCESS = "Connexion réussie";

    /** SESSION **/
    public static final String NAME_USER_SESSION = "user";

    private final EmployeeService employeeService = new EmployeeService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute(NAME_USER_SESSION) != null)
            response.sendRedirect(URLUtil.baseUrl(""));
        else
            this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = "";
        String password = "";
        String message = null;
        HttpSession session;

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        if (this.employeeService.checkExist(login)) {
            if (this.employeeService.checkPassword(login, password)) {
                session = request.getSession();
                session.setAttribute(NAME_USER_SESSION, this.employeeService.getWithLogin(login));
            } else {
                message = USER_NOT_FOUND;
            }

        } else {
            message = WRONG_CREDENTIALS;
        }

        if(message!=null) {
            request.setAttribute(MESSAGE, message);
            this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
        } else {
            response.sendRedirect(URLUtil.baseUrl(""));
        }
    }
}
