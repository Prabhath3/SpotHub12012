module com.example.employeemangementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;


    opens com.example.employeemangementsystem to javafx.fxml;
    exports com.example.employeemangementsystem;
}