<%@ page import="servlet.VehicleServlet" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 25/11/2020
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String brand = request.getParameter(VehicleServlet.BRAND);
    String model = request.getParameter(VehicleServlet.MODEL);
    String id = request.getParameter(VehicleServlet.ID);
%>
<div class="modal" tabindex="-1" role="dialog" id="deleteVehicle<%= id %>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Supprimer un véhicule</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <p>Êtes-vous sûr(e) de vouloir supprimer le véhicule <%= brand %> <%= model %> (<%= id %>) ?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="button" class="btn btn-danger" onclick="deleteVehicle('<%= id %>')">Supprimer</button>
            </div>
        </div>
    </div>
</div>
