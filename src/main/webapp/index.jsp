<%@ page import="model.people.Employee" %>
<%@ page import="model.people.Role" %>
<%@ page import="static servlet.EmployeeLoginServlet.NAME_USER_SESSION" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee employee = (Employee) request.getSession().getAttribute(NAME_USER_SESSION);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Location Vehicule</title>
    <jsp:include page="/css/mdb-css.jsp"/>
</head>
<header>
    <jsp:include page="/views/templates/nav.jsp"/>
</header>
<body>
    <div class="jumbotron">
        <h1 class="display-4">Bienvenue, <%= employee.getFirstName() %> !</h1>
        <p class="lead">Veuillez cliquer sur un des liens ci-dessus pour commencer la gestion du parc location.</p>
        <hr class="my-4">
        <p>LocaJee Version 1.0.0</p>
    </div>
</body>
</html>