<%@ page import="model.people.Role" %><%--
  Created by IntelliJ IDEA.
  User: corentin
  Date: 17/11/2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création de nouvel utilisateur</title>
</head>
<body>
    <span>${result}</span>
    <span>${errors["sql"]}</span>
    <form method="post" action="createEmployee">
        <fieldset>
            <legend>Informations personnelles</legend>

            <label for="firstName">Prénom</label><br/>
            <input type="text" id="firstName" name="firstName" placeholder="John"><br/><span>${errors["firstName"]}</span><br/>

            <label for="lastName">Nom</label><br/>
            <input type="text" id="lastName" name="lastName" placeholder="Doe"><br/><span>${errors["lastName"]}</span><br/>

            <label for="birthday">Date de naissance</label><br/>
            <input type="date" id="birthday" name="birthday"><span>${errors["birthday"]}</span><br/>
        </fieldset>
        <fieldset>
            <legend>Informations pour l'entreprise</legend>

            <label for="email">Email</label><br/>
            <input type="email" id="email" name="email" placeholder="jdoe@company.fr"><br/><span>${errors["email"]}</span><br/>

            <label for="rolesDiv">Roles</label><br/>
            <div id="rolesDiv">
                <%for(Role role : Role.values()){%>
                    <input type="checkbox" id="<%=role%>" name="roles" value="<%=role%>">
                    <label for="<%=role%>"><%=role.getWording()%></label><br/>
                <%}%>
            </div>
            <span>${errors["roles"]}</span>
        </fieldset>
        <fieldset>
            <legend>Identifiants</legend>

            <label for="login">Login</label><br/>
            <input type="text" id="login" name="login" placeholder="login"><br/><span>${errors["login"]}</span><br/>

            <label for="password">Mot de passe</label><br/>
            <input type="password" id="password" name="password"><br/><span>${errors["password"]}</span><br/>
        </fieldset>
        <input type="submit" value="Valider">
    </form>
</body>
</html>
