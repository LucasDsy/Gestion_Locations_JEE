<%@ page import="utils.URLUtil" %>
<%@ page import="static servlet.LocationServlet.*" %>
<%@ page import="model.vehicle.Vehicle" %>
<%@ page import="model.people.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Vehicle> listVehicles = (List<Vehicle>) request.getAttribute(VEHICLES_LIST);
    List<Customer> listCustomers = (List<Customer>) request.getAttribute(CUSTOMERS_LIST);
%>

<jsp:include page="/js/location-creation-js.jsp" />

<div class="modal fade" id="createLocation" role="dialog" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form method="POST" action="<%= URLUtil.baseUrl("location") %>">
                <div class="modal-header">
                    <h5 class="modal-title">Ajouter une nouvelle location</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col">
                            <label for="tableCustomers">Choisissez un client</label><br/>
                            <table id="tableCustomers" class="dtCustomerVehicles table table-striped">
                                <thead>
                                <tr>
                                    <th class="th-sm">Id</th>
                                    <th class="th-sm">Prénom</th>
                                    <th class="th-sm">Nom</th>
                                    <th class="th-sm">Email</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <% if (listCustomers != null) { %>
                                        <% for (Customer customer : listCustomers) { %>
                                            <tr class="trCustomers">
                                                <td><%= customer.getId() %></td>
                                                <td><%= customer.getFirstName() %></td>
                                                <td><%= customer.getLastName() %></td>
                                                <td><%= customer.getEmail() %></td>
                                            </tr>
                                        <%}%>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="tableVehicles">Choisissez un véhicule</label><br/>
                            <table id="tableVehicles" class="dtCustomerVehicles table table-striped">
                                <thead>
                                    <tr>
                                        <th class="th-sm">Id</th>
                                        <th class="th-sm">Marque</th>
                                        <th class="th-sm">Modèle</th>
                                        <th class="th-sm">Puissance (ch.)</th>
                                        <th class="th-sm">Etat</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (listVehicles != null) { %>
                                        <% for (Vehicle vehicle : listVehicles) { %>
                                            <tr class="trVehicles">
                                                <td><%= vehicle.getId() %></td>
                                                <td><%= vehicle.getBrand() %></td>
                                                <td><%= vehicle.getModel() %></td>
                                                <td><%= vehicle.getHorsePower() %></td>
                                                <td><%= vehicle.getState() %></td>
                                            </tr>
                                        <%}%>
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <input id="idVehicle" name="idVehicle" type="hidden" value="" required>
                            <input id="idCustomer" name="idCustomer" type="hidden" value="" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="estimatedKilometers">Distance estimée (km.)</label><br/>
                            <input class="form-control" id="estimatedKilometers" name="estimatedKilometers" type="text" value="" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col">
                            <label for="discount">Réduction</label><br/>
                            <select class="form-control" name="discount" id="discount" disabled required>
                                <option value="false">Non</option>
                                <option value="true">Oui</option>
                            </select>
                        </div>

                        <div class="form-group col">
                            <label for="startDate">Date début</label>
                            <input class="datesLocation form-control" type="date" id="startDate" name="startDate"
                                   value="2020-11-27"
                                   min="2020-01-01" max="2022-12-31" required>
                        </div>

                        <div class="form-group col">
                            <label for="endDate">Date fin</label>
                            <input class="datesLocation form-control" type="date" id="endDate" name="endDate"
                                   value="2020-11-27"
                                   min="2020-01-01" max="2022-12-31" required>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <div class="row" style="float: right">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button id="submitLocation" type="submit" class="btn btn-dark-green" disabled>Créer</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
