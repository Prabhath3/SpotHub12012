package com.example.employeemangementsystem;

import javafx.event.ActionEvent;
import com.example.employeemangementsystem.databaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginController {
    private String Name;

    @FXML
    private Button backBtn1;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField password1;

    @FXML
    private TextField userName;

    @FXML
    void login(ActionEvent event) throws SQLException {
        String nicInput = userName.getText();
        String passwordInput = password1.getText();
        System.out.println(this.Name);
        String  sql = "SELECT count(*) FROM passwordmanagement where fk_nic_no = ? AND password = ? and user = ?";

        Alert alert = new Alert(AlertType.INFORMATION);
        try(Connection conn = databaseConnector.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(3, nicInput);
            st.setString(2, passwordInput);
            st.setString(1,Name);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int userCount = rs.getInt(1);
                    alert.setTitle("employeeManagementSystem");
                    alert.setHeaderText("Access granted");
                    alert.showAndWait();
                    this.Name = rs.getString(1);
                    System.out.println(this.Name);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeInterface.class.getResource("dashBoard.fxml"));
                        Parent root = fxmlLoader.load();
                        dashController dashController = fxmlLoader.getController();
                        dashController.init(nicInput);
                        Stage stage = (Stage) loginBtn.getScene().getWindow();
                        Scene scene = new Scene(root);
                        String cssPath = getClass().getResource("stye.css").toExternalForm();
                        scene.getStylesheets().add(cssPath);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    alert.setTitle("employeeManagementSystem");
                    alert.setHeaderText("Access denied");
                    alert.showAndWait();
                }
            }
        }

    }
    @FXML
    void Back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomeInterface.class.getResource("firstInterface.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) backBtn1.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
