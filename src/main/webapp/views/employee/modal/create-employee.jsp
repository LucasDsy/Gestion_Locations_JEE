<%@ page import="utils.URLUtil" %>
<%@ page import="static servlet.EmployeeServlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="modal fade" id="createEmployee" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form method="POST" action="<%= URLUtil.baseUrl("employee") %>">
                <div class="modal-header">
                    <h5 class="modal-title">Créer un utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col">
                            <label for="<%= LASTNAME %>">Nom</label>
                            <input type="text" class="form-control" id="<%= LASTNAME %>" name="<%= LASTNAME %>" required>
                        </div>

                        <div class="form-group col">
                            <label for="<%= FIRSTNAME %>">Prénom</label>
                            <input type="text" class="form-control" id="<%= FIRSTNAME %>" name="<%= FIRSTNAME %>" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="<%= BIRTHDATE %>">Date de naissance</label>
                            <input type="date" class="form-control" id="<%= BIRTHDATE %>" name="<%= BIRTHDATE %>" required>
                        </div>

                        <div class="form-group col">
                            <label for="<%= EMAIL %>">Adresse email</label>
                            <input type="email" class="form-control" id="<%= EMAIL %>" name="<%= EMAIL %>" required>
                        </div>
                    </div>

                    <jsp:include page="../reusable/role-picker.jsp">
                        <jsp:param name="roles" value=""/>
                    </jsp:include>

                    <div class="row">
                        <div class="form-group col">
                            <label for="<%= LOGIN %>">Login</label>
                            <input type="text" class="form-control" id="<%= LOGIN %>" name="<%= LOGIN %>" required>
                        </div>

                        <div class="form-group col">
                            <label for="<%= PASSWORD %>">Nouveau mot de passe</label>
                            <input type="password" class="form-control" id="<%= PASSWORD %>" name="<%= PASSWORD %>" required>
                        </div>

                        <div class="form-group col">
                            <label for="<%= PASSWORDBIS %>">Répétez le nouveau mot de passe</label>
                            <input type="password" class="form-control" id="<%= PASSWORDBIS %>" name="<%= PASSWORDBIS %>" required>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="row" style="float: right">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" class="btn btn-dark-green">Créer</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>