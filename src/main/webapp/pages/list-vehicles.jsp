<%@ page import="model.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="model.vehicle.Car" %>
<%@ page import="model.vehicle.MotorBike" %>
<%@ page import="model.vehicle.Plane" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 17/11/2020
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des véhicules</title>
    <jsp:include page="/css/mdb-css.jsp"/>
</head>
<body>
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

                <%
                    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
                %>
                <tbody>
                    <% for (Vehicle vehicle : vehicles) { %>
                        <%
                            String type;

                            if (vehicle instanceof Car) type = "Voiture";
                            else if (vehicle instanceof MotorBike) type = "Moto";
                            else if (vehicle instanceof Plane) type = "Avion";
                            else type = "#UNDEDFINED#";
                        %>
                        <tr>
                            <th scope="row"><%= type %></th>
                            <td><%= vehicle.getBrand() %></td>
                            <td><%= vehicle.getModel() %></td>
                            <td><%= vehicle.getPrixLocJour() %></td>
                            <td><button class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></button></td>
                            <td><button class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></button></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>

<jsp:include page="/js/mdb-js.jsp"/>
</html>
