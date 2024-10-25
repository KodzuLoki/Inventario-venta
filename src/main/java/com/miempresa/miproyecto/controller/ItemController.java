package com.miempresa.miproyecto.controller;

import com.miempresa.miproyecto.dao.ItemDAO;
import com.miempresa.miproyecto.model.Item;
import com.miempresa.miproyecto.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ItemController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField imagenField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField stockField;

    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableColumn<Item, String> nombreColumn;
    @FXML
    private TableColumn<Item, String> descripcionColumn;
    @FXML
    private TableColumn<Item, Double> precioColumn;
    @FXML
    private TableColumn<Item, Integer> stockColumn;

    private ItemDAO itemDAO = new ItemDAO();

    public void initialize() {
        // Configurar las columnas del TableView
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        descripcionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        precioColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrecio()).asObject());
        stockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        actualizarLista(); // Llenar el TableView al iniciar
    }

    @FXML
    public void handleAddItem() {
        abrirFormulario("Agregar Ítem", "/fxml/item/addItem.fxml", true);
    }

    @FXML
    public void handleEditItem() {
        if (itemTable.getSelectionModel().getSelectedItem() != null) {
            abrirFormulario("Editar Ítem", "/fxml/item/editItem.fxml", false);
        } else {
            mostrarError("Por favor, seleccione un ítem para editar.");
        }
    }

    @FXML
    public void handleDeleteItem() {
        eliminarItem();
    }

    @FXML
    private void abrirFormulario(String titulo, String vista, boolean esNuevo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();

            // Si es para agregar un nuevo ítem, limpiar campos
            if (esNuevo) {
                limpiarCampos();
            } else {
                // Si es para editar, cargar el ítem seleccionado
                cargarItemSeleccionado();
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            mostrarError("Error al abrir el formulario de ítem.");
            e.printStackTrace();
        }
    }

    private void cargarItemSeleccionado() {
        Item item = itemTable.getSelectionModel().getSelectedItem();
        if (item != null) {
            idField.setText(String.valueOf(item.getId()));
            nombreField.setText(item.getNombre());
            descripcionField.setText(item.getDescripcion());
            imagenField.setText(item.getImagen());
            precioField.setText(String.valueOf(item.getPrecio()));
            stockField.setText(String.valueOf(item.getStock()));
        }
    }

    @FXML
    public void guardarItem() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            String imagen = imagenField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Item item = new Item(id, nombre, descripcion, imagen, precio, stock);

            // Guardar o actualizar según el caso
            if (itemDAO.getById(id) == null) {
                itemDAO.create(item);
                mostrarMensaje("Ítem agregado exitosamente.");
            } else {
                itemDAO.update(item);
                mostrarMensaje("Ítem actualizado exitosamente.");
            }

            actualizarLista();
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese datos válidos.");
        } catch (Exception e) {
            mostrarError("Error al guardar el ítem.");
        }
    }

    @FXML
    public void eliminarItem() {
        try {
            int id = Integer.parseInt(idField.getText());
            itemDAO.delete(id);
            mostrarMensaje("Ítem eliminado exitosamente.");
            actualizarLista();
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            mostrarError("Error al eliminar el ítem.");
        }
    }

    private void actualizarLista() {
        itemTable.getItems().setAll(itemDAO.getAll()); // Actualizar los elementos en el TableView
    }

    private void limpiarCampos() {
        idField.clear();
        nombreField.clear();
        descripcionField.clear();
        imagenField.clear();
        precioField.clear();
        stockField.clear();
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
