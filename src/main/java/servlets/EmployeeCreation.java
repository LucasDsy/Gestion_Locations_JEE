package servlets;

import dao.DAO;
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

public class EmployeeCreation extends HttpServlet {
    public static final String VIEW = "/WEB-INF/EmployeeCreation.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Set<Role> roles = new HashSet<>();
            String[] stringRoles = request.getParameterValues("roles");
            for (String role : stringRoles) {
                roles.add(Role.valueOf(role));
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar birthdate = Calendar.getInstance();

            birthdate.setTime(dateFormat.parse(request.getParameter("birthday")));

            Employee employee = new Employee(lastName, firstName, email, birthdate, roles, login, password);
            DAO<Employee> dao = new DAO<>(Employee.class);
            dao.merge(employee);
        }
        catch (ParseException e) {
            e.printStackTrace();
            response.addCookie(new Cookie("EmployeeCreationError", "Invalid date format or roles"));
        }
        finally {
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }
    }
}
