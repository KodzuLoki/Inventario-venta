package com.miempresa.miproyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SucursalController {

    @FXML
    private ChoiceBox<String> sucursalChoiceBox;
    @FXML
    private Button continuarButton;

    @FXML
    public void initialize() {
        // Añadir sucursales disponibles
        sucursalChoiceBox.getItems().addAll("Maipu", "San Miguel", "Providencia");

        continuarButton.setOnAction(e -> {
            String sucursalSeleccionada = sucursalChoiceBox.getValue();
            if (sucursalSeleccionada != null) {
                try {
                    MainApp.showVentaScreen(sucursalSeleccionada, "admin");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Debe seleccionar una sucursal");
            }
        });
    }
}
