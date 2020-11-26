<%@ page import="model.people.Role" %>
<%@ page import="static servlet.EmployeeServlet.*" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.ConvertUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: alexis_choupault
  Date: 26/11/2020
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String rolesString = request.getParameter(ROLES);
    String id = request.getParameter(ID);
    List<String> checkedRoles = ConvertUtil.parseStringWithCommas(rolesString);

%>
<div class="row">
    <label for="hiddenDesign">Roles</label>
    <input type="hidden" id="hiddenDesign">
</div>
<div class="row">
    <div class="form-group col" id="<%= ROLES %><%= id %>">
        <% for (Role role : Role.values()) { %>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="<%= role %><%= id %>" name="<%= role %>" value="<%= role %>" <%= checkedRoles.contains(role.toString()) ? "checked" : "" %>>
                <label class="form-check-label" for="<%= role %><%= id %>"><%= role.getWording() %></label>
            </div>
        <% } %>
    </div>
</div>