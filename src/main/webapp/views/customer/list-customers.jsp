<%@ page import="model.people.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="static servlet.CustomerServlet.ERRORS" %>
<%@ page import="static servlet.CustomerServlet.RESULT" %>
<%@ page import="static servlet.CustomerServlet.*" %>
<%@ page import="utils.URLUtil" %>
<%@ page import="utils.ConvertUtil" %>
<%@ page import="model.people.Employee" %>
<%@ page import="static servlet.EmployeeLoginServlet.NAME_USER_SESSION" %>
<%@ page import="model.people.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Employee connected = (Employee) request.getSession().getAttribute(NAME_USER_SESSION);
    boolean canEdit = connected.getRoles().contains(Role.Administrator) || connected.getRoles().contains(Role.ClientManager);

    List<Customer> customers = (List) request.getAttribute(CUSTOMER_ATTRIBUTE);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des clients</title>
    <jsp:include page="/css/mdb-css.jsp"/>
    <jsp:include page="/js/mdb-js.jsp"/>
</head>
<body>
    <header>
        <jsp:include page="/views/templates/nav.jsp"/>
    </header>

    <div class="container-fluid">
        <div class="row">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nom</th>
                        <th scope="col">Prénom</th>
                        <th scope="col">Date de naissance</th>
                        <% if (canEdit) { %>
                            <th scope="col">Modifier</th>
                            <th scope="col">Supprimer</th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <% for (Customer customer : customers) { %>
                        <tr>
                            <th scope="row"><%= customer.getId() %></th>
                            <td><%= customer.getLastName() %></td>
                            <td><%= customer.getFirstName() %></td>
                            <td><%= new SimpleDateFormat("dd MMM yyyy").format(customer.getBirthDate().getTime()) %></td>
                            <% if (canEdit) { %>
                                <td><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editCustomer<%=customer.getId()%>"><i class="fas fa-edit"></i></button></td>
                                <td><button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delCustomer<%=customer.getId()%>"><i class="fas fa-trash"></i></button></td>
                                <jsp:include page="modal/edit-customer.jsp" flush="true">
                                    <jsp:param name="firstName" value="<%=customer.getFirstName()%>"/>
                                    <jsp:param name="lastName" value="<%=customer.getLastName()%>"/>
                                    <jsp:param name="email" value="<%=customer.getEmail()%>"/>
                                    <jsp:param name="birthDate" value="<%=ConvertUtil.convertDateCalendar(customer.getBirthDate())%>"/>
                                    <jsp:param name="putId" value="<%=customer.getId().toString()%>"/>
                                </jsp:include>
                                <jsp:include page="modal/delete-customer.jsp" flush="true">
                                    <jsp:param name="delFirstName" value="<%=customer.getFirstName()%>"/>
                                    <jsp:param name="delLastName" value="<%=customer.getLastName()%>"/>
                                    <jsp:param name="delId" value="<%=customer.getId().toString()%>"/>
                                </jsp:include>
                            <% } %>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
    <% if (canEdit) { %>
        <div style="position:fixed; bottom: 25px; right: 24px;"><button class="btn btn-dark-green rounded-pill" data-toggle="modal" data-target="#createCustomer"><i class="fas fa-plus"></i> Ajouter</button></div>
        <jsp:include page="modal/create-customer.jsp"/>
    <% } %>
</body>
<script>
    var url = "<%= URLUtil.baseUrl("customer")%>";

    function updateCustomer(id) {
        let data = {
            id: id,
            lastName: document.querySelector("#customerLastname"+id).value,
            firstName: document.querySelector("#customerFirstname"+id).value,
            email: document.querySelector("#customerEmail"+id).value,
            birthDate: document.querySelector("#customerBirthdate"+id).value
        };

        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'PUT',
            body: JSON.stringify(data),
            redirect: "manual",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
        .then(response => {
                if(response.status === 200 || response.status === 0) {
                    window.location.href = url;
                } else {
                    response.json().then(data => writeErrors(data))
                }
            })

        .catch(error => console.error(error))
    }

    function deleteCustomer(id) {
        let data = {};
        data.id = id;

        fetch(url, {
            method: 'DELETE',
            body: JSON.stringify(data),
            redirect: "follow",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
            .then(function (response) {
                    if(response.status === 200 || response.status === 0) {
                        window.location.href = url;
                    } else {
                        response.json().then(data => writeErrors(data))
                    }
                }
            )
            .catch(error => console.error(error))
    }

    function writeErrors(json) {

        let div = document.createElement('div');
        div.setAttribute("class", "containter");

        let spanResult = document.createElement('span')
        spanResult.setAttribute("class", "text-danger");
        spanResult.innerText = json.<%= RESULT %>;

        div.appendChild(spanResult);
        div.appendChild(document.createElement('br'));

        for(error of json.<%= ERRORS %>) {
            let errorSpan = document.createElement('span')
            errorSpan.setAttribute("class", "text-danger");
            errorSpan.innerText = error;

            div.appendChild(errorSpan);
            div.appendChild(document.createElement('br'));
        }

        document.body.insertBefore(div, document.body.firstChild);
    }
</script>
</html>