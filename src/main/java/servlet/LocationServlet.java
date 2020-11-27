package servlet;

import model.Location;
import model.people.Customer;
import model.people.State;
import model.vehicle.Vehicle;
import model.people.Employee;

import org.json.JSONObject;
import service.LocationService;
import service.VehicleService;
import service.CustomerService;

import utils.ConvertUtil;
import utils.ErrorUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/location")
public class LocationServlet extends HttpServlet {

    /** Attributes **/
    public static final String LOCATION_ATTRIBUTE = "locationsList";
    private static final String ID = "id";
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String VEHICLES_LIST = "vehiclesList";
    public static final String CUSTOMERS_LIST = "customersList";
    public static final String VEHICLE_ID = "idVehicle";
    public static final String CUSTOMER_ID = "idCustomer";
    public static final String ESTIMATED_KILOMETERS = "estimatedKilometers";
    public static final String DISCOUNT = "discount";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";

    /** Views **/
    private static final String LOCATION_VIEW = "/views/location/list-location.jsp";

    /** Errors **/
    private static final String INVALID_FIELDS = "Les informations envoyées sont incorrectes";
    private static final String VEHICLE_NOT_FOUND = "Ce véhicule n'existe pas";
    private static final String CUSTOMER_NOT_FOUND = "Ce client n'existe pas";
    private static final String EMPLOYEE_NOT_FOUND = "Votre compte n'a pas été trouvé";

    /** SESSION **/
    private static final String NAME_USER_SESSION = "user";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Location> locations = new LocationService().findAll();
        request.setAttribute(LOCATION_ATTRIBUTE, locations);

        VehicleService serviceVehicle = new VehicleService();
         CustomerService serviceCustomer = new CustomerService();

         List<Vehicle> listVehicles = serviceVehicle.getAvailable();
         List<Customer> listCustomers = serviceCustomer.findAll();

         request.setAttribute(VEHICLES_LIST,listVehicles);
         request.setAttribute(CUSTOMERS_LIST,listCustomers);

        this.getServletContext().getRequestDispatcher(LOCATION_VIEW).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocationService service = new LocationService();
        VehicleService serviceVehicle = new VehicleService();
        CustomerService serviceCustomer = new CustomerService();

        HashSet<String> errors = new HashSet<>();

        /** Parameters **/
        Integer idVehicle = null;
        Integer idCustomer = null;
        Integer estimatedKilometers = null;
        Boolean discount = null;
        Calendar startDate = null;
        Calendar endDate = null;
        State state = null;

        /** Objects parameters **/
        Vehicle vehicle = null;
        Customer customer = null;
        Employee employee = null;

        /** Parse form data **/
        try {
            idVehicle =  Integer.parseInt(request.getParameter(VEHICLE_ID));
            idCustomer = Integer.parseInt(request.getParameter(CUSTOMER_ID));
            estimatedKilometers = Integer.parseInt(request.getParameter(ESTIMATED_KILOMETERS));
            discount = Boolean.parseBoolean(request.getParameter(DISCOUNT));
            startDate = ConvertUtil.convertDateString(request.getParameter(START_DATE));
            endDate = ConvertUtil.convertDateString(request.getParameter(END_DATE));
        } catch(Exception e) {
            errors.add(INVALID_FIELDS);
        }


        /** Get vehicle **/
        vehicle = serviceVehicle.findById(idVehicle);
        if (vehicle == null)
            errors.add(VEHICLE_NOT_FOUND);

        /** Get customer **/
        customer = serviceCustomer.findById(idCustomer);
        if (customer == null)
            errors.add(CUSTOMER_NOT_FOUND);

        /** Get employee **/
        HttpSession session = request.getSession();
        if (session.getAttribute(NAME_USER_SESSION) != null)
            employee = (Employee) session.getAttribute(NAME_USER_SESSION);

        if (employee == null)
            errors.add(EMPLOYEE_NOT_FOUND);

        /** Deal with Status **/
        Calendar calendar = Calendar.getInstance();
        Date todayDate = calendar.getTime();
        Date start = startDate.getTime();
        Date end = endDate.getTime();

        if(todayDate.compareTo(start) >= 0)
            state = State.InProgress;
        else
            state = State.Booked;


        Location location = new Location(vehicle, customer, employee, state, startDate, endDate, discount, estimatedKilometers);

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(location)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if (errors.isEmpty()) {
            service.create(location);
            response.sendRedirect(request.getRequestURL().toString());
        } else {
            String result = "Impossible de créer la location.";
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        LocationService locationService = new LocationService();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());

        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt(ID);

        Location location = locationService.findById(id);

        if (locationService.delete(location)) {
            response.sendRedirect(request.getRequestURL().toString());
        } else {
            String result = "Location " + location.getId() + " a été supprimé !";
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }
}
