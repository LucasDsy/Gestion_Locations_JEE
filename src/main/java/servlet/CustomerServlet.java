package servlet;

import model.people.Customer;
import service.CustomerService;
import utils.ConvertUtil;
import utils.HibernateUtil;
import utils.URLUtil;

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
import java.util.List;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    /** Attributs **/
    private static final String LASTNAME = "lastName";
    private static final String FIRSTNAME = "firstName";
    private static final String EMAIL = "email";
    private static final String BIRTHDATE = "birthDate";

    /** Messages **/
    private static final String BIRTHDATE_INVALID = "Merci de vérifier la date de naissance";
    private static final String ERRORS = "errors";
    private static final String RESULT = "result";

    /** View **/
    private static final String VIEW = "/views/customer.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new CustomerService().findAll();
        request.setAttribute("customers", customers);

        System.out.println(customers);

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();

        String lastName = request.getParameter(LASTNAME);
        String firstName = request.getParameter(FIRSTNAME);
        String email = request.getParameter(EMAIL);
        Calendar birthDate = null;

        try {
            birthDate = ConvertUtil.convertDateString(request.getParameter(BIRTHDATE));
        } catch (ParseException e) {
            errors.add(BIRTHDATE_INVALID);
        }

        Customer customer = new Customer(lastName, firstName, email, birthDate);

        HibernateUtil.getValidator()
                .validate(customer)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if (errors.isEmpty()) new CustomerService().create(customer);

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, errors.isEmpty());

        response.sendRedirect(URLUtil.baseUrl("customer"));
    }
}