<%@ page import="model.people.Employee" %>
<%@ page import="model.people.Role" %>
<%@ page import="static servlet.EmployeeLoginServlet.NAME_USER_SESSION" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee emp = (Employee) request.getSession().getAttribute(NAME_USER_SESSION);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Location Vehicule</title>
</head>
<header>
    <% if (emp!= null) {%>
        <% if (emp.getRoles().contains(Role.RentalManager)) {%>
            <div><a href="location">Locations</a></div>
        <%}%>
        <% if (emp.getRoles().contains(Role.TechnicalManager)) {%>
            <div><a href="vehicle">Véhicules</a></div>
        <%}%>
        <% if (emp.getRoles().contains(Role.ClientManager)) {%>
            <div><a href="client">Clients</a></div>
        <%}%>
        <% if (emp.getRoles().contains(Role.ClientManager)) {%>
            <div><a href="top10">TOP 10 Clients</a></div>
        <%}%>
        <div><a href="logout">Se déconnecter</a></div>
    <%}else{%>
        <div><a href="login">Se connecter</a></div>
        <div><a href="employee">S'inscrire</a></div>
    <%}%>
</header>
<body>


</body>
</html>