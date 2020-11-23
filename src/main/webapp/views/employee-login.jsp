<%@ page import = "java.io.*,java.util.*" contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <span>${result}</span>
    <span>${errors["sql"]}</span>
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
    <p>
    <%
        String MESSAGE = "msgLogin";

        String msgLogin = (String) request.getAttribute(MESSAGE);
        if(msgLogin != null)
            System.out.println(msgLogin);
    %>
    </p>
</body>
</html>
