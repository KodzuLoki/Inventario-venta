package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.model.Trabajador;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TrabajadorFormController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField rolField;
    @FXML
    private TextField puestoField;
    @FXML
    private TextField numeroCelularField; // Cambiado para coincidir con el FXML
    @FXML
    private TextField correoField;

    private Stage dialogStage;
    private Trabajador trabajador;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        // Puedes inicializar aquí si necesitas algo al principio
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
        if (trabajador != null) {
            nombreField.setText(trabajador.getNombre());
            rolField.setText(trabajador.getRol());
            puestoField.setText(trabajador.getPuesto());
            numeroCelularField.setText(trabajador.getNumeroCelular());
            correoField.setText(trabajador.getCorreo());
        }
    }

    public Trabajador getTrabajador() {
        if (trabajador == null) {
            trabajador = new Trabajador();
        }
        trabajador.setNombre(nombreField.getText());
        trabajador.setRol(rolField.getText());
        trabajador.setPuesto(puestoField.getText());
        trabajador.setNumeroCelular(numeroCelularField.getText());
        trabajador.setCorreo(correoField.getText());
        return trabajador;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre no válido!\n";
        }
        if (rolField.getText() == null || rolField.getText().length() == 0) {
            errorMessage += "Rol no válido!\n";
        }
        if (puestoField.getText() == null || puestoField.getText().length() == 0) {
            errorMessage += "Puesto no válido!\n";
        }
        if (numeroCelularField.getText() == null || numeroCelularField.getText().length() == 0) {
            errorMessage += "Celular no válido!\n";
        }
        if (correoField.getText() == null || correoField.getText().length() == 0) {
            errorMessage += "Correo no válido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos no válidos");
            alert.setHeaderText("Por favor corrige los campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
