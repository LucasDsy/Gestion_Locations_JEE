package servlet;

import jdk.nashorn.internal.parser.JSONParser;
import model.people.Customer;
import org.json.JSONArray;
import org.json.JSONObject;
import service.CustomerService;
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
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    /**
     * Attributs
     **/
    public static final String LASTNAME = "lastName";
    public static final String FIRSTNAME = "firstName";
    public static final String EMAIL = "email";
    public static final String BIRTHDATE = "birthDate";
    public static final String ROLES = "roles";
    public static final String CUSTOMER_ATTRIBUTE = "customers";

    /**
     * Messages
     **/
    private static final String BIRTHDATE_INVALID = "Merci de vérifier la date de naissance";
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";

    /**
     * View
     **/
    private static final String CUSTOMER_VIEW = "/views/customer/list-customers.jsp";

    private final CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new CustomerService().findAll();
        request.setAttribute(CUSTOMER_ATTRIBUTE, customers);

        this.getServletContext().getRequestDispatcher(CUSTOMER_VIEW).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        String result;
        if (errors.isEmpty()) {
            result = "Client " + customer.getFirstName() + " " + customer.getLastName() + " créé !";
            customerService.create(customer);
        } else {
            result = "Impossible de créer le client " + customer.getFirstName() + " " + customer.getLastName();
            request.setAttribute(FIRSTNAME, customer.getFirstName());
            request.setAttribute(LASTNAME, customer.getLastName());
            request.setAttribute(EMAIL, customer.getEmail());
        }

        List<Customer> customers = new CustomerService().findAll();
        request.setAttribute(CUSTOMER_ATTRIBUTE, customers);

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, result);

        this.getServletContext().getRequestDispatcher(CUSTOMER_VIEW).forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HashSet<String> errors = new HashSet<>();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());

        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt("id");

        Customer customer = customerService.findById(id);

        request.getServletContext().log(customer.getFirstName());

        if (!customerService.delete(customer)) {
            String result = "Impossible de supprimer le client : " + customer.getFirstName() + " " + customer.getLastName() + ".";
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        String body = ConvertUtil.inputStreamToString(req.getInputStream());
        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt("id");

        Customer customer = customerService.findById(id);

        Calendar birthDate = null;
        try {
            birthDate = ConvertUtil.convertDateString(jsonObject.getString(BIRTHDATE));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            errors.add(BIRTHDATE_INVALID);
        }

        customer.setBirthDate(birthDate);
        customer.setLastName(jsonObject.getString(LASTNAME));
        customer.setFirstName(jsonObject.getString(FIRSTNAME));
        customer.setEmail(jsonObject.getString(EMAIL));

        HibernateUtil.getValidator()
                .validate(customer)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if (errors.isEmpty()) {
            customerService.update(customer);
        } else {
            String result = "Impossible de mettre à jour le client " + customer.getFirstName() + " " + customer.getLastName();
            ErrorUtil.sendError(resp, RESULT, result, ERRORS, errors);
        }
    }
}