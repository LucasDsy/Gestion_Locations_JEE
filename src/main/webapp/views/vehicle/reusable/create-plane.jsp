<%@ page import="servlet.VehicleServlet" %>
<%@ page import="model.vehicle.State" %>
<%@ page import="utils.URLUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 26/11/2020
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<div class="card">
    <form method="post" action="<%= URLUtil.baseUrl("vehicle") %>">
        <div class="card-header" id="createPlaneBtn">
            <h2 class="mb-0">
                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#createPlane">
                    Créer un avion
                </button>
            </h2>
        </div>

        <div id="createPlane" class="collapse" data-parent="#createVehicle">
            <div class="card-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="<%= VehicleServlet.BRAND %>">Marque</label>
                        <input type="text" class="form-control" id="<%= VehicleServlet.BRAND %>" name="<%= VehicleServlet.BRAND %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.MODEL %>">Modèle</label>
                        <input type="text" class="form-control" id="<%= VehicleServlet.MODEL %>" name="<%= VehicleServlet.MODEL %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.HORSEPOWER %>">Puissance</label>
                        <input type="number" min="0" max="10000" class="form-control" id="<%= VehicleServlet.HORSEPOWER %>" name="<%= VehicleServlet.HORSEPOWER %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.VEHICLE_STATE %>">État</label>
                        <select class="form-control" id="<%= VehicleServlet.VEHICLE_STATE %>" name="<%= VehicleServlet.VEHICLE_STATE %>" required>
                            <% for (State s : State.values()) { %>
                            <option value="<%= s.valeur %>"><%= s.toString() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col">
                        <label for="<%= VehicleServlet.MAXSPEED %>">Vitesse maximale</label>
                        <input type="number" min="0" max="1000" class="form-control" id="<%= VehicleServlet.MAXSPEED %>" name="<%= VehicleServlet.MAXSPEED %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.CRUISING_SPEED %>">Vitesse de croisière</label>
                        <input type="number" min="0" max="10000" class="form-control" id="<%= VehicleServlet.CRUISING_SPEED %>" name="<%= VehicleServlet.CRUISING_SPEED %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.FLIGHT_HOURS %>">Heures de vol</label>
                        <input type="number" min="0" max="1000000" class="form-control" id="<%= VehicleServlet.FLIGHT_HOURS %>" name="<%= VehicleServlet.FLIGHT_HOURS %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.NB_MOTORS %>">Nombre de moteurs</label>
                        <input type="number" min="0" max="12" class="form-control" id="<%= VehicleServlet.NB_MOTORS %>" name="<%= VehicleServlet.NB_MOTORS %>" required>
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <input type="hidden" name="<%= VehicleServlet.VEHICLE_TYPE %>" value="<%= VehicleServlet.PLANE %>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="submit" class="btn btn-dark-green">Créer</button>
            </div>
        </div>
    </form>
</div>