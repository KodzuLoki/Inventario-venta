package com.miempresa.miproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.primaryStage = primaryStage;
        showLoginScreen();  // Iniciar con la pantalla de inicio de sesión
    }

    // Método auxiliar para establecer el tamaño de la ventana
    private static void setWindowSize(double width, double height) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }

    // Pantalla de inicio de sesión
    public static void showLoginScreen() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Inicio de Sesión");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAdminScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/admin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Cargar el CSS
            scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

            primaryStage.setTitle("Administración"); // Título para la pantalla de administración

            // Establecer tamaño de la ventana
            setWindowSize(800, 600);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Pantalla de selección de sucursal
    public static void showSucursalScreen() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/selectSucursal.fxml"));
        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Seleccionar Sucursal");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Pantalla de sucursales
    public static void showSucursales() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/sucursal.fxml"));
        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Sucursales");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Pantalla de venta
    public static void showVentaScreen(String sucursal, String usuario) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/venta.fxml"));
        Parent root = loader.load();

        // Pasar parámetros al controlador de la pantalla de venta
        VentaController ventaController = loader.getController();
        ventaController.setUsuarioYFecha(usuario, sucursal);

        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Pantalla de Venta");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Pantalla de CRUD para trabajadores
    public static void showTrabajadoresScreen() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/trabajador.fxml"));
        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Gestión de Trabajadores");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Pantalla de CRUD para ítems
    public static void showItemsScreen() throws Exception {
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/item.fxml"));
        Scene scene = new Scene(root);

        // Cargar el CSS
        scene.getStylesheets().add(MainApp.class.getResource("/css/styles.css").toExternalForm());

        primaryStage.setTitle("Gestión de Ítems");

        // Establecer tamaño de la ventana
        setWindowSize(800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
