
package servlet;

import model.Location;
import model.people.Customer;
import service.CustomerService;
import service.LocationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/displaycustomer")
public class ConsultationTop10CustomerServlet extends HttpServlet {
    /** Views **/
    private static final String VIEW = "/views/customer-consultation.jsp";

    /** Services **/
    private static final CustomerService customerService = new CustomerService();
    private static final LocationService locationService = new LocationService();

    /** Attributes **/
    public static final String CUSTOMER = "customer";
    public static final String FOUND = "found";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HashMap<Customer, Float> map = new HashMap<>();

        List<Location> locations = locationService.findAll();

        for (Location loc: locations) {
            Float price = loc.getActualPrice();
            if (map.get(loc.getClient()) != null) {
                price += map.get(loc.getClient());
            }
            map.put(loc.getClient(), price);
        }
        List<Customer> listeTop10 = new ArrayList<Customer>(map.keySet());

        Collections.


        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
