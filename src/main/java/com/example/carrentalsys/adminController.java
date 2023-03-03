package com.example.carrentalsys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminController {

    @FXML
    private AnchorPane admin_Dashboard;

    @FXML
    private AnchorPane admin_availableCars;

    @FXML
    private Button availableCars_btnClear;

    @FXML
    private Button admin_UserManag;

    @FXML
    private Button availableCars_btnDelete;

    @FXML
    private Button availableCars_btnImport;

    @FXML
    private Button availableCars_btnInsert;

    @FXML
    private Button availableCars_btnUpdate;

    @FXML
    private ComboBox<?> availableCars_cbStatus;

    @FXML
    private TableView<?> availableCars_tb;

    @FXML
    private TableColumn<?, ?> availableCars_tbBrand;

    @FXML
    private TableColumn<?, ?> availableCars_tbCarID;

    @FXML
    private TableColumn<?, ?> availableCars_tbModel;

    @FXML
    private TableColumn<?, ?> availableCars_tbPrice;

    @FXML
    private TableColumn<?, ?> availableCars_tbStatus;

    @FXML
    private TextField availableCars_tfBrand;

    @FXML
    private TextField availableCars_tfCarID;

    @FXML
    private TextField availableCars_tfModel;

    @FXML
    private TextField availableCars_tfPrice;

    @FXML
    private TextField availableCars_tfSearch;

    @FXML
    private Button btnAdminCars;

    @FXML
    private Button btnAdminDashboard;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Label home_lbTotalCars;

    @FXML
    private Label home_lbTotalCus;

    @FXML
    private Label home_lbTotalIncome;

    @FXML
    private LineChart<?, ?> home_lcCustomer;

    @FXML
    private LineChart<?, ?> home_lcIncome;

    @FXML
    private Button nav_btnHome;

    @FXML
    private Button nav_btnSignout;

    @FXML
    private Label usernameDisplay;

    public void displayUsername() {
        String user = getData.username;
        usernameDisplay.setText(user.substring(0, 1).toUpperCase() + user.substring(1).toLowerCase());
    }

    @FXML
    void adminDashboard(ActionEvent event) throws Exception {
        if (event.getSource() == btnAdminDashboard) {
            admin_Dashboard.setVisible(true);
            admin_availableCars.setVisible(false);
        } else if (event.getSource() == btnAdminCars) {
            admin_Dashboard.setVisible(false);
            admin_availableCars.setVisible(true);
        } else if (event.getSource() == admin_UserManag) {
            admin_Dashboard.setVisible(false);
            admin_availableCars.setVisible(false);


        }
    }

    @FXML
    void logout(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) nav_btnSignout.getScene().getWindow();
            stage.close();
            Main main = new Main();
            main.start(new Stage());
        } else {
            alert.close();
        }
    }

    @FXML
    void setBtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void setBtnMinimize(ActionEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        displayUsername();
    }
}
