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

    /** Views **/
    public static final String LOGIN_VIEW = "/views/employee-login.jsp";
    public static final String NAME_USER_SESSION = "user";

    /** Attributes **/
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    /** Messsages **/
    private static final String USER_NOT_FOUND = "Utilisateur introuvable";
    private static final String WRONG_CREDENTIALS = "Identifiants incorrects";
    private static final String LOGIN_SUCCESS = "Connexion réussie";


    /** SESSION **/
    private static final String INVALID_FIELDS = "Champs invalide";

    private final EmployeeService employeeService = new EmployeeService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = "";
        String password = "";
        String message = "";
        HttpSession session;

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        if (this.employeeService.checkExist(login)) {
            if (this.employeeService.checkPassword(login, password)) {
                message = LOGIN_SUCCESS;
                session = request.getSession();
                session.setAttribute(NAME_USER_SESSION, this.employeeService.getWithLogin(login));
            } else {
                message = USER_NOT_FOUND;
            }

        } else {
            message = INVALID_FIELDS;
        }

        request.setAttribute( "msgLogin", message );
        this.getServletContext().getRequestDispatcher(LOGIN_VIEW).forward(request, response);
    }

    private boolean validateField(String field) {
        return (field != null && !field.isEmpty());
    }

}
