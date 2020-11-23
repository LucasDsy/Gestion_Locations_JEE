package servlet;

import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class EmployeeLoginServlet extends HttpServlet {

    /** VIEWS **/
    private static final String VIEW = "/views/employee-login.jsp";

    /** Attributes **/
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "msgLogin";

    /** Messsages **/
    private static final String USER_NOT_FOUND = "Utilisateur introuvable";
    private static final String WRONG_CREDENTIALS = "Identifiants incorrects";
    private static final String LOGIN_SUCCESS = "Connexion réussie";


    /** SESSION **/
    private static final String NAME_USER_SESSION = "user";

    private EmployeeService employeeService = new EmployeeService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;

        String login = "";
        String password = "";
        String message = "";

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        if (this.employeeService.checkExist(login)) {
            if (this.employeeService.checkPassword(login, password)) {
                message = LOGIN_SUCCESS;
                session = request.getSession();
                session.setAttribute(NAME_USER_SESSION, this.employeeService.getWithLogin(login));
            } else {
                message = WRONG_CREDENTIALS;
            }
        } else {
            message = USER_NOT_FOUND;
        }

        request.setAttribute(MESSAGE, message);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
