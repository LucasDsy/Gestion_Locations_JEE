<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 16/11/2020
  Time: 13:47
--%>
<div class="container-fluid">
    <form method="POST" action="">
        <h5 class="modal-title">Modifier une fiche client</h5>

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
                <input type="date" class="form-control" value="${birthdate}" id="customerBirthdate" name="birthdate" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group col">
                <label for="customerEmail">Adresse email</label>
                <input type="email" class="form-control" value="${email}" id="customerEmail" name="email" required pattern="[^@ \t\r\n]+@[^@ \t\r\n]+\.[^@ \t\r\n]+">
            </div>
        </div>


        <div class="row">
            <button type="submit" style="float:right" class="btn btn-primary">Modifier</button>
        </div>
    </form>
</div>