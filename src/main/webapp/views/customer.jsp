<%@ page import="model.people.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.ConvertUtil" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 23/11/2020
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Gestion client</title>
    <jsp:include page="/css/mdb-css.jsp"></jsp:include>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createCustomer">
                <i class="fas fa-plus"></i>
            </button>
        </div>

        <div class="row">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nom</th>
                        <th scope="col">Prénom</th>
                        <th scope="col">Date de naissance</th>
                        <th scope="col">Email</th>
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
                            <td><%= customer.getEmail() %></td>
                            <td><%= ConvertUtil.convertDateCalendar(customer.getBirthDate()) %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="modal/create-customer.jsp"></jsp:include>
    <jsp:include page="/js/mdb-js.jsp"></jsp:include>
</body>
</html>
