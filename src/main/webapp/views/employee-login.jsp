<%@ page import = "java.io.*,java.util.*" contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="static servlet.EmployeeLoginServlet.MESSAGE" %>
<%
    String msgLogin = (String) request.getAttribute(MESSAGE);
%>
<html>
<head>
    <title>Login</title>
    <jsp:include page="../css/mdb-css.jsp"></jsp:include>
</head>
<body>
    <form method="post" action="login">
        <fieldset>
            <legend>Identifiants</legend>

            <label for="login">Login</label><br/>
            <input type="text" id="login" name="login" placeholder="login"><br/><span>${errors["login"]}</span><br/>

            <label for="password">Mot de passe</label><br/>
            <input type="password" id="password" name="password"><br/><span>${errors["password"]}</span><br/>
        </fieldset>
        <input type="submit" value="Valider">
    </form>
    <%if(msgLogin != null){%>
        <p class="text-danger">
            <%= msgLogin %>
        </p>
    <%}%>
</body>
</html>
