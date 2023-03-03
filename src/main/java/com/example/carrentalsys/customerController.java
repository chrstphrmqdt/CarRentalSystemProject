package com.example.carrentalsys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerController implements Initializable {

    @FXML
    private Label usernameDisplay;
    @FXML
    private Button account_btnUpdate;
    @FXML
    private Button btnAccount;

    @FXML
    private Button btnReservations;


    @FXML
    private TextField account_tfAddress;

    @FXML
    private TextField account_tfCardH;

    @FXML
    private TextField account_tfCardNum;

    @FXML
    private TextField account_tfCvc;

    @FXML
    private TextField account_tfEmail;

    @FXML
    private TextField account_tfExp;

    @FXML
    private TextField account_tfFirstn;

    @FXML
    private TextField account_tfLastN;

    @FXML
    private AnchorPane admin_Dashboard;

    @FXML
    private AnchorPane admin_availableCars;

    @FXML
    private Button availableCars_btnClear;

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
    private Button btnCars;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private AnchorPane customer_Home;

    @FXML
    private AnchorPane customer_Rentacar;

    @FXML
    private AnchorPane customer_myAccount;

    @FXML
    private AnchorPane customer_reservations;

    @FXML
    private ImageView home_carLogo;

    @FXML
    private ImageView home_carsImage;

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
    private Button rent_btnRent;

    @FXML
    private ImageView rent_carImage;

    @FXML
    private DatePicker rent_dpDateRented;

    @FXML
    private DatePicker rent_dpDateReturned;

    @FXML
    private Label rent_lbTotal;

    @FXML
    private TableView<?> rent_tableview;

    @FXML
    private TableColumn<?, ?> rent_tbBrand;

    @FXML
    private TableColumn<?, ?> rent_tbCarID;

    @FXML
    private TableColumn<?, ?> rent_tbModel;

    @FXML
    private TableColumn<?, ?> rent_tbPrice;

    @FXML
    private TableColumn<?, ?> rent_tbStatus;

    @FXML
    private Button reservations_btnModify;

    @FXML
    private Button reservations_btnNewRes;

    @FXML
    private Tab reservations_tbCurrent;

    @FXML
    private TableView<?> reservations_tbCurrent_tv;

    @FXML
    private Tab reservations_tbPast;

    @FXML
    private TableView<?> reservations_tbPast_tv;

    @FXML
    private TabPane reservations_tbp;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_Brand;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_CarID;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_CarRented;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_CarReturned;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_Model;

    @FXML
    private TableColumn<?, ?> reservations_tvCur_Price;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_Brand;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_CarID;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_CarRented;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_CarReturn;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_Model;

    @FXML
    private TableColumn<?, ?> reservations_tvPast_Price;

    public void displayUsername() {
        String user = getData.username;
        usernameDisplay.setText(user.substring(0, 1).toUpperCase() + user.substring(1).toLowerCase());
    }
// navigation buttons functions

    public void customerDashboard(ActionEvent event) throws IOException {
        if (event.getSource() == nav_btnHome) {
            customer_Home.setVisible(true);
            customer_myAccount.setVisible(false);
            customer_Rentacar.setVisible(false);
            customer_reservations.setVisible(false);

        } else if (event.getSource() == btnAccount) {
            customer_myAccount.setVisible(true);
            customer_Home.setVisible(false);
            customer_Rentacar.setVisible(false);
            customer_reservations.setVisible(false);

        } else if (event.getSource() == btnCars) {
            customer_Rentacar.setVisible(true);
            customer_Home.setVisible(false);
            customer_myAccount.setVisible(false);
            customer_reservations.setVisible(false);

        } else if (event.getSource() == btnReservations) {
            customer_reservations.setVisible(true);
            customer_Home.setVisible(false);
            customer_myAccount.setVisible(false);
            customer_Rentacar.setVisible(false);

        }
    }

    public void logout(ActionEvent event) throws Exception {
        // this will go back to the login page and call the start method from the Main.java
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

    public void setBtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void setBtnMinimize(ActionEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
    }
}
