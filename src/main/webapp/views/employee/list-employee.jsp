<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="static servlet.EmployeeServlet.ERRORS" %>
<%@ page import="static servlet.EmployeeServlet.RESULT" %>
<%@ page import="static servlet.EmployeeServlet.*" %>
<%@ page import="utils.URLUtil" %>
<%@ page import="utils.ConvertUtil" %>
<%@ page import="model.people.Employee" %>
<%@ page import="model.people.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Employee connected = (Employee) request.getAttribute("admin");
    String result = (String) request.getAttribute(RESULT);
    HashSet<String> errorsList = (HashSet<String>) request.getAttribute(ERRORS);
    List<Employee> employees = (List<Employee>) request.getAttribute(EMPLOYEE_ATTRIBUTE);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des employés</title>
    <jsp:include page="/css/mdb-css.jsp"/>
    <jsp:include page="/js/mdb-js.jsp"/>
</head>
<body>
    <% if(errorsList != null && !errorsList.isEmpty()){%>
        <div class="container">
        <span class="text-danger"><%=result%></span><br/>
        <% for(String err : errorsList){%>
            <span class="text-danger"><%=err%></span><br/>
        <%}%>
        </div>
    <%} else if(result!=null){%>
        <span class="text-success"><%=result%></span><br/>
    <%}%>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Modifier</th>
                    <th scope="col">Mot de passe</th>
                    <th scope="col">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <% for (Employee employee : employees) { %>
                    <tr>
                        <th scope="row"><%= employee.getId() %></th>
                        <td><%= employee.getLastName() %></td>
                        <td><%= employee.getFirstName() %></td>
                        <td><%= new SimpleDateFormat("dd/MM/yyyy").format(employee.getBirthDate().getTime()) %></td>
                        <td><button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editEmployee<%=employee.getId()%>"><i class="fas fa-edit"></i></button></td>
                        <td><button class="btn btn-warning btn-sm" data-toggle="modal" data-target="#changePassword<%=employee.getId()%>"><i class="fas fa-lock"></i></button></td>
                        <td><button class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delEmployee<%=employee.getId()%>"><i class="fas fa-trash"></i></button></td>
                        <jsp:include page="modal/edit-employee.jsp" flush="true">
                            <jsp:param name="firstName" value="<%=employee.getFirstName()%>"/>
                            <jsp:param name="lastName" value="<%=employee.getLastName()%>"/>
                            <jsp:param name="email" value="<%=employee.getEmail()%>"/>
                            <jsp:param name="birthdate" value="<%=ConvertUtil.convertDateCalendar(employee.getBirthDate())%>"/>
                            <jsp:param name="roles" value='<%=ConvertUtil.convertRolesListToString(employee.getRoles())%>'/>
                            <jsp:param name="id" value="<%=employee.getId().toString()%>"/>
                            <jsp:param name="login" value="<%=employee.getLogin()%>"/>
                        </jsp:include>
                        <jsp:include page="modal/delete-employee.jsp" flush="true">
                            <jsp:param name="firstName" value="<%=employee.getFirstName()%>"/>
                            <jsp:param name="lastName" value="<%=employee.getLastName()%>"/>
                            <jsp:param name="id" value="<%=employee.getId().toString()%>"/>
                        </jsp:include>
                        <jsp:include page="modal/change-password.jsp" flush="true">
                            <jsp:param name="id" value="<%=employee.getId().toString()%>"/>
                        </jsp:include>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <div style="position:fixed; bottom: 25px; right: 24px;"><button class="btn btn-dark-green rounded-pill" data-toggle="modal" data-target="#createEmployee"><i class="fas fa-plus"></i> Ajouter</button></div>
    <jsp:include page="modal/create-employee.jsp"/>
</body>
</html>

<script>
    const url = "<%= URLUtil.baseUrl("employee")%>";

    function deleteEmployee(id) {
        const data = {};
        data.id = id;

        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'DELETE',
            body: JSON.stringify(data),
            redirect: "follow",
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

    function updateEmployee(id) {
        const data = {
            "<%= ID %>": id,
            "<%= LASTNAME %>": document.querySelector("#<%= LASTNAME %>"+id).value,
            "<%= FIRSTNAME %>": document.querySelector("#<%= FIRSTNAME %>"+id).value,
            "<%= EMAIL %>": document.querySelector("#<%= EMAIL %>"+id).value,
            "<%= BIRTHDATE %>": document.querySelector("#<%= BIRTHDATE %>"+id).value,
            "<%= LOGIN %>": document.querySelector("#<%= LOGIN %>"+id).value
        };

        <% for (Role role : Role.values()) { %>
            if (document.querySelector("#<%= role %>"+id).checked) {
                data.<%= role %> = "on";
            }
        <% } %>

        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'PUT',
            body: JSON.stringify(data),
            redirect: "follow",
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
        })
        .then(response => {
            console.log(response)
            if(response.status === 200 || response.status === 0) {
                window.location.href = url;
            } else {
                response.json().then(data => writeErrors(data))
            }
        })
        .catch(error => console.error(error))
    }

    function changePassword(id) {
        const data = {
            "<%= ID %>": id,
            "<%= OLDPASSWORD %>": document.querySelector("#<%= OLDPASSWORD %>"+id).value,
            "<%= PASSWORD %>": document.querySelector("#<%= PASSWORD %>"+id).value,
            "<%= PASSWORDBIS %>": document.querySelector("#<%= PASSWORDBIS %>"+id).value
        }

        fetch(url, {
            //credentials: 'same-origin', // 'include', default: 'omit'

            method: 'PUT',
            body: JSON.stringify(data),
            redirect: "follow",
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