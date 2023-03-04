package com.example.carrentalsys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginpageController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Label lbLogin;

    @FXML
    private AnchorPane leftside;

    @FXML
    private Button loginButton;

    @FXML
    private Label logoname;

    @FXML
    private Label logophrase;

    @FXML
    private BorderPane main_form;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane rightside;

    @FXML
    private TextField usernameField;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private RadioButton adminRadio;

    @FXML
    private RadioButton customerRadio;


    //database tools
    private Connection conn;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    // Getter and setter methods

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    // Getter and setter methods for prepare
    public PreparedStatement getPrepare() {
        return prepare;
    }

    public void setPrepare(PreparedStatement prepare) {
        this.prepare = prepare;
    }

    // Getter and setter methods for result
    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    // Getter and setter methods for x
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    // Getter and setter methods for y
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

//methods of the class

    public String getUserType() {
        if (adminRadio.isSelected()) {
            return "admin";
        } else if (customerRadio.isSelected()) {
            return "customer";
        } else {
            return null;
        }
    }

    public void loginCustomer() throws SQLException {
        String userType = getUserType();
        DatabaseHelper dbHelper = new DatabaseHelper();
        ResultSet userInfo = dbHelper.getUserInfo(usernameField.getText(), passwordField.getText(), userType);
        Alert alert;

        try {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || userType == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your username, password, and select user type");
                alert.showAndWait();
            } else {
                if (userInfo.next()) {

                    getData.username = usernameField.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome " + usernameField.getText());
                    alert.showAndWait();

                    loginButton.getScene().getWindow().hide();

                    // link to the appropriate dashboard based on user type
                    if (userType.equals("admin")) {
                        Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);

                        root.setOnMousePressed(MouseEvent -> {
                            x = MouseEvent.getSceneX();
                            y = MouseEvent.getSceneY();
                        });
                        root.setOnMouseDragged(MouseEvent -> {
                            stage.setX(MouseEvent.getScreenX() - x);
                            stage.setY(MouseEvent.getScreenY() - y);
                            stage.setOpacity(0.7);
                        });
                        root.setOnMouseReleased(MouseEvent -> {
                            stage.setOpacity(1.0);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Parent root = FXMLLoader.load(getClass().getResource("customerDashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);

                        root.setOnMousePressed(MouseEvent -> {
                            x = MouseEvent.getSceneX();
                            y = MouseEvent.getSceneY();
                        });
                        root.setOnMouseDragged(MouseEvent -> {
                            stage.setX(MouseEvent.getScreenX() - x);
                            stage.setY(MouseEvent.getScreenY() - y);
                            stage.setOpacity(0.7);
                        });
                        root.setOnMouseReleased(MouseEvent -> {
                            stage.setOpacity(1.0);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password");
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
        }
    }


    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);

    }

    public void initialize() {
        toggleButton.setOnAction(e -> {
            if (toggleButton.isSelected()) {
                passwordField.setPromptText(passwordField.getText());
                passwordField.setText("");
                passwordField.setDisable(true);
            } else {
                passwordField.setText(passwordField.getPromptText());
                passwordField.setPromptText("");
                passwordField.setDisable(false);
            }
        });
    }

    @FXML
    void handleLoginButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
