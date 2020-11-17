<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 17/11/2020
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<div class="modal fade bd-example-modal-lg" id="editPlane" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <form method="POST" action="${action}">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${actionTitle} une fiche avion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span>&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-3">
                            <label for="brandName">Marque</label>
                            <input type="text" class="form-control" value="${marque}" id="brandName" name="brand" required>
                        </div>

                        <div class="form-group col-3">
                            <label for="modelName">Modèle</label>
                            <input type="text" class="form-control" value="${model}"id="modelName" name="model" required>
                        </div>

                        <div class="form-group col-3">
                            <label for="power">Puissance</label>
                            <input type="number" min="0" max="10000" class="form-control" value="${power}" id="power" name="email" required>
                        </div>

                        <div class="form-group col-3">
                            <label for="maxSpeed">Vitesse maximum</label>
                            <input type="number" min="0" max="10000" class="form-control" value="${maxSpeed}" id="maxSpeed" name="maxSpeed" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-3">
                            <label for="state">État</label>
                            <select class="custom-select form-control" id="state" name="state">
                                <option value="0">Neuf</option>
                                <option value="1">Dégats mineurs</option>
                                <option value="2">Dégats majeurs</option>
                                <option value="3">Hors service</option>
                            </select>
                        </div>

                        <div class="form-group col-3">
                            <label for="cruisingSpeed">Vitesse de croisière</label>
                            <input type="number" min="0" max="10000" class="form-control" value="${cruisingSpeed}" id="cruisingSpeed" name="cruisingSpeed" required>
                        </div>

                        <div class="form-group col-3">
                            <label for="flyingHours">Heures de vol</label>
                            <input type="number" min="0" max="1000000000" class="form-control" value="${flyingHours}" id="flyingHours" name="flyingHours" required>
                        </div>

                        <div class="form-group col-3">
                            <label for="flightHours">Nombre de moteurs</label>
                            <input type="number" min="0" max="9" class="form-control" value="${flightHours}" id="flightHours" name="flightHours" required>
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
