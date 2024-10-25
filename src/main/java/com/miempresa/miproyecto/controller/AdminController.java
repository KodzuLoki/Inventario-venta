package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.MainApp; // Asegúrate de importar MainApp
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminController {

    @FXML
    private Label welcomeLabel; // Asegúrate de que este id coincida con el FXML

    @FXML
    public void initialize() {
        welcomeLabel.setText("Bienvenido al Panel de Administración");
    }

    @FXML
    public void showTrabajadores() {
        try {
            MainApp.showTrabajadoresScreen(); // Cambia a la pantalla de trabajadores
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showSucursales() {
        try {
            MainApp.showSucursales(); // Cambia a la pantalla de sucursales
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showItems() {
        try {
            MainApp.showItemsScreen(); // Cambia a la pantalla de ítems
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showSucursalScreen() { // Agrega este método
        try {
            MainApp.showSucursalScreen(); // Cambia a la pantalla de selección de sucursal
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
