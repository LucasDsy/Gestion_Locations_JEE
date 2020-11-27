package servlet;


import org.json.JSONObject;
import service.VehicleService;

import model.vehicle.Vehicle;
import model.vehicle.Car;
import model.vehicle.MotorBike;
import model.vehicle.Plane;
import model.vehicle.State;

import utils.ConvertUtil;
import utils.ErrorUtil;
import utils.HibernateUtil;
import utils.Routes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

@WebServlet(Routes.VEHICLE)
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
    public static final String ID = "id";
    public static final String VEHICLE_ATTRIBUTE = "vehicles";


    /** Types **/
    public static final String CAR = "voiture";
    public static final String MOTOR_BIKE = "moto";
    public static final String PLANE = "avion";

    /** Errors **/
    private static final String INVALID_FIELDS = "Les informations envoyées sont incorrectes";
    private static final String INVALID_NUMBER_FORMAT = "Un nombre est incorrect";

    /** Views **/
    private static final String VIEW = "/views/vehicle/list-vehicles.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute(VEHICLE_ATTRIBUTE, new VehicleService().findAll());

        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        Vehicle vehicle = null;

        String brand = request.getParameter(BRAND);
        String model = request.getParameter(MODEL);
        Integer horsePower = null;
        Integer maxSpeed = null;
        Integer stateValue = null;
        State state = null;
        Integer kilometers = null;
        Integer seatingCapacity = null;
        Integer cruisingSpeed = null;
        Integer nbMotors = null;
        Integer flightHours = null;

        try {
             horsePower = Integer.parseInt(request.getParameter(HORSEPOWER));
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }

        try {
            maxSpeed = Integer.parseInt(request.getParameter(MAXSPEED));
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }

        try {
            stateValue = Integer.parseInt(request.getParameter(VEHICLE_STATE));
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }

        try {
            state = State.fromValue(stateValue);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }

        switch (request.getParameter(VEHICLE_TYPE)) {
            case CAR:
                try {
                    kilometers = Integer.parseInt(request.getParameter(KILOMETERS));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                try {
                    seatingCapacity = Integer.parseInt(request.getParameter(SEATING_CAPACITY));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                vehicle = new Car(brand, model, horsePower, maxSpeed, state, kilometers, seatingCapacity);
                break;

            case MOTOR_BIKE:
                try {
                    kilometers = Integer.parseInt(request.getParameter(KILOMETERS));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                vehicle = new MotorBike(brand, model, horsePower, maxSpeed, state, kilometers);
                break;

            case PLANE:
                try {
                    cruisingSpeed = Integer.parseInt(request.getParameter(CRUISING_SPEED));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                try {
                    nbMotors = Integer.parseInt(request.getParameter(NB_MOTORS));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                try {
                    flightHours = Integer.parseInt(request.getParameter(FLIGHT_HOURS));
                } catch (NumberFormatException e) {
                    errors.add(e.getMessage());
                }

                vehicle = new Plane(brand, model, horsePower, maxSpeed, state, cruisingSpeed, nbMotors, flightHours);
                break;

            default:
                errors.add("Type véhicule incorrect");
                break;
        }

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(vehicle)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);

        if (errors.isEmpty()) {
            new VehicleService().update(vehicle);
            response.sendRedirect(request.getRequestURL().toString());
        } else {
            String result = "Impossible d'ajouter le véhicule";
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());
        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt(ID);
        Vehicle vehicle = null;

        try {
            switch (jsonObject.getString(VEHICLE_TYPE)) {
                case CAR:
                    vehicle = new Car(
                        jsonObject.getString(BRAND),
                        jsonObject.getString(MODEL),
                        jsonObject.getInt(HORSEPOWER),
                        jsonObject.getInt(MAXSPEED),
                        State.fromValue(jsonObject.getInt(VEHICLE_STATE)),
                        jsonObject.getInt(KILOMETERS),
                        jsonObject.getInt(SEATING_CAPACITY)
                    );
                    break;

                case MOTOR_BIKE:
                    vehicle = new MotorBike(
                        jsonObject.getString(BRAND),
                        jsonObject.getString(MODEL),
                        jsonObject.getInt(HORSEPOWER),
                        jsonObject.getInt(MAXSPEED),
                        State.fromValue(jsonObject.getInt(VEHICLE_STATE)),
                        jsonObject.getInt(KILOMETERS)
                    );
                    break;

                case PLANE:
                    vehicle = new Plane(
                        jsonObject.getString(BRAND),
                        jsonObject.getString(MODEL),
                        jsonObject.getInt(HORSEPOWER),
                        jsonObject.getInt(MAXSPEED),
                        State.fromValue(jsonObject.getInt(VEHICLE_STATE)),
                        jsonObject.getInt(CRUISING_SPEED),
                        jsonObject.getInt(NB_MOTORS),
                        jsonObject.getInt(FLIGHT_HOURS)
                    );
                    break;

                default: throw new Exception();
            }

            vehicle.setId(id);

        } catch (Exception e) {
            errors.add(INVALID_FIELDS);
        }

        //vérification par hibernate que les champs sont valides
        HibernateUtil.getValidator()
                .validate(vehicle)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(errors::add);


        VehicleService vehicleService = new VehicleService();

        if (errors.isEmpty()) {
            vehicleService.update(vehicle);
        } else {
            vehicle = vehicleService.findById(id);
            String result = "Impossible de modifier le véhicule " + vehicle.getBrand() + " " + vehicle.getModel();
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<String> errors = new HashSet<>();
        String body = ConvertUtil.inputStreamToString(request.getInputStream());

        JSONObject jsonObject = new JSONObject(body);
        Integer id = jsonObject.getInt("id");

        VehicleService vehicleService = new VehicleService();
        Vehicle vehicle = vehicleService.findById(id);

        if (!vehicleService.delete(vehicle)) {
            String result = "Véhicule " + vehicle.getId() + " a été supprimé !";
            ErrorUtil.sendError(response, RESULT, result, ERRORS, errors);
        }
    }
}
