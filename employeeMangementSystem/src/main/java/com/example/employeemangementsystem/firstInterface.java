package com.example.employeemangementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class firstInterface {

    @FXML
    private Button logiBtn;

    @FXML
    private Button signupBtn;




    @FXML
    void login(ActionEvent event) {
        try {
            FXMLLoader fxmLoader = new FXMLLoader(WelcomeInterface.class.getResource("hello-view.fxml"));
            Parent root1 = fxmLoader.load();
            Stage stage = (Stage) logiBtn.getScene().getWindow();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void signup(ActionEvent event) {
        try{
            FXMLLoader fxmlLoder = new  FXMLLoader(WelcomeInterface.class.getResource("signup page.fxml"));
            Parent root2 = fxmlLoder.load();
            Stage stage = (Stage) signupBtn.getScene().getWindow();
            Scene scene = new Scene(root2);
            String cssPath = getClass().getResource("stye.css").toExternalForm();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
