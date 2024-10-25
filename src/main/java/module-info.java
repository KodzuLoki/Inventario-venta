module com.miempresa.miproyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.miempresa.miproyecto to javafx.fxml;
    exports com.miempresa.miproyecto;
    exports com.miempresa.miproyecto.controller;
    opens com.miempresa.miproyecto.controller to javafx.fxml;
}
