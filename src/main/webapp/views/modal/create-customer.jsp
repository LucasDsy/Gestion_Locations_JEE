<%@ page import="utils.URLUtil" %><%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 13:47
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="createCustomer" tabindex="-1">
    <div class="modal-dialog">
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
                            <input type="text" class="form-control" id="customerLastname" name="lastName" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="customerFirstname">Prénom</label>
                            <input type="text" class="form-control" id="customerFirstname" name="firstName" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="customerBirthdate">Date de naissance</label>
                            <input type="date" class="form-control" id="customerBirthdate" name="birthDate" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="customerEmail">Adresse email</label>
                            <input type="email" class="form-control" id="customerEmail" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="row" style="float: right">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" class="btn btn-primary">Créer</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>