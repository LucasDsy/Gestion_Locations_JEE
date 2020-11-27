<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 23/11
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal" tabindex="-1" role="dialog" id="createVehicleModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="accordion" id="createVehicle">
                <jsp:include page="../reusable/create-car.jsp"/>
                <jsp:include page="../reusable/create-motorbike.jsp"/>
                <jsp:include page="../reusable/create-plane.jsp"/>
            </div>
        </div>
    </div>
</div>