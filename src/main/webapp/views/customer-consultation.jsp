<%@ page import="servlet.CustomerServlet" %>
<%@ page import="model.people.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultation du client</title>
</head>
<body>
    <%  if ((boolean)request.getAttribute("found")) { %>
        <%  Customer customer = (Customer)request.getAttribute("customer"); %>
        <h1>Client numéro <%= customer.getId() %></h1>
        <p>Nom : <%= customer.getFirstName() %> <%= customer.getLastName().toUpperCase() %></p>
        <p>Email : <%= customer.getEmail() %></p>
        <p>Date de naissance : <%= customer.getBirthDate().getTime().toString() %></p>
    <% } else { %>
        <h1>Client introuvable</h1>
    <% } %>

</body>
</html>
