
<!-- JS -->
<script>

    /** Select **/
    var selectVehicleType = "vehicletype";

    /** Car **/
    var carSeatingCapacity = "seatingCapacity";
    var carKilometers = "kilometers";
    var carSeatingCapacityLabel = "seatingCapacityLabel";
    var carKilometersLabel = "kilometersLabel";

    /** Motorbike **/
    var motorbikeKilometers = "kilometers";
    var motorbikeKilometersLabel = "kilometersLabel";

    /** Plane **/
    var planeCruisingSpeed = "cruisingSpeed";
    var planeNbMotors = "nbMotors";
    var planeFlightHours = "flightHours";
    var planeCruisingSpeedLabel = "cruisingSpeedLabel";
    var planeNbMotorsLabel = "nbMotorsLabel";
    var planeFlightHoursLabel = "flightHoursLabel";

    /** Types **/
    var CAR = "voiture";
    var MOTORBIKE = "moto";
    var PLANE = "avion";

    function hideEverything() {
        document.getElementById(carSeatingCapacity).style.display = 'none';
        document.getElementById(carKilometers).style.display = 'none';
        document.getElementById(planeCruisingSpeed).style.display = 'none';
        document.getElementById(planeNbMotors).style.display = 'none';
        document.getElementById(planeFlightHours).style.display = 'none';
        document.getElementById(carKilometersLabel).style.display = 'none';
        document.getElementById(carSeatingCapacityLabel).style.display = 'none';
        document.getElementById(planeCruisingSpeedLabel).style.display = 'none';
        document.getElementById(planeFlightHoursLabel).style.display = 'none';
        document.getElementById(planeNbMotorsLabel).style.display = 'none';
    }

    function showCarOptions() {
        hideEverything();
        document.getElementById(carSeatingCapacity).style.display = 'block';
        document.getElementById(carKilometers).style.display = 'block';
        document.getElementById(carKilometersLabel).style.display = 'block';
        document.getElementById(carSeatingCapacityLabel).style.display = 'block';
    }

    function showMotorbikeOptions() {
        hideEverything();
        document.getElementById(motorbikeKilometers).style.display = 'block';
        document.getElementById(motorbikeKilometersLabel).style.display = 'block';
    }

    function showPlaneOptions() {
        hideEverything();
        document.getElementById(planeCruisingSpeed).style.display = 'block';
        document.getElementById(planeNbMotors).style.display = 'block';
        document.getElementById(planeFlightHours).style.display = 'block';
        document.getElementById(planeCruisingSpeedLabel).style.display = 'block';
        document.getElementById(planeFlightHoursLabel).style.display = 'block';
        document.getElementById(planeNbMotorsLabel).style.display = 'block';
    }

    function changeFields() {
        var select = document.getElementById(selectVehicleType);
        switch (select.value) {
            case MOTORBIKE:
                showMotorbikeOptions();
                break;
            case PLANE:
                showPlaneOptions();
                break;
            case CAR:
                showCarOptions();
                break;
        }

    }

</script>
