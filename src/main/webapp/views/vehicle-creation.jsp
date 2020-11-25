<%@ page import="model.vehicle.State" %>
<%@ page import="static servlet.EmployeeServlet.RESULT" %>
<%@ page import="static servlet.EmployeeServlet.ERRORS" %>
<%@ page import="java.util.HashSet" %>
<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 23/11
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean result = (Boolean) request.getAttribute(RESULT);
    HashSet<String> errorsList = (HashSet<String>) request.getAttribute(ERRORS);
%>
<html>
<head>
    <title>Création d'un nouveau vehicule</title>
</head>

<jsp:include page="/js/vehicle-creation-js.jsp"/>

<body>
<% if(result != null){%>
<% if(result){%>
<span>Le véhicule a bien été créé</span><br/>
<% } else { %>
<span>Impossible de créer le véhicule</span><br/>
<% } %>
<% } %>
<% if(errorsList != null){%>
<% for(String err : errorsList){%>
<span><%=err%></span><br/>
<%}%>
<%}%>
<form method="post" action="vehicule">
    <fieldset>
        <legend>Informations personnelles</legend>

        <label for="modelname">Modèle</label><br/>
        <input type="text" id="modelname" name="modelname" placeholder="John"><br/>

        <label for="brandname">Marque</label><br/>
        <input type="text" id="brandname" name="brandname" placeholder="Doe"><br/>

        <label for="horsepower">Puissance (ch)</label><br/>
        <input type="text" id="horsepower" name="horsepower"><br/>

        <label for="maxspeed">Vitesse max.</label><br/>
        <input type="text" id="maxspeed" name="maxspeed"><br/>

        <label for="vehicletype">Type de véhicule</label><br/>
        <select name="vehicletype" id="vehicletype" onchange="changeFields()">
            <option value="voiture">-- Choisir --</option>
            <option value="voiture">Voiture</option>
            <option value="moto">Moto</option>
            <option value="avion">Avion</option>
        </select>

        <br>

        <label id="seatingCapacityLabel" for="seatingCapacity">Nombre de places</label><br/>
        <input type="text" id="seatingCapacity" name="seatingCapacity"><br/>

        <label id="kilometersLabel" for="kilometers">Kilométrage</label><br/>
        <input type="text" id="kilometers" name="kilometers"><br/>

        <label id="cruisingSpeedLabel" for="cruisingSpeed">Vitesse de croisière</label><br/>
        <input type="text" id="cruisingSpeed" name="cruisingSpeed"><br/>

        <label id="nbMotorsLabel" for="nbMotors">Nombre de moteurs</label><br/>
        <input type="text" id="nbMotors" name="nbMotors"><br/>

        <label id="flightHoursLabel" for="flightHours">Nombre heures vol</label><br/>
        <input type="text" id="flightHours" name="maxspeed"><br/>

        <label for="vehiclestate">Etat du véhicule</label><br/>
        <select name="vehiclestate" id="vehiclestate">
                <% for (State state : State.values()) { %>
                    <option value="<%= state.valeur %>"><%= state.toString() %></option>
                <% } %>
        </select>

    </fieldset>

    <input type="submit" value="Ajouter véhicule">
</form>
</body>
</html>
