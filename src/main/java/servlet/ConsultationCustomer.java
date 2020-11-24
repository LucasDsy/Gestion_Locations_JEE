
package servlet;

import model.people.Customer;
import model.people.Employee;
import model.people.Role;
import service.CustomerService;
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
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/displaycustomer")
public class ConsultationCustomer extends HttpServlet {
    /** Views **/
    private static final String VIEW = "/views/customer-consultation.jsp";

    /** Services **/
    private static final CustomerService customerService = new CustomerService();

    /** Attributes **/
    public static final String CUSTOMER = "customer";
    public static final String FOUND = "found";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            Integer customerId = Integer.parseInt(request.getParameter("id"));
            Customer actuelCustomer = this.customerService.findById(customerId);
            System.out.println("Client actuellement traité : ");
            System.out.println(actuelCustomer);
            if (actuelCustomer != null) {
                request.setAttribute(CUSTOMER, actuelCustomer);
                request.setAttribute(FOUND, true);
            } else {
                request.setAttribute(FOUND, false);
            }
        } catch (NumberFormatException e) {
            request.setAttribute(FOUND, false);
        }

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
