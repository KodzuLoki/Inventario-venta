package com.miempresa.miproyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VentaController {

    @FXML
    private Label usuarioLabel;
    @FXML
    private Label sucursalLabel;
    @FXML
    private ListView<String> itemsListView;
    @FXML
    private Button agregarButton;
    @FXML
    private TextArea boletaTextArea;
    @FXML
    private Button cerrarVentaButton;

    private double totalSinIva = 0.0;
    private final double IVA = 0.19;

    @FXML
    public void initialize() {
        // Añadir productos a la lista
        itemsListView.getItems().addAll("Café - $2.000", "Churrasco - $4.000", "Completo - $3.000", "Ron Cola - $5.000");

        agregarButton.setOnAction(e -> {
            String itemSeleccionado = itemsListView.getSelectionModel().getSelectedItem();
            if (itemSeleccionado != null) {
                boletaTextArea.appendText(itemSeleccionado + "\n");
                totalSinIva += extraerPrecio(itemSeleccionado);
            }
        });

        cerrarVentaButton.setOnAction(e -> {
            double iva = totalSinIva * IVA;
            double totalConIva = totalSinIva + iva;

            boletaTextArea.appendText("\n--------------------------\n");
            boletaTextArea.appendText("Total sin IVA: $" + String.format("%.2f", totalSinIva) + "\n");
            boletaTextArea.appendText("IVA (19%): $" + String.format("%.2f", iva) + "\n");
            boletaTextArea.appendText("Total con IVA: $" + String.format("%.2f", totalConIva) + "\n");
        });
    }

    public void setUsuarioYFecha(String usuario, String sucursal) {
        usuarioLabel.setText("Usuario: " + usuario);
        sucursalLabel.setText("Sucursal: " + sucursal);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fecha = dtf.format(LocalDateTime.now());

        boletaTextArea.appendText("Usuario: " + usuario + "\n");
        boletaTextArea.appendText("Sucursal: " + sucursal + "\n");
        boletaTextArea.appendText("Fecha: " + fecha + "\n");
        boletaTextArea.appendText("\n--------------------------\n");
    }

    private double extraerPrecio(String item) {
        String[] partes = item.split(" - \\$");
        return Double.parseDouble(partes[1].replace(".", ""));
    }
}
