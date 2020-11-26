<%@ page import = "java.io.*,java.util.*" contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="utils.URLUtil" %>
<%@ page import="static servlet.EmployeeLoginServlet.*" %>
<%
    String msgLogin = (String) request.getAttribute(MESSAGE);
%>
<html>
<head>
    <title>Se connecter</title>
    <jsp:include page="../css/mdb-css.jsp"></jsp:include>
</head>
<body>
    <div class="container-fluid h-100 bg-light">
        <div class="row align-items-center h-100">
            <div class="col-sm-5 mx-auto">
                <div class="card card-block">
                    <h5 class="card-header primary-color white-text text-center py-4">
                        <strong>Se connecter</strong>
                    </h5>

                    <!--Card content-->
                    <div class="card-body px-lg-5 pt-0">

                        <!-- Form -->
                        <form class="text-center" style="color: #757575;" action="<%= URLUtil.baseUrl("login") %>" method="post">

                            <%if(msgLogin != null){%>
                                <div id="errors" class="mb-2 mt-2">
                                    <p class="text-danger">
                                        <%= msgLogin %>
                                    </p>
                                </div>
                            <%}%>

                            <!-- Login -->
                            <div class="md-form">
                                <input type="text" id="<%= LOGIN %>" name="<%= LOGIN %>" class="form-control mb-4"><br/>
                                <label for="<%= LOGIN %>">Identifiant</label>
                            </div>

                            <!-- Password -->
                            <div class="md-form">
                                <input type="password" id="<%= PASSWORD %>" name="<%= PASSWORD %>" class="form-control mb-4">
                                <label for="<%= PASSWORD %>">Mot de passe</label>
                            </div>

                            <!-- Sign in button -->
                            <button class="btn btn-outline-primary btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">Se connecter</button>

                            <!-- Register -->
                            <p>Merci de contacter l'administrateur si vous n'avez pas de compte</p>
                        </form>
                        <!-- Form -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<jsp:include page="../js/mdb-js.jsp"></jsp:include>
