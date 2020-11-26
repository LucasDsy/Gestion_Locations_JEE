<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String firstName = request.getParameter("delFirstName");
    String lastName = request.getParameter("delLastName");
    String id = request.getParameter("delId");
%>
<div class="modal" tabindex="-1" role="dialog" id="delCustomer<%=id%>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Supprimer un client</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr(e) de vouloir supprimer le client <%= firstName %> <%= lastName %> (<%= id %>) ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-danger"  data-dismiss="modal" onclick="deleteCustomer('<%=id%>')">Supprimer</button>
            </div>
        </div>
    </div>
</div>
