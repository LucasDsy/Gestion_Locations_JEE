<%@ page import="static servlet.EmployeeServlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String firstName = request.getParameter(FIRSTNAME);
    String lastName = request.getParameter(LASTNAME);
    String id = request.getParameter(ID);
%>
<div class="modal" tabindex="-1" role="dialog" id="delEmployee<%=id%>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Supprimer un utilisateur</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr(e) de vouloir supprimer l'utilisateur <%= firstName %> <%= lastName %> (<%= id %>) ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-danger" onclick="deleteEmployee('<%=id%>')">Supprimer</button>
            </div>
        </div>
    </div>
</div>
