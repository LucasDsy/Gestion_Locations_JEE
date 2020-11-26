<%@ page import="utils.URLUtil" %>
<%@ page import="static servlet.CustomerServlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String firstName = request.getParameter(FIRSTNAME);
    String lastName = request.getParameter(LASTNAME);
    String email = request.getParameter(EMAIL);
    String birthDate = request.getParameter(BIRTHDATE);
    String id = request.getParameter("putId");
%>
<div class="modal fade" id="editCustomer<%= id %>" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifer une fiche client</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="customerLastname">Nom</label>
                        <input type="text" class="form-control" id="customerLastname" name="lastName" required pattern="[A-z]{2,}" value=<%= lastName %>>
                    </div>

                    <div class="form-group col">
                        <label for="customerFirstname">Prénom</label>
                        <input type="text" class="form-control" id="customerFirstname" name="firstName" required pattern="[A-z]{2,}" value=<%= firstName %>>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col">
                        <label for="customerBirthdate">Date de naissance</label>
                        <input type="date" class="form-control" id="customerBirthdate" name="birthDate" required value=<%= birthDate %>>
                    </div>

                    <div class="form-group col">
                        <label for="customerEmail">Adresse email</label>
                        <input type="email" class="form-control" id="customerEmail" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+" value=<%= email %>>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="row" style="float: right">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="button" class="btn btn-primary" onclick="updateCustomer(<%= id %>)">Modifier</button>
                </div>
            </div>
        </div>
    </div>
</div>