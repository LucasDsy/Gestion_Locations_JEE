<%@ page import="static servlet.EmployeeServlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String firstName = request.getParameter(FIRSTNAME);
    String lastName = request.getParameter(LASTNAME);
    String email = request.getParameter(EMAIL);
    String birthDate = request.getParameter(BIRTHDATE);
    String roles = request.getParameter(ROLES);
    String login = request.getParameter(LOGIN);
    String id = request.getParameter(ID);
%>
<div class="modal fade" id="editEmployee<%= id %>" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifer un utilisateur</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="<%= LASTNAME %><%= id %>">Nom</label>
                        <input type="text" class="form-control" id="<%= LASTNAME %><%= id %>" required value="<%= lastName %>">
                    </div>

                    <div class="form-group col">
                        <label for="<%= FIRSTNAME %><%= id %>">Prénom</label>
                        <input type="text" class="form-control" id="<%= FIRSTNAME %><%= id %>" required value="<%= firstName %>">
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col">
                        <label for="<%= BIRTHDATE %><%= id %>">Date de naissance</label>
                        <input type="date" class="form-control" id="<%= BIRTHDATE %><%= id %>" required value="<%= birthDate %>">
                    </div>

                    <div class="form-group col">
                        <label for="<%= EMAIL %><%= id %>">Adresse email</label>
                        <input type="email" class="form-control" id="<%= EMAIL %><%= id %>" required value="<%= email %>">
                    </div>
                </div>

                <jsp:include page="../reusable/role-picker.jsp">
                    <jsp:param name="id" value="<%= id %>"/>
                    <jsp:param name="roles" value="<%= roles %>"/>
                </jsp:include>

                <div class="row">
                    <div class="form-group col-6">
                        <label for="<%= LOGIN %><%= id %>">Login</label>
                        <input type="text" class="form-control" id="<%= LOGIN %><%= id %>" name="<%= LOGIN %>" required value="<%= login %>">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row" style="float: right">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="button" class="btn btn-primary" onclick="updateEmployee(<%= id %>)">Modifier</button>
                </div>
            </div>
        </div>
    </div>
</div>