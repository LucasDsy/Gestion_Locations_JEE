<%@ page import="model.vehicle.Vehicle" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 17/11/2020
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">Type</th>
            <th scope="col">Marque</th>
            <th scope="col">Modèle</th>
            <th scope="col">Prix jour</th>
        </tr>
    </thead>

    <%
        List<Vehicle> vehicles = (List) request.getAttribute("vehicles");
    %>
    <tbody>
    <% for (Vehicle vehicle : vehicles) { %>
    <tr>
        <th scope="row"><%= vehicle.getType() %></th>
        <td><%= vehicle.getBrand() %></td>
        <td><%= vehicle.getModel() %></td>
        <td><%= vehicle.getPrixLocJour() %></td>
    </tr>
    <% } %>
    </tbody>
</table>