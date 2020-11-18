package servlets;

import model.people.Role;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static service.EmployeeService.*;

public class EmployeeCreation extends HttpServlet {
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String VIEW = "/WEB-INF/EmployeeCreation.jsp";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService service = new EmployeeService();
        Map<String, String> errors = service.addEmployee(request.getParameter(LASTNAME), request.getParameter(FIRSTNAME), request.getParameter(EMAIL), request.getParameter(BIRTHDAY),
                request.getParameterValues(ROLES), request.getParameter(LOGIN), request.getParameter(PASSWORD));

        String result = errors.isEmpty() ? "Utilisateur ajouté" : "Impossible d'ajouter l'utilisateur";

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, result);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
