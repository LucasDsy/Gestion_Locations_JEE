package servlet;


import service.VehicleService;

import model.vehicle.Vehicle;
import model.vehicle.Car;
import model.vehicle.MotorBike;
import model.vehicle.Plane;
import model.vehicle.State;

import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.*;

@WebServlet("/vehicule")
public class VehicleServlet extends HttpServlet {

    /** Attributes **/
    public static final String ERRORS = "errors";
    public static final String RESULT = "result";
    public static final String BRAND = "brandname";
    public static final String MODEL = "modelname";
    public static final String HORSEPOWER = "horsepower";
    public static final String MAXSPEED = "maxspeed";
    public static final String KILOMETERS = "kilometers";
    public static final String SEATING_CAPACITY = "seatingCapacity";
    public static final String CRUISING_SPEED = "cruisingSpeed";
    public static final String NB_MOTORS = "nbMotors";
    public static final String FLIGHT_HOURS = "flightHours";
    public static final String VEHICLE_TYPE = "vehicletype";
    public static final String VEHICLE_STATE = "vehiclestate";

    /** Types **/
    public static final String CAR = "voiture";
    public static final String MOTOR_BIKE = "moto";
    public static final String PLANE = "avion";

    /** Errors **/
    private static final String VEHICLE_TYPE_UNKNOWN = "Ce type de véhicule est inconnu";
    private static final String STATE_TYPE_UNKNOWN = "Cet état est inconnu";
    private static final String INVALID_FIELDS = "Les informations envoyées sont incorrectes";

    /** Views **/
    private static final String VIEW = "/views/vehicle-creation.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleService service = new VehicleService();
        HashSet<String> errors = new HashSet<>();

        String type = null;
        Integer stateValue = null;

        try {
            type = request.getParameter(VEHICLE_TYPE);
            stateValue = Integer.parseInt(request.getParameter(VEHICLE_STATE));
        } catch(Exception e) {
            errors.add(INVALID_FIELDS);
        }

        /** Get state for vehicle **/
        State vehicleState = null;

        try {
            vehicleState = State.fromValue(stateValue);
        } catch(Exception e) {
            errors.add(STATE_TYPE_UNKNOWN);
        }

        /** Basic Fields **/
        String brand = null;
        String model = null;
        int horsePower = 0;
        int maxSpeed = 0;

        try {
            brand = request.getParameter(BRAND);
            model = request.getParameter(MODEL);
            horsePower = Integer.parseInt(request.getParameter(HORSEPOWER));
            maxSpeed = Integer.parseInt(request.getParameter(MAXSPEED));
        } catch(Exception e) {
            errors.add(INVALID_FIELDS);
        }

        /** Extra Fields **/
        int kilometers = 0;
        int seatingCapacity = 0;
        int cruisingSpeed = 0;
        int nbMotors = 0;
        int flightHours = 0;

        /** Create vehicle object **/
        Vehicle vehicle = null;

        switch(type) {
            case CAR:
                try {
                    kilometers = Integer.parseInt(request.getParameter(KILOMETERS));
                    seatingCapacity = Integer.parseInt(request.getParameter(SEATING_CAPACITY));
                } catch(Exception e) {
                    errors.add(INVALID_FIELDS);
                }
                vehicle = new Car(brand,model,horsePower,maxSpeed,vehicleState,kilometers,seatingCapacity);
                break;
            case MOTOR_BIKE:
                try {
                    kilometers = Integer.parseInt(request.getParameter(KILOMETERS));
                } catch(Exception e) {
                    errors.add(INVALID_FIELDS);
                }
                vehicle = new MotorBike(brand,model,horsePower,maxSpeed,vehicleState,kilometers);
                break;
            case PLANE:
                try {
                    cruisingSpeed = Integer.parseInt(request.getParameter(CRUISING_SPEED));
                    nbMotors = Integer.parseInt(request.getParameter(NB_MOTORS));
                    flightHours = Integer.parseInt(request.getParameter(FLIGHT_HOURS));
                } catch(Exception e) {
                    errors.add(INVALID_FIELDS);
                }
                vehicle = new Plane(brand,model,horsePower,maxSpeed,vehicleState,cruisingSpeed,nbMotors,flightHours);
                break;
            default:
                errors.add(VEHICLE_TYPE_UNKNOWN);
                break;
        }

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(vehicle)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if(errors.isEmpty()) {
            service.create(vehicle);
        }

        request.setAttribute(ERRORS, errors);
        request.setAttribute(RESULT, errors.isEmpty());

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }
}
