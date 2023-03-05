package com.example.carrentalsys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class adminController {

    @FXML
    private ImageView availableCars_imageView;

    @FXML
    private AnchorPane admin_Dashboard;

    @FXML
    private AnchorPane main_form;

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
    private TableView<carData> availableCars_tb;

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

    private String username;

    // DATABASE
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;

    public void availableCarAdd() {

        String sql = "INSERT INTO car (car_id, brand, model, price, status, image, date) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectdb();

        try {
            Alert alert;

            if (availableCars_tbCarID.getText().isEmpty()
                    || availableCars_tfCarID.getText().isEmpty()
                    || availableCars_tfModel.getText().isEmpty()
                    || availableCars_cbStatus.getSelectionModel().getSelectedItem() == null
                    || availableCars_tfPrice.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableCars_tfCarID.getText());
                prepare.setString(2, availableCars_tfBrand.getText());
                prepare.setString(3, availableCars_tfModel.getText());
                prepare.setString(4, availableCars_tfPrice.getText());
                prepare.setString(5, (String) availableCars_cbStatus.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");

                prepare.setString(6, uri);

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(7, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                availableCarShowListData();
                availableCarClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void availableCarUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE car SET brand = '" + availableCars_tfBrand.getText() + "', model = '"
                + availableCars_tfModel.getText() + "', status ='"
                + availableCars_cbStatus.getSelectionModel().getSelectedItem() + "', price = '"
                + availableCars_tfPrice.getText() + "', image = '" + uri
                + "' WHERE car_id = '" + availableCars_tfCarID.getText() + "'";

        connect = database.connectdb();

        try {
            Alert alert;

            if (availableCars_tfCarID.getText().isEmpty()
                    || availableCars_tfBrand.getText().isEmpty()
                    || availableCars_tfModel.getText().isEmpty()
                    || availableCars_cbStatus.getSelectionModel().getSelectedItem() == null
                    || availableCars_tfPrice.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Car ID: " + availableCars_tfCarID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void availableCarDelete() {

        String sql = "DELETE FROM car WHERE car_id = '" + availableCars_tfCarID.getText() + "'";

        connect = database.connectdb();

        try {
            Alert alert;
            if (availableCars_tfCarID.getText().isEmpty()
                    || availableCars_tfBrand.getText().isEmpty()
                    || availableCars_tfModel.getText().isEmpty()
                    || availableCars_cbStatus.getSelectionModel().getSelectedItem() == null
                    || availableCars_tfPrice.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Car ID: " + availableCars_tfCarID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCarClear() {
        availableCars_tfCarID.setText("");
        availableCars_tfBrand.setText("");
        availableCars_tfModel.setText("");
        availableCars_cbStatus.getSelectionModel().clearSelection();
        availableCars_tfPrice.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);

    }

    private String[] listStatus = {"Available", "Not Available"};

    public void availableCarStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCars_cbStatus.setItems(listData);
    }
    public void availableCarImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 179, 108, false, true);
            availableCars_imageView.setImage(image);

        }

    }

    public ObservableList<carData> availableCarListData() {

        ObservableList<carData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM car";

        connect = database.connectdb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            carData carD;

            while (result.next()) {
                carD = new carData(result.getInt("car_id"),
                        result.getString("brand"),
                        result.getString("model"),
                        result.getDouble("price"),
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

    private ObservableList<carData> availableCarList;

    public void availableCarShowListData() {
        availableCarList = availableCarListData();

        availableCars_tbCarID.setCellValueFactory(new PropertyValueFactory<>("carId"));
        availableCars_tbBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        availableCars_tbModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCars_tbPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableCars_tbStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        availableCars_tb.setItems(availableCarList);
    }


    public void availableCarSearch() {

        FilteredList<carData> filter = new FilteredList<>(availableCarList, e -> true);

        availableCars_tfSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateCarData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCarData.getCarId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCarData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCarData.getModel().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCarData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCarData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<carData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(availableCars_tb.comparatorProperty());
        availableCars_tb.setItems(sortList);

    }
    public void availableCarSelect() {
        carData carD = availableCars_tb.getSelectionModel().getSelectedItem();
        int num = availableCars_tb.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        availableCars_tfCarID.setText(String.valueOf(carD.getCarId()));
        availableCars_tfBrand.setText(carD.getBrand());
        availableCars_tfModel.setText(carD.getModel());
        availableCars_tfPrice.setText(String.valueOf(carD.getPrice()));

        getData.path = carD.getImage();

        String uri = "file:" + carD.getImage();

        image = new Image(uri, 179, 108, false, true);

        availableCars_imageView.setImage(image);


    }


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

            // To update the table view when clicked
            availableCarShowListData();
            availableCarStatusList();

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

        // Display the data onto the table view
        availableCarShowListData();
        availableCarStatusList();
    }
}
