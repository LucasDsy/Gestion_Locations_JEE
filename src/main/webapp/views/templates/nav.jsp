<%@ page import="model.people.Employee" %>
<%@ page import="static servlet.EmployeeLoginServlet.NAME_USER_SESSION" %>
<%@ page import="model.people.Role" %>
<%@ page import="utils.URLUtil" %>
<%@ page import="java.net.URL" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee emp = (Employee) request.getSession().getAttribute(NAME_USER_SESSION);
    Boolean isAdmin = emp != null && emp.getRoles().contains(Role.Administrator);
%>
<!--Navbar -->
<nav class="navbar sticky-top navbar-expand-lg navbar-dark blue">
    <div class="collapse navbar-collapse col-md-8">
        <div class="nav-bar brand mr-2">
            <a class="navbar-brand" href="<%= URLUtil.baseUrl("") %>">LocaJee</a>
        </div>
        <ul class="navbar-nav mr-auto">
            <% if (emp!= null) {%>
                    <li class="nav-item"><a class="nav-link" href="<%= URLUtil.baseUrl("location") %>">Locations</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= URLUtil.baseUrl("vehicle") %>">Véhicules</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= URLUtil.baseUrl("customer") %>">Clients</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%= URLUtil.baseUrl("commercial") %>">TOP 10 Clients</a></li>
                <% if (isAdmin) {%>
                    <li class="nav-item"><a class="nav-link" href="<%= URLUtil.baseUrl("employee") %>">Employés</a></li>
                <%}%>
            <%}%>
        <ul/>
    </div>

    <!-- Login/logout/signup -->
    <div class="container justify-content-end col-md-4">
        <% if (emp!= null) {%>
            <span class="text-warning font-weight-bold">Bienvenue <%= emp.getFirstName()+" "+emp.getLastName()%> !</span>
            <a class="btn btn-success" href="logout">Se déconnecter</a>
        <%}else{%>
            <a href="login" class="btn btn-success">Se connecter</a>
        <%}%>
    </div>
</nav>
<!--/.Navbar -->
