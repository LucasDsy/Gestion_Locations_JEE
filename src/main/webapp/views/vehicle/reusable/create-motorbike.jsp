<%@ page import="static servlet.VehicleServlet.*" %>
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
        <div class="card-header" id="createMotorbikeBtn">
            <h2 class="mb-0">
                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#createMotorbike">
                    Créer une moto
                </button>
            </h2>
        </div>

        <div id="createMotorbike" class="collapse" data-parent="#createVehicle">
            <div class="card-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="<%= BRAND %><%= VEHICLE_TYPE %>">Marque</label>
                        <input type="text" class="form-control" id="<%= BRAND %><%= VEHICLE_TYPE %>" name="<%= BRAND %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= MODEL %><%= VEHICLE_TYPE %>">Modèle</label>
                        <input type="text" class="form-control" id="<%= MODEL %><%= VEHICLE_TYPE %>" name="<%= MODEL %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= HORSEPOWER %><%= VEHICLE_TYPE %>">Puissance</label>
                        <input type="number" min="0" max="10000" class="form-control" id="<%= HORSEPOWER %><%= VEHICLE_TYPE %>" name="<%= HORSEPOWER %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VEHICLE_STATE %><%= VEHICLE_TYPE %>">État</label>
                        <select class="form-control" id="<%= VEHICLE_STATE %><%= VEHICLE_TYPE %>" name="<%= VEHICLE_STATE %>" required>
                            <% for (State s : State.values()) { %>
                            <option value="<%= s.valeur %>"><%= s.toString() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col">
                        <label for="<%= MAXSPEED %><%= VEHICLE_TYPE %>">Vitesse maximale</label>
                        <input type="number" min="0" max="1000" class="form-control" id="<%= MAXSPEED %><%= VEHICLE_TYPE %>" name="<%= MAXSPEED %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= KILOMETERS %><%= VEHICLE_TYPE %>">Kilométrage</label>
                        <input type="number" min="0" max="1000000" class="form-control" id="<%= KILOMETERS %><%= VEHICLE_TYPE %>" name="<%= KILOMETERS %>" required>
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <input type="hidden" name="<%= VEHICLE_TYPE %>" value="<%= MOTOR_BIKE %>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="submit" class="btn btn-dark-green">Créer</button>
            </div>
        </div>
    </form>
</div>