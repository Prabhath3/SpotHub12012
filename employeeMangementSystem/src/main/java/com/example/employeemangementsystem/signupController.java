package com.example.employeemangementsystem;

import com.example.employeemangementsystem.databaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class signupController implements Initializable {

    @FXML
    private TextField getAddress;

    @FXML
    private TextField getName;

    @FXML
    private TextField getNic;

    @FXML
    private TextField getdob;
    @FXML
    private Button back;

    @FXML
    private Button createBtn;

    @FXML
    private ComboBox<String> listPos;

    ObservableList<String> pos = FXCollections.observableArrayList("Manager", "Supervisor","Employee","CEO");
    @FXML
    void creating(ActionEvent event) throws SQLException {
        String nic = getNic.getText();
//        long NIC = Integer.parseInt(nic);
        String name = getName.getText();
        String dob = getdob.getText();
        String position = listPos.getSelectionModel().getSelectedItem();
        String address = getAddress.getText();
        String sql = "Insert into employeedetails(nic_no,fullName, DOB, position, address) values (?,?,?,?,?)";
        try(Connection con = databaseConnector.getConnection();
            PreparedStatement statement = con.prepareStatement(sql)
        ){
            statement.setString(1, nic);
            statement.setString(2, name);
            statement.setString(3, dob);
            statement.setString(4, position);
            statement.setString(5, address);

            int row = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomeInterface.class.getResource("passwordCreation.fxml"));
            Parent root = fxmlLoader.load();

            passwordController passwordController = (passwordController) fxmlLoader.getController();
            passwordController.init(name);
            Stage stage = (Stage) createBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void listingPosition(ActionEvent event) {

    }

    @FXML
    void returnTo(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomeInterface.class.getResource("firstInterface.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listPos.setItems(pos);
    }
}

