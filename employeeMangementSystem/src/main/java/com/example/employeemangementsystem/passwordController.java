package com.example.employeemangementsystem;

import com.example.employeemangementsystem.databaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class passwordController {
    private String Name;

    @FXML
    private Button backBtn;

    @FXML
    private Button lBtn;
    @FXML
    private Button pCreateBtn;

    @FXML
    private TextField userName;

    @FXML
    private TextField rePassword;

    @FXML
    private Button resetBtn;

    public void init(String name){
        this.Name = name;
    }


    @FXML
    void PasswordCreate(ActionEvent event) throws SQLException {
        String nic = userName.getText();
        String reNewPassword = rePassword.getText();
        String sql = "insert into passwordmanagement(user,password, fk_nic_no) values(?,?,?)";
        try(Connection con = databaseConnector.getConnection();
        PreparedStatement statement = con.prepareStatement(sql)){
            statement.setString(1, Name);
            statement.setString(2, reNewPassword);
            statement.setString(3, nic);
            int row =  statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        userName.clear();
        rePassword.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Your Account Successfully Created.");
        alert.showAndWait();
    }

    @FXML
    void clearingPassword(ActionEvent event) {
        userName.clear();
        rePassword.clear();
    }

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader fxmLoader = new FXMLLoader(WelcomeInterface.class.getResource("signup page.fxml"));
            Parent root1 = fxmLoader.load();
            Stage stage = (Stage) backBtn.getScene().getWindow();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goTologin(ActionEvent event) {
        try {
            FXMLLoader fxmLoader = new FXMLLoader(WelcomeInterface.class.getResource("hello-view.fxml"));
            Parent root1 = fxmLoader.load();
            Stage stage = (Stage) backBtn.getScene().getWindow();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
