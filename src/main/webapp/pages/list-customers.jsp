<%@ page import="model.people.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 13:47
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des clients</title>
    <jsp:include page="/css/mdb-css.jsp"/>
</head>
<body>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Modifier</th>
                    <th scope="col">Supprimer</th>
                </tr>
            </thead>

            <%
                List<Customer> customers = (List) request.getAttribute("customers");
            %>
            <tbody>
                <% for (Customer customer : customers) { %>
                    <tr>
                        <th scope="row"><%= customer.getId() %></th>
                        <td><%= customer.getLastName() %></td>
                        <td><%= customer.getFirstName() %></td>
                        <td><%= customer.getBirthDate() %>></td>
                        <td><button class="btn btn-primary btn-sm"><i class="fas fa-edit"></i></button></td>
                        <td><button class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></button></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>