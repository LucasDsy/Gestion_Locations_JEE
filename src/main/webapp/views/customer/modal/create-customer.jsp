<%@ page import="utils.URLUtil" %>
<%@ page import="static servlet.CustomerServlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String firstName = (String) request.getAttribute(FIRSTNAME);
    String lastName = (String) request.getAttribute(LASTNAME);
    String email = (String) request.getAttribute(EMAIL);
%>
<div class="modal fade" id="createCustomer" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form method="POST" action="<%= URLUtil.baseUrl("customer") %>">
                <div class="modal-header">
                    <h5 class="modal-title">Créer une fiche client</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col">
                            <label for="customerLastname">Nom</label>
                            <input type="text" class="form-control" id="customerLastname" name="lastName" required pattern="[A-z]{2,}" value=<%= lastName != null ? lastName:"" %>>
                        </div>

                        <div class="form-group col">
                            <label for="customerFirstname">Prénom</label>
                            <input type="text" class="form-control" id="customerFirstname" name="firstName" required pattern="[A-z]{2,}" value=<%= firstName != null ? firstName:"" %>>
                        </div>

                        <div class="form-group col">
                            <label for="customerBirthdate">Date de naissance</label>
                            <input type="date" class="form-control" id="customerBirthdate" name="birthDate" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="customerEmail">Adresse email</label>
                            <input type="email" class="form-control" id="customerEmail" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+" value=<%= email != null ? email:"" %>>
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