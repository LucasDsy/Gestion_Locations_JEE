<%@ page import="model.people.Role" %>
<%@ page import="static servlet.EmployeeServlet.RESULT" %>
<%@ page import="static servlet.EmployeeServlet.ERRORS" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%--
  Created by IntelliJ IDEA.
  User: corentin
  Date: 17/11/2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Boolean result = (Boolean) request.getAttribute(RESULT);
    HashSet<String> errorsList = (HashSet<String>) request.getAttribute(ERRORS);
%>
<html>
<head>
    <title>Création de nouvel utilisateur</title>
</head>
<body>
    <% if(result != null){%>
        <% if(result){%>
            <span>L'utilisateur a bien été créé</span><br/>
        <% } else { %>
            <span>Impossible de créer l'utilisateur</span><br/>
        <% } %>
    <%}%>
    <% if(errorsList != null){%>
        <% for(String err : errorsList){%>
            <span><%=err%></span><br/>
        <%}%>
    <%}%>
    <form method="post" action="createEmployee">
        <fieldset>
            <legend>Informations personnelles</legend>

            <label for="firstName">Prénom</label><br/>
            <input type="text" id="firstName" name="firstName" placeholder="John"><br/>

            <label for="lastName">Nom</label><br/>
            <input type="text" id="lastName" name="lastName" placeholder="Doe"><br/>

            <label for="birthday">Date de naissance</label><br/>
            <input type="date" id="birthday" name="birthday"><br/>
        </fieldset>
        <fieldset>
            <legend>Informations pour l'entreprise</legend>

            <label for="email">Email</label><br/>
            <input type="email" id="email" name="email" placeholder="jdoe@company.fr"><br/>

            <label for="rolesDiv">Roles</label><br/>
            <div id="rolesDiv">
                <%for(Role role : Role.values()){%>
                    <input type="checkbox" id="<%=role%>" name="roles" value="<%=role%>">
                    <label for="<%=role%>"><%=role.getWording()%></label><br/>
                <%}%>
            </div>
        </fieldset>
        <fieldset>
            <legend>Identifiants</legend>

            <label for="login">Login</label><br/>
            <input type="text" id="login" name="login" placeholder="login"><br/>

            <label for="password">Mot de passe</label><br/>
            <input type="password" id="password" name="password"><br/><br/>
        </fieldset>
        <input type="submit" value="Valider">
    </form>
</body>
</html>
