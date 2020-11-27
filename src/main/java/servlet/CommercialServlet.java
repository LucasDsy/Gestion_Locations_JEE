package servlet;

import model.Location;
import model.people.Customer;
import service.LocationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/commercial")
public class CommercialServlet extends HttpServlet {

    /** Attributes **/
    public static final String CUSTOMERS = "customers";

    /** Views **/
    private static final String COMMERCIAL_VIEW = "/views/commercial/commercial.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Location> locations = new LocationService().findAll();

        Map<Customer, Double> customers_price = locations
                .stream()
                .filter(l -> l.getClient()!=null)
                .collect(Collectors.groupingBy(Location::getClient,
                        Collectors.summingDouble(Location::getPrice)));

        Map<Customer, Double> res = customers_price.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                        LinkedHashMap::new));

        request.setAttribute(CUSTOMERS ,res);

        this.getServletContext().getRequestDispatcher(COMMERCIAL_VIEW).forward(request, response);
    }




}
