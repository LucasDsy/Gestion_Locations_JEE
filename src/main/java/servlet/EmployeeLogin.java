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
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public EmployeeService employeeService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String login = "";
        String password = "";

        try{
            validateField(request.getParameter(LOGIN));
            login = request.getParameter(LOGIN);
        }
        catch (Exception e) {
            errors.put(LOGIN, "invalid field");
        }

        try{
            validateField(request.getParameter(PASSWORD));
            password = request.getParameter(PASSWORD);
        }
        catch (Exception e) {
            errors.put(PASSWORD, "invalid field");
        }

    }

    public void validateField(String field) throws Exception {
        if(field==null || field == ""){
            throw new Exception("not valid field");
        }
    }

}
