<%@ page import="model.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="model.vehicle.Car" %>
<%@ page import="model.vehicle.MotorBike" %>
<%@ page import="model.vehicle.Plane" %>
<%@ page import="servlet.VehicleServlet" %>
<%@ page import="utils.URLUtil" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="static servlet.VehicleServlet.RESULT" %>
<%@ page import="static servlet.VehicleServlet.ERRORS" %>
<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 17/11/2020
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String result = (String) request.getAttribute(RESULT);
    HashSet<String> errorsList = (HashSet<String>) request.getAttribute(VehicleServlet.ERRORS);
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute(VehicleServlet.VEHICLE_ATTRIBUTE);
%>

<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des véhicules</title>
    <jsp:include page="/css/mdb-css.jsp"/>
</head>
<body>
    <% if(errorsList != null && !errorsList.isEmpty()){%>
    <div class="container">
        <span class="text-danger"><%=result%></span><br/>
        <% for(String err : errorsList){%>
        <span class="text-danger"><%=err%></span><br/>
        <%}%>
    </div>
    <%} else if(result!=null){%>
    <span class="text-success"><%=result%></span><br/>
    <%}%>

    <div class="container">
        <div class="row">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Type</th>
                        <th scope="col">Marque</th>
                        <th scope="col">Modèle</th>
                        <th scope="col">Prix jour</th>
                        <th scope="col">Modifier</th>
                        <th scope="col">Supprimer</th>
                    </tr>
                </thead>

                <tbody>
                    <% for (Vehicle vehicle : vehicles) { %>
                        <% if (vehicle instanceof Car) { %>
                            <% Car car = (Car) vehicle; %>
                            <tr>
                                <th scope="row">Voiture</th>
                                <td><%= vehicle.getBrand() %></td>
                                <td><%= vehicle.getModel() %></td>
                                <td><%= vehicle.getPrixLocJour() %></td>
                                <td>
                                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editCar<%= car.getId() %>">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteVehicle<%= car.getId() %>">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                                <jsp:include page="modal/edit-car.jsp" flush="true">
                                    <jsp:param name="id" value="<%= car.getId().toString() %>"/>
                                    <jsp:param name="brandname" value="<%= car.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= car.getModel() %>"/>
                                    <jsp:param name="horsepower" value="<%= car.getHorsePower() %>"/>
                                    <jsp:param name="maxspeed" value="<%= car.getMaxSpeed() %>"/>
                                    <jsp:param name="vehiclestate" value="<%= car.getState().valeur %>"/>
                                    <jsp:param name="kilometers" value="<%= car.getKilometers() %>"/>
                                    <jsp:param name="seatingCapacity" value="<%= car.getSeatingCapacity() %>"/>
                                </jsp:include>
                                <jsp:include page="modal/delete-vehicle.jsp" flush="true">
                                    <jsp:param name="id" value="<%= car.getId() %>"/>
                                    <jsp:param name="brandname" value="<%= car.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= car.getModel() %>"/>
                                </jsp:include>
                            </tr>
                        <% } else if (vehicle instanceof MotorBike) { %>
                            <% MotorBike moto = (MotorBike) vehicle; %>
                            <tr>
                                <th scope="row">Moto</th>
                                <td><%= vehicle.getBrand() %></td>
                                <td><%= vehicle.getModel() %></td>
                                <td><%= vehicle.getPrixLocJour() %></td>
                                <td>
                                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editMotorbike<%= moto.getId() %>">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteVehicle<%= moto.getId() %>">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                                <jsp:include page="modal/edit-motorbike.jsp" flush="true">
                                    <jsp:param name="id" value="<%= moto.getId() %>"/>
                                    <jsp:param name="brandname" value="<%= moto.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= moto.getModel() %>"/>
                                    <jsp:param name="horsepower" value="<%= moto.getHorsePower() %>"/>
                                    <jsp:param name="maxspeed" value="<%= moto.getMaxSpeed() %>"/>
                                    <jsp:param name="vehiclestate" value="<%= moto.getState().valeur %>"/>
                                    <jsp:param name="kilometers" value="<%= moto.getKilometers() %>"/>
                                </jsp:include>
                                <jsp:include page="modal/delete-vehicle.jsp" flush="true">
                                    <jsp:param name="id" value="<%= moto.getId() %>"/>
                                    <jsp:param name="brandname" value="<%= moto.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= moto.getModel() %>"/>
                                </jsp:include>
                            </tr>
                        <% } else if (vehicle instanceof Plane) { %>
                            <% Plane plane = (Plane) vehicle; %>
                            <tr>
                                <th scope="row">Avion</th>
                                <td><%= vehicle.getBrand() %></td>
                                <td><%= vehicle.getModel() %></td>
                                <td><%= vehicle.getPrixLocJour() %></td>
                                <td>
                                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editPlane<%= plane.getId() %>">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                </td>
                                <td>
                                    <button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteVehicle<%= plane.getId() %>">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                                <jsp:include page="modal/edit-plane.jsp" flush="true">
                                    <jsp:param name="id" value="<%= plane.getId() %>"/>
                                    <jsp:param name="brandname" value="<%= plane.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= plane.getModel() %>"/>
                                    <jsp:param name="horsepower" value="<%= plane.getHorsePower() %>"/>
                                    <jsp:param name="maxspeed" value="<%= plane.getMaxSpeed() %>"/>
                                    <jsp:param name="vehiclestate" value="<%= plane.getState().valeur %>"/>
                                    <jsp:param name="flightHours" value="<%= plane.getFlightHours() %>"/>
                                    <jsp:param name="nbMotors" value="<%= plane.getNbMotors() %>"/>
                                    <jsp:param name="cruisingSpeed" value="<%= plane.getCruisingSpeed() %>"/>
                                </jsp:include>
                                <jsp:include page="modal/delete-vehicle.jsp" flush="true">
                                    <jsp:param name="id" value="<%= plane.getId() %>"/>
                                    <jsp:param name="brandname" value="<%= plane.getBrand() %>"/>
                                    <jsp:param name="modelname" value="<%= plane.getModel() %>"/>
                                </jsp:include>
                            </tr>
                        <% } else { %>
                            <tr>
                                <th scope="row">ERROR</th>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        <% } %>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <div style="position:fixed; bottom: 25px; right: 24px;">
        <button class="btn btn-dark-green rounded-pill" data-toggle="modal" data-target="#createVehicleModal">
            <i class="fas fa-plus"></i> Ajouter
        </button>
    </div>
    <jsp:include page="modal/create-vehicle.jsp"/>
</body>

<jsp:include page="/js/mdb-js.jsp"/>
<script>
    const url = "<%= URLUtil.baseUrl("vehicle")%>";

    function deleteVehicle(id) {
        const data = { id: id };

        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'DELETE',
            body: JSON.stringify(data),
            redirect: "follow",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
        .then(function (response) {
                if(response.status === 200 || response.status === 0) {
                    window.location.href = url;
                } else {
                    response.json().then(data => writeErrors(data))
                }
            }
        )
        .catch(error => console.error(error))
    }

    function editCar(id) {
        const data = {
            "<%= VehicleServlet.VEHICLE_TYPE %>": "<%= VehicleServlet.CAR %>",
            "<%= VehicleServlet.BRAND %>": document.querySelector("#<%= VehicleServlet.BRAND %>"+id).value,
            "<%= VehicleServlet.MODEL %>": document.querySelector("#<%= VehicleServlet.MODEL %>"+id).value,
            "<%= VehicleServlet.HORSEPOWER %>": document.querySelector("#<%= VehicleServlet.HORSEPOWER %>"+id).value,
            "<%= VehicleServlet.MAXSPEED %>": document.querySelector("#<%= VehicleServlet.MAXSPEED %>"+id).value,
            "<%= VehicleServlet.VEHICLE_STATE %>": document.querySelector("#<%= VehicleServlet.VEHICLE_STATE %>"+id).value,
            "<%= VehicleServlet.KILOMETERS %>": document.querySelector("#<%= VehicleServlet.KILOMETERS %>"+id).value,
            "<%= VehicleServlet.SEATING_CAPACITY %>": document.querySelector("#<%= VehicleServlet.SEATING_CAPACITY %>"+id).value,
            "<%= VehicleServlet.ID %>": id
        };

        editVehicle(data);
    }

    function editMotorbike(id) {
        const data = {
            "<%= VehicleServlet.VEHICLE_TYPE %>": "<%= VehicleServlet.MOTOR_BIKE %>",
            "<%= VehicleServlet.BRAND %>": document.querySelector("#<%= VehicleServlet.BRAND %>"+id).value,
            "<%= VehicleServlet.MODEL %>": document.querySelector("#<%= VehicleServlet.MODEL %>"+id).value,
            "<%= VehicleServlet.HORSEPOWER %>": document.querySelector("#<%= VehicleServlet.HORSEPOWER %>"+id).value,
            "<%= VehicleServlet.MAXSPEED %>": document.querySelector("#<%= VehicleServlet.MAXSPEED %>"+id).value,
            "<%= VehicleServlet.VEHICLE_STATE %>": document.querySelector("#<%= VehicleServlet.VEHICLE_STATE %>"+id).value,
            "<%= VehicleServlet.KILOMETERS %>": document.querySelector("#<%= VehicleServlet.KILOMETERS %>"+id).value,
            "<%= VehicleServlet.ID %>": id
        };

        editVehicle(data);
    }

    function editPlane(id) {
        const data = {
            "<%= VehicleServlet.VEHICLE_TYPE %>": "<%= VehicleServlet.PLANE %>",
            "<%= VehicleServlet.BRAND %>": document.querySelector("#<%= VehicleServlet.BRAND %>"+id).value,
            "<%= VehicleServlet.MODEL %>": document.querySelector("#<%= VehicleServlet.MODEL %>"+id).value,
            "<%= VehicleServlet.HORSEPOWER %>": document.querySelector("#<%= VehicleServlet.HORSEPOWER %>"+id).value,
            "<%= VehicleServlet.MAXSPEED %>": document.querySelector("#<%= VehicleServlet.MAXSPEED %>"+id).value,
            "<%= VehicleServlet.VEHICLE_STATE %>": document.querySelector("#<%= VehicleServlet.VEHICLE_STATE %>"+id).value,
            "<%= VehicleServlet.CRUISING_SPEED %>": document.querySelector("#<%= VehicleServlet.CRUISING_SPEED %>"+id).value,
            "<%= VehicleServlet.NB_MOTORS %>": document.querySelector("#<%= VehicleServlet.NB_MOTORS %>"+id).value,
            "<%= VehicleServlet.FLIGHT_HOURS %>": document.querySelector("#<%= VehicleServlet.FLIGHT_HOURS %>"+id).value,
            "<%= VehicleServlet.ID %>": id
        };

        editVehicle(data);
    }

    function editVehicle(data) {
        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'PUT',
            body: JSON.stringify(data),
            redirect: "follow",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
        .then(function (response) {
                if(response.status === 200 || response.status === 0) {
                    window.location.href = url;
                } else {
                    response.json().then(data => writeErrors(data))
                }
            }
        )
        .catch(error => console.error(error))
    }

    function writeErrors(json) {

        let div = document.createElement('div');
        div.setAttribute("class", "containter");

        let spanResult = document.createElement('span')
        spanResult.setAttribute("class", "text-danger");
        spanResult.innerText = json.<%= RESULT %>;

        div.appendChild(spanResult);
        div.appendChild(document.createElement('br'));

        for(error of json.<%= ERRORS %>) {
            let errorSpan = document.createElement('span')
            errorSpan.setAttribute("class", "text-danger");
            errorSpan.innerText = error;

            div.appendChild(errorSpan);
            div.appendChild(document.createElement('br'));
        }

        document.body.insertBefore(div, document.body.firstChild);
    }

</script>
</html>
