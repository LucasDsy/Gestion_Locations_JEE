<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 25/11/2020
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="static servlet.VehicleServlet.*" %>
<%@ page import="model.vehicle.State" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String brand = request.getParameter(BRAND);
    String model = request.getParameter(MODEL);
    String horsePower = request.getParameter(HORSEPOWER);
    String maxSpeed = request.getParameter(MAXSPEED);
    int state = Integer.parseInt(request.getParameter(VEHICLE_STATE));
    String cruisingSpeed = request.getParameter(CRUISING_SPEED);
    String nbMotors = request.getParameter(NB_MOTORS);
    String flightHours = request.getParameter(FLIGHT_HOURS);
    String id = request.getParameter(ID);
%>
<div class="modal fade bd-example-modal-lg" id="editPlane<%= id %>" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modifier une fiche avion</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span>&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="form-group col">
                        <label for="<%= BRAND %><%= id %>">Marque</label>
                        <input type="text" class="form-control" value="<%= brand %>" id="<%= BRAND %><%= id %>" name="<%= BRAND %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= MODEL %><%= id %>">Modèle</label>
                        <input type="text" class="form-control" value="<%= model %>" id="<%= MODEL %><%= id %>" name="<%= MODEL %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= HORSEPOWER %><%= id %>">Puissance</label>
                        <input type="number" min="0" max="10000" class="form-control" value="<%= horsePower %>" id="<%= HORSEPOWER %><%= id %>" name="<%= HORSEPOWER %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= VEHICLE_STATE %><%= id %>">État</label>
                        <select class="form-control" id="<%= VEHICLE_STATE %><%= id %>" name="<%= VEHICLE_STATE %>" required>
                            <% for (State s : State.values()) { %>
                                <option value="<%= s.valeur %>" <%= (s.valeur == state) ? "selected" : "" %>>
                                    <%= s.toString() %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col">
                        <label for="<%= FLIGHT_HOURS %><%= id %>">Heures de vol</label>
                        <input type="number" min="0" max="1000000" class="form-control" value="<%= flightHours %>" id="<%= FLIGHT_HOURS %><%= id %>" name="<%= FLIGHT_HOURS %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= CRUISING_SPEED %><%= id %>">Vitesse de croisière</label>
                        <input type="number" min="0" max="10000" class="form-control" value="<%= cruisingSpeed %>" id="<%= CRUISING_SPEED %><%= id %>" name="<%= CRUISING_SPEED %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= MAXSPEED %><%= id %>">Vitesse maximum</label>
                        <input type="number" min="0" max="10000" class="form-control" value="<%= maxSpeed %>" id="<%= MAXSPEED %><%= id %>" name="<%= MAXSPEED %>" required>
                    </div>

                    <div class="form-group col">
                        <label for="<%= NB_MOTORS %><%= id %>">Nombre de moteurs</label>
                        <input type="number" min="0" max="12" class="form-control" value="<%= nbMotors %>" id="<%= NB_MOTORS %><%= id %>" name="<%= NB_MOTORS %>" required>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary" onclick="editPlane(<%= id %>)">Modifier</button>
            </div>
        </div>
    </div>
</div>