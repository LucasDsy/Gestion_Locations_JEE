<%@ page import="static servlet.EmployeeServlet.*" %>
<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 26/11/2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter(ID);
%>
<div class="modal fade" id="changePassword<%= id %>" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifer un mot de passe</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="<%= OLDPASSWORD %><%= id %>">Ancien mot de passe</label>
                        <input type="password" class="form-control" id="<%= OLDPASSWORD %><%= id %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= PASSWORD %><%= id %>">Nouveau mot de passe</label>
                        <input type="password" class="form-control" id="<%= PASSWORD %><%= id %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= PASSWORDBIS %><%= id %>">Répétez le nouveau mot de passe</label>
                        <input type="password" class="form-control" id="<%= PASSWORDBIS %><%= id %>" required>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row" style="float: right">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="button" class="btn btn-primary" onclick="changePassword(<%= id %>)">Modifier</button>
                </div>
            </div>
        </div>
    </div>
</div>