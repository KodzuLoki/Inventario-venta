package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> {
            String usuario = usuarioField.getText();
            String password = passwordField.getText();

            if (usuario.equals("admin") && password.equals("12345")) {
                try {
                    // Cambia esta línea para cargar admin.fxml
                    MainApp.showAdminScreen(); // Método nuevo que debes implementar
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        });
    }
}
