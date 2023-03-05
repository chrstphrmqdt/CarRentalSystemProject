package com.example.carrentalsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerController implements Initializable {


    @FXML
    private ImageView customer_carImage;
    @FXML
    private Label rent_balance;
    @FXML
    private ComboBox rent_CarID;

    @FXML
    private TextField rent_firstName;

    @FXML
    private TextField rent_lastName;

    @FXML
    private TextField rent_amount;

    @FXML
    private ComboBox rent_Brand;

    @FXML
    private ComboBox rent_Model;

    @FXML
    private ComboBox rent_Gender;

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
    private TableView<carData> rent_tableview;

    @FXML
    private TableColumn<carData, String> rent_tbBrand;

    @FXML
    private TableColumn<carData, String> rent_tbCarID;

    @FXML
    private TableColumn<carData, String> rent_tbModel;

    @FXML
    private TableColumn<carData, String> rent_tbPrice;

    @FXML
    private TableColumn<carData, String> rent_tbStatus;

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

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;
    
    public void availableCarSelect() {
        carData carD = rent_tableview.getSelectionModel().getSelectedItem();
        //set image
        getData.path = carD.getImage();
        String uri = "file:" + carD.getImage();
        image = new Image(uri, 283, 179, false, true);
        customer_carImage.setImage(image);
    }

// navigation buttons functions

    public void customerDashboard(ActionEvent event) throws IOException {
        if (event.getSource() == nav_btnHome) {
            customer_Home.setVisible(true);
            customer_myAccount.setVisible(false);
            customer_Rentacar.setVisible(false);


        } else if (event.getSource() == btnAccount) {
            customer_myAccount.setVisible(true);
            customer_Home.setVisible(false);
            customer_Rentacar.setVisible(false);


        } else if (event.getSource() == btnCars) {
            customer_Rentacar.setVisible(true);
            customer_Home.setVisible(false);
            customer_myAccount.setVisible(false);


            rentCarShowListData();
            rentCarCarId();
            rentCarBrand();
            rentCarModel();
            rentCarGender();


        } else if (event.getSource() == btnReservations) {
            customer_reservations.setVisible(true);
            customer_Home.setVisible(false);
            customer_myAccount.setVisible(false);
            customer_Rentacar.setVisible(false);

        }
    }
    public void rentPay(){

        rentCustomerId();

        String sql = "INSERT INTO customer "
                + "(customer_id, firstName, lastName, gender, car_id, brand"
                + ", model, total, date_rented, date_return) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = database.connectdb();

        try{
            Alert alert;

            if(rent_firstName.getText().isEmpty()
                    || rent_lastName.getText().isEmpty()
                    || rent_Gender.getSelectionModel().getSelectedItem() == null
                    || rent_CarID.getSelectionModel().getSelectedItem() == null
                    || rent_Brand.getSelectionModel().getSelectedItem() == null
                    || rent_Model.getSelectionModel().getSelectedItem() == null
                    || totalP == 0 || rent_amount.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            }else{

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, rent_firstName.getText());
                    prepare.setString(3, rent_lastName.getText());
                    prepare.setString(4, (String)rent_Gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String)rent_CarID.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String)rent_Brand.getSelectionModel().getSelectedItem());
                    prepare.setString(7, (String)rent_Model.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(totalP));
                    prepare.setString(9, String.valueOf(rent_dpDateRented.getValue()));
                    prepare.setString(10, String.valueOf(rent_dpDateReturned.getValue()));

                    prepare.executeUpdate();

                    // SET THE  STATUS OF CAR TO NOT AVAILABLE
                    String updateCar = "UPDATE car SET status = 'Not Available' WHERE car_id = '"
                            +rent_CarID.getSelectionModel().getSelectedItem()+"'";

                    statement = connect.createStatement();
                    statement.executeUpdate(updateCar);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();

                    rentCarShowListData();

                    rentClear();
                } // NOW LETS PROCEED TO DASHBOARD FORM : )
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void rentClear(){
        totalP = 0;
        rent_firstName.setText("");
        rent_lastName.setText("");
        rent_Gender.getSelectionModel().clearSelection();
        amount = 0;
        balance = 0;
        rent_balance.setText("₱0.0");
        rent_lbTotal.setText("₱0.0");
        rent_amount.setText("");
        rent_CarID.getSelectionModel().clearSelection();
        rent_Brand.getSelectionModel().clearSelection();
        rent_Model.getSelectionModel().clearSelection();
    }
    private int customerId;
    public void rentCustomerId(){
        String sql = "SELECT id FROM customer";

        connect = database.connectdb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                // GET THE LAST id and add + 1
                customerId = result.getInt("id") + 1;
            }

        }catch(Exception e){e.printStackTrace();}
    }
    private double amount;
    private double balance;
    public void rentAmount(){
        Alert alert;
        if(totalP == 0 || rent_amount.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid");
            alert.showAndWait();

            rent_amount.setText("");
        }else{
            amount = Double.parseDouble(rent_amount.getText());

            if(amount >= totalP){
                balance = (amount - totalP);
                rent_balance.setText("₱" + String.valueOf(balance));
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid");
                alert.showAndWait();

                rent_amount.setText("");
            }

        }

    }

    public ObservableList<carData> rentCarListData() {

        ObservableList<carData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM car";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            carData carD;

            while (result.next()) {
                carD = new carData(result.getInt("car_id"), result.getString("brand"),
                        result.getString("model"), result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private int countDate;
    public void rentDate(){
        Alert alert;
        if(rent_CarID.getSelectionModel().getSelectedItem() == null
                || rent_Brand.getSelectionModel().getSelectedItem() == null
                || rent_Model.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something wrong");
            alert.showAndWait();

            rent_dpDateRented.setValue(null);
            rent_dpDateReturned.setValue(null);

        }else{

            if(rent_dpDateReturned.getValue().isAfter(rent_dpDateRented.getValue())){
                // COUNT THE DAY
                countDate = rent_dpDateReturned.getValue().compareTo(rent_dpDateRented.getValue());
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong");
                alert.showAndWait();
                // INCREASE OF 1 DAY ONCE THE USER CLICKED THE SAME DAY
                rent_dpDateReturned.setValue(rent_dpDateRented.getValue().plusDays(1));

            }
        }
    }
    private double totalP;
    public void rentDisplayTotal(){
        rentDate();
        double price = 0;
        String sql = "SELECT price, model FROM car WHERE model = '"
                +rent_Model.getSelectionModel().getSelectedItem()+"'";

        connect = database.connectdb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                price = result.getDouble("price");
            }
            // price * the count day you want to use the car
            totalP = (price * countDate);
            // DISPLAY TOTAL
            rent_lbTotal.setText("₱" + String.valueOf(totalP));

        }catch(Exception e){e.printStackTrace();}

    }
    private String[] genderList = {"Male", "Female", "Others"};



    public void rentCarGender() {

        List<String> listG = new ArrayList<>();

        for (String data : genderList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);

        rent_Gender.setItems(listData);

    }

    public void rentCarCarId() {

        String sql = "SELECT * FROM car WHERE status = 'Available'";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("car_id"));
            }

            rent_CarID.setItems(listData);

            rentCarBrand();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void rentCarBrand() {

        String sql = "SELECT * FROM car WHERE car_id = '"
                + rent_CarID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("brand"));
            }

            rent_Brand.setItems(listData);

            rentCarModel();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void rentCarModel() {

        String sql = "SELECT * FROM car WHERE brand = '"
                + rent_Brand.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("model"));
            }

            rent_Model.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ObservableList<carData> rentCarList;

    public void rentCarShowListData() {
        rentCarList = rentCarListData();
        rent_tbCarID.setCellValueFactory(new PropertyValueFactory<>("carId"));
        rent_tbBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        rent_tbModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        rent_tbPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        rent_tbStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        rent_tableview.setItems(rentCarList);

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

        rentCarShowListData();
        rentCarCarId();
        rentCarBrand();
        rentCarModel();
        rentCarGender();
        rentDisplayTotal();
    }
}


