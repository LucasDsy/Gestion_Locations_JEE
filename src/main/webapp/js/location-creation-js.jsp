<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 25/11/2020
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>

    /** datatable mdb **/
    $(document).ready(function () {
        $('.dtCustomerVehicles').DataTable();
        $('.dataTables_length').addClass('bs-select');
    });

    /** Manage vehicle choice **/
    $(document).ready(function () {
        var elements = document.getElementsByClassName('trVehicles');
        for (var i = 0; i < elements.length; i++) {
            (elements)[i].addEventListener("click", function () {
                document.getElementById('idVehicle').value = this.children[0].innerText;
                for(var y=0; y < elements.length; y++){
                    elements[y].style.backgroundColor = "#fffefe";
                }
                this.style.backgroundColor = "#388e3c";
                if (document.getElementById('idVehicle').value != "" && document.getElementById('idCustomer').value != "") {
                    document.getElementById('submitLocation').disabled = false;
                }
            });
        }
    });

    /** Manage customer choice **/
    $(document).ready(function () {
        var elements = document.getElementsByClassName('trCustomers');
        for (var i = 0; i < elements.length; i++) {
            (elements)[i].addEventListener("click", function () {
                document.getElementById('idCustomer').value = this.children[0].innerText;
                for(var y=0; y < elements.length; y++){
                    elements[y].style.backgroundColor = "#fffefe";
                }
                this.style.backgroundColor = "#388e3c";
                if (document.getElementById('idVehicle').value != "" && document.getElementById('idCustomer').value != "") {
                    document.getElementById('submitLocation').disabled = false;
                }
            });
        }
    });

    /** Manage discount button **/
    $(document).ready(function () {
        var elements = document.getElementsByClassName('datesLocation');
        for (var i = 0; i < elements.length; i++) {
            (elements)[i].addEventListener("change", function () {
                var discountButton = document.getElementById('discount');
                var startDate = new Date(document.getElementById('startDate').value);
                var endDate = new Date(document.getElementById('endDate').value);
                var time_diff = endDate.getTime() - startDate.getTime();
                var days_diff = time_diff / (1000 * 3600 * 24);

                if(days_diff >= 7) {
                    discountButton.disabled = false;
                } else {
                    discountButton.disabled = true;
                }
            });
        }
    });

</script>
