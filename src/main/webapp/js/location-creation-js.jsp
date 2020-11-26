<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 25/11/2020
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready(function () {
        $('#dtVehicles').DataTable();
        $('.dataTables_length').addClass('bs-select');
    });

    $(document).ready(function () {
        $('#dtCustomers').DataTable();
        $('.dataTables_length').addClass('bs-select');
    });

    $(document).ready(function () {
        var elements = document.getElementsByClassName('trVehicles');
        for (var i = 0; i < elements.length; i++) {
            (elements)[i].addEventListener("click", function () {
                document.getElementById('idVehicle').value = this.children[0].innerText;
                for(var y=0; y < elements.length; y++){
                    elements[y].style.backgroundColor = "#fffefe";
                }
                this.style.backgroundColor = "#00aaa9";
            });
        }
    });

    $(document).ready(function () {
        var elements = document.getElementsByClassName('trCustomers');
        for (var i = 0; i < elements.length; i++) {
            (elements)[i].addEventListener("click", function () {
                document.getElementById('idCustomer').value = this.children[0].innerText;
                for(var y=0; y < elements.length; y++){
                    elements[y].style.backgroundColor = "#fffefe";
                }
                this.style.backgroundColor = "#00aaa9";
            });
        }
    });

</script>
