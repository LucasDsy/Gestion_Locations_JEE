<%@ page import="model.people.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 13:47
--%>
<table class="table table-striped">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Nom</th>
            <th scope="col">Prénom</th>
            <th scope="col">Date de naissance</th>
        </tr>
    </thead>

    <%
        int i = 1;
        List<Customer> customers = (List) request.getAttribute("customers");
    %>
    <tbody>
        <% for (Customer customer : customers) { %>
            <tr>
                <th scope="row"><%= customer.getId() %></th>
                <td><%= customer.getLastName() %></td>
                <td><%= customer.getFirstName() %></td>
                <td><%= customer.getBirthDate() %>></td>
            </tr>
        <% } %>
    </tbody>
</table>