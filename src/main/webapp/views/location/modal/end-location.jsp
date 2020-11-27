<%@ page import="utils.URLUtil" %><%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 26/11/2020
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    String price = request.getParameter("price");
%>
<div class="modal" tabindex="-1" role="dialog" id="endLocation<%=id%>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="number" placeholder="Nombre km" id="km<%=id%>" onchange="sendKm(<%=id%>)">
                <p>Le prix de la location est de <span id="price<%=id%>"></span>€</p>
                <p>Êtes-vous sûr(e) de finaliser la location <%=id%> ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-success" onclick="endLocation('<%=id%>')">Finaliser</button>
            </div>
        </div>
    </div>
</div>
