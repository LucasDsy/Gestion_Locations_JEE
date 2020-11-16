<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 13:47
--%>
<div class="modal fade bd-example-modal-lg" id="editClient" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <form method="POST" action="${action}">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${actionTitle} une fiche client</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span>&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col">
                            <label for="customerLastname">Nom</label>
                            <input type="text" class="form-control" value="${lastname}" id="customerLastname" name="lastname" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="customerFirstname">Prénom</label>
                            <input type="text" class="form-control" value="${firstname}"id="customerFirstname" name="firstname" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="customerBirthdate">Date de naissance</label>
                            <input type="date" class="form-control" value="${birthdate}" id="customerBirthdate" name="birthdate" required pattern="(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="customerEmail">Adresse email</label>
                            <input type="email" class="form-control" value="${email}" id="customerEmail" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">${actionTitle}</button>
                </div>
            </div>
        </form>
    </div>
</div>