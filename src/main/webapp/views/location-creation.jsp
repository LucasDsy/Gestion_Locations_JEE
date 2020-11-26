<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 25/11/2020
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.vehicle.Vehicle" %>
<%@ page import="model.people.Customer" %>
<%@ page import="static servlet.EmployeeServlet.RESULT" %>
<%@ page import="static servlet.EmployeeServlet.ERRORS" %>
<%@ page import="static servlet.LocationServlet.VEHICLES_LIST" %>
<%@ page import="static servlet.LocationServlet.CUSTOMERS_LIST" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.List" %>
<%
    /** Results **/
    Boolean result = (Boolean) request.getAttribute(RESULT);
    HashSet<String> errorsList = (HashSet<String>) request.getAttribute(ERRORS);

    /** Datas **/
    List<Vehicle> listVehicles = (List<Vehicle>) request.getAttribute(VEHICLES_LIST);
    List<Customer> listCustomers = (List<Customer>) request.getAttribute(CUSTOMERS_LIST);
%>

<html>
<head>
    <title>Saisie d'une nouvelle location</title>
</head>
<body>

<%-- Libs --%>
<jsp:include page="/js/mdb-js.jsp"/>
<jsp:include page="/css/mdb-css.jsp"/>

<%-- Custom --%>
<jsp:include page="/js/location-creation-js.jsp"/>

<% if(result != null){%>
    <% if(result){%>
        <span>La location a bien été ajoutée</span><br/>
    <% } else { %>
        <span>Impossible d'ajouter la location</span><br/>
    <% } %>
<% } %>
<% if(errorsList != null){%>
    <% for(String err : errorsList){%>
        <span><%=err%></span><br/>
    <%}%>
<%}%>

<table id="dtVehicles" class="table table-bordered" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th class="th-sm">Id</th>
            <th class="th-sm">Marque</th>
            <th class="th-sm">Modèle</th>
            <th class="th-sm">Vitesse max.</th>
            <th class="th-sm">Puissance (ch.)</th>
            <th class="th-sm">Etat</th>
        </tr>
    </thead>
    <tbody>
        <% if (listVehicles != null) { %>
            <% for (Vehicle vehicle : listVehicles) { %>
            <tr class="trVehicles">
                <td><%= vehicle.getId() %></td>
                <td><%= vehicle.getBrand() %></td>
                <td><%= vehicle.getModel() %></td>
                <td><%= vehicle.getMaxSpeed() %></td>
                <td><%= vehicle.getHorsePower() %></td>
                <td><%= vehicle.getState() %></td>
            </tr>
                <%}%>
            <%}%>
    </tfoot>
</table>

<table id="dtCustomers" class="table table-bordered" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="th-sm">Id</th>
        <th class="th-sm">Prénom</th>
        <th class="th-sm">Nom</th>
        <th class="th-sm">Email</th>
    </tr>
    </thead>
    <tbody>
        <% if (listCustomers != null) { %>
            <% for (Customer customer : listCustomers) { %>
        <tr class="trCustomers">
            <td><%= customer.getId() %></td>
            <td><%= customer.getFirstName() %></td>
            <td><%= customer.getLastName() %></td>
            <td><%= customer.getEmail() %></td>
        </tr>
            <%}%>
            <%}%>
    </tfoot>
</table>

<form method="post" action="location">
    <fieldset>
        <input id="idVehicle" name="idVehicle" type="hidden" value="">
        <input id="idCustomer" name="idCustomer" type="hidden" value="">

        <label for="estimatedKilometers">Distance estimée (km.)</label><br/>
        <input id="estimatedKilometers" name="estimatedKilometers" type="text" value="">

        <label for="discount">Réduction</label><br/>
        <select name="discount" id="discount" onchange="changeFields()">
            <option value="false">Non</option>
            <option value="true">Oui</option>
        </select>

        <label for="startDate">Date début</label><br/>
        <input type="date" id="startDate" name="startDate"
               value="2020-11-27"
               min="2020-01-01" max="2022-12-31">

        <label for="endDate">Date fin</label><br/>
        <input type="date" id="endDate" name="endDate"
               value="2020-11-27"
               min="2020-01-01" max="2022-12-31">

        <input type="submit" value="Ajouter location">
    </fieldset>
</form>

</body>
</html>
