package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectSucursalController {

    @FXML
    private ListView<String> sucursalListView; // Asegúrate de que tienes un ListView en tu FXML

    @FXML
    private Button seleccionarButton;

    // Este método debe coincidir exactamente con lo que tienes en tu FXML
    @FXML
    public void handleSucursalSeleccionada() {
        // Lógica para manejar la selección de sucursal
        String selectedSucursal = sucursalListView.getSelectionModel().getSelectedItem();
        if (selectedSucursal != null) {
            // Haz algo con la sucursal seleccionada
            System.out.println("Sucursal seleccionada: " + selectedSucursal);
        } else {
            System.out.println("No se ha seleccionado ninguna sucursal.");
        }
    }

    @FXML
    public void handleBackToAdmin() {
        try {
            MainApp.showAdminScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
