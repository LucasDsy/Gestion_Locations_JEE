<%@ page import="static servlet.LocationServlet.ID" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 26/11/2020
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String id = request.getParameter(ID);
%>
<div class="modal" tabindex="-1" role="dialog" id="delLocation<%=id%>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Supprimer une location</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr(e) de vouloir supprimer la location <%= id %> ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-danger" onclick="deleteLocation('<%=id%>')">Supprimer</button>
            </div>
        </div>
    </div>
</div>
