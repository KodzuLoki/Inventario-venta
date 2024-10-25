package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.dao.SucursalDAO;
import com.miempresa.miproyecto.model.Sucursal;
import com.miempresa.miproyecto.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SucursalController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField direccionField;
    @FXML
    private ListView<Sucursal> sucursalListView;

    private SucursalDAO sucursalDAO = new SucursalDAO();

    public void initialize() {
        // Cargar datos iniciales
        sucursalListView.getItems().setAll(sucursalDAO.getAll());
    }

    @FXML
    public void handleAddSucursal() {
        abrirFormulario("Agregar Sucursal", "/fxml/sucursal/addSucursal.fxml", true);
    }

    @FXML
    public void handleEditSucursal() {
        if (sucursalListView.getSelectionModel().getSelectedItem() != null) {
            abrirFormulario("Editar Sucursal", "/fxml/sucursal/editSucursal.fxml", false);
        } else {
            mostrarError("Por favor, seleccione una sucursal para editar.");
        }
    }

    @FXML
    public void handleDeleteSucursal() {
        eliminarSucursal();
    }

    @FXML
    private void abrirFormulario(String titulo, String vista, boolean esNuevo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();

            // Si es para agregar una nueva sucursal, limpiar campos
            if (esNuevo) {
                limpiarCampos();
            } else {
                // Si es para editar, cargar la sucursal seleccionada
                cargarSucursalSeleccionada();
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            mostrarError("Error al abrir el formulario de sucursal.");
            e.printStackTrace();
        }
    }

    private void cargarSucursalSeleccionada() {
        Sucursal sucursal = sucursalListView.getSelectionModel().getSelectedItem();
        if (sucursal != null) {
            idField.setText(String.valueOf(sucursal.getId()));
            nombreField.setText(sucursal.getNombre());
            direccionField.setText(sucursal.getDireccion());
        }
    }

    @FXML
    public void guardarSucursal() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();

            Sucursal sucursal = new Sucursal(id, nombre, direccion);

            // Guardar o actualizar según el caso
            if (sucursalDAO.getById(id) == null) {
                sucursalDAO.create(sucursal);
                mostrarMensaje("Sucursal agregada exitosamente.");
            } else {
                sucursalDAO.update(sucursal);
                mostrarMensaje("Sucursal actualizada exitosamente.");
            }

            actualizarLista();
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            mostrarError("Error al guardar la sucursal.");
        }
    }

    @FXML
    public void eliminarSucursal() {
        try {
            int id = Integer.parseInt(idField.getText());
            sucursalDAO.delete(id);
            mostrarMensaje("Sucursal eliminada exitosamente.");
            actualizarLista();
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            mostrarError("Error al eliminar la sucursal.");
        }
    }

    private void actualizarLista() {
        sucursalListView.getItems().setAll(sucursalDAO.getAll());
    }

    private void limpiarCampos() {
        idField.clear();
        nombreField.clear();
        direccionField.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION, mensaje, ButtonType.OK);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR, mensaje, ButtonType.OK);
        alert.showAndWait();
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
