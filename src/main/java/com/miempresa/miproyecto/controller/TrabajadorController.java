package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.dao.TrabajadorDAO;
import com.miempresa.miproyecto.model.Trabajador;
import com.miempresa.miproyecto.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Optional;

public class TrabajadorController {

    @FXML
    private TableView<Trabajador> trabajadorTable;
    @FXML
    private TableColumn<Trabajador, String> nombreColumn;
    @FXML
    private TableColumn<Trabajador, String> rolColumn;
    @FXML
    private TableColumn<Trabajador, String> puestoColumn;
    @FXML
    private TableColumn<Trabajador, String> celularColumn;
    @FXML
    private TableColumn<Trabajador, String> correoColumn;

    private TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public void initialize() {
        // Inicializa las columnas de la tabla
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol"));
        puestoColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        celularColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCelular"));
        correoColumn.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Carga los trabajadores
        actualizarLista();
    }

    @FXML
    public void handleAddTrabajador() {
        mostrarDialogoTrabajador(null);
    }

    @FXML
    public void handleEditTrabajador() {
        Trabajador seleccionado = trabajadorTable.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            mostrarDialogoTrabajador(seleccionado);
        } else {
            mostrarError("Por favor, selecciona un trabajador para editar.");
        }
    }

    @FXML
    public void handleDeleteTrabajador() {
        Trabajador seleccionado = trabajadorTable.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            eliminarTrabajador(seleccionado);
        } else {
            mostrarError("Por favor, selecciona un trabajador para eliminar.");
        }
    }

    private void mostrarDialogoTrabajador(Trabajador trabajador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/trabajador/trabajadorForm.fxml"));
            Parent root = loader.load();

            // Controlador del diálogo
            TrabajadorFormController formController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle(trabajador == null ? "Agregar Trabajador" : "Editar Trabajador");
            stage.setScene(new Scene(root));
            formController.setDialogStage(stage); // Agregar esta línea

            // Si estamos editando, pasar los datos del trabajador seleccionado
            if (trabajador != null) {
                formController.setTrabajador(trabajador);
            }

            // Mostrar el diálogo y esperar hasta que se cierre
            stage.showAndWait();

            if (formController.isSaveClicked()) {
                Trabajador nuevoTrabajador = formController.getTrabajador();

                if (trabajador == null) {
                    trabajadorDAO.create(nuevoTrabajador);  // Crear nuevo trabajador
                    mostrarMensaje("Trabajador agregado exitosamente.");
                } else {
                    trabajadorDAO.update(nuevoTrabajador);  // Actualizar trabajador
                    mostrarMensaje("Trabajador actualizado exitosamente.");
                }

                actualizarLista(); // Refrescar la lista de trabajadores
            }
        } catch (IOException e) {
            mostrarError("Error al abrir el diálogo.");
            e.printStackTrace();
        }
    }

    private void eliminarTrabajador(Trabajador trabajador) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "¿Estás seguro de eliminar este trabajador?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            trabajadorDAO.delete(trabajador.getId());
            actualizarLista();
            mostrarMensaje("Trabajador eliminado exitosamente.");
        }
    }

    private void actualizarLista() {
        trabajadorTable.getItems().setAll(trabajadorDAO.getAll());
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
