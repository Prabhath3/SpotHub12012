package com.example.employeemangementsystem;

import com.example.employeemangementsystem.databaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashController implements Initializable {
    private String name;
    private String nicInput;


    @FXML
    private Button deleteBtn1;


    @FXML
    private Label AddPro;

    @FXML
    private Label dobPro;

    @FXML
    private Button logout;

    @FXML
    private Label nicPro;

    @FXML
    private Label positionPro;

    @FXML
    private Label userNameShow;

public void init(String nicInput) {
        this.nicInput = nicInput;
        System.out.println("the databases logined" + this.nicInput);
        loaduserName();
    }

    private void loaduserName() {
        String sql = "Select * from employeedetails where nic_no = ?";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, nicInput);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    userNameShow.setText(rs.getString(2));
                    nicPro.setText(rs.getString("nic_no"));
                    dobPro.setText(rs.getString("DOB"));
                    positionPro.setText(rs.getString("position"));
                    AddPro.setText(rs.getString("Address"));

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void loutBtn(ActionEvent event) throws SQLException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomeInterface.class.getResource("firstInterface.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) logout.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void deleteRec(ActionEvent event) {
        String sql1 = "delete from employeedetails where nic_no = ?";
        String sql2 = "delete from passwordmanagement where fk_nic_no = ?";
        try(Connection con = databaseConnector.getConnection();
        ) {
            try(PreparedStatement st = con.prepareStatement(sql2)){
                st.setString(1, nicInput);
                st.executeUpdate();
            }
            try(PreparedStatement st = con.prepareStatement(sql1)){
                st.setString(1, nicInput);
                int rowCount = st.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(rowCount > 0){
                    alert.setTitle("Success");
                    alert.setHeaderText("Account Deleted");
                    alert.showAndWait();
                }else{
                    alert.setTitle("Failed");
                    alert.setHeaderText("Account Not Deleted");
                    alert.showAndWait();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}