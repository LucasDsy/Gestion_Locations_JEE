<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 15:57
--%>
<div class="modal fade bd-example-modal-lg" id="editVehicle" tabindex="-1" role="dialog">
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
                            <label for="nomClient">Nom</label>
                            <input type="text" class="form-control" value="${lastname}" id="nomClient" name="lastname" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="prenomClient">Prénom</label>
                            <input type="text" class="form-control" value="${firstname}"id="prenomClient" name="firstname" required pattern="[A-z]{2,}">
                        </div>

                        <div class="form-group col">
                            <label for="dateNaissClient">Date de naissance</label>
                            <input type="date" class="form-control" value="${birthdate}" id="dateNaissClient" name="birthdate" required pattern="(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="emailClient">Adresse email</label>
                            <input type="email" class="form-control" value="${email}" id="emailClient" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Créer</button>
                </div>
            </div>
        </form>
    </div>
</div>