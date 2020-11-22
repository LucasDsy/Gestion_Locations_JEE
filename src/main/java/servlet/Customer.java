package servlet;

import service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Customer extends HttpServlet {
    final static String VIEW = "/pages/list-customers.jsp";
    final static String RESULT = "result";
    final static String ERRORS = "errors";
    final static String ID = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //récupération de l'id via l'url
        int id = Integer.parseInt(req.getRequestURI().replace("/", ""));

        model.people.Customer customer = new model.people.Customer();
        customer.setId(id);

        CustomerService service = new CustomerService();
        Map<String, String> errors;
        errors = service.deleteCustomer(customer);

        String result = errors.isEmpty() ? "Fiche client supprimée" : "Impossible de supprimer la fiche client";

        req.setAttribute(ERRORS, errors);
        req.setAttribute(RESULT, result);
    }
}
