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
        <div class="card-header" id="createCarBtn">
            <h2 class="mb-0">
                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#createCar">
                    Créer une voiture
                </button>
            </h2>
        </div>

        <div id="createCar" class="collapse show" data-parent="#createVehicle">
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
                        <label for="<%= VehicleServlet.KILOMETERS %>">Kilométrage</label>
                        <input type="number" min="0" max="1000000" class="form-control" id="<%= VehicleServlet.KILOMETERS %>" name="<%= VehicleServlet.KILOMETERS %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VehicleServlet.SEATING_CAPACITY %>">Nombre de places</label>
                        <input type="number" min="0" max="9" class="form-control" id="<%= VehicleServlet.SEATING_CAPACITY %>" name="<%= VehicleServlet.SEATING_CAPACITY %>" required>
                    </div>
                </div>
            </div>

            <div class="card-footer">
                <input type="hidden" name="<%= VehicleServlet.VEHICLE_TYPE %>" value="<%= VehicleServlet.CAR %>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <button type="submit" class="btn btn-dark-green">Créer</button>
            </div>
        </div>
    </form>
</div>