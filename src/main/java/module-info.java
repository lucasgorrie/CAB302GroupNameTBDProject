module com.example.cab302groupnametbdproject {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.commons.codec;


    exports com.example.cab302groupnametbdproject.controllers;
    opens com.example.cab302groupnametbdproject.controllers to javafx.fxml;
    exports com.example.cab302groupnametbdproject.model;
    opens com.example.cab302groupnametbdproject.model to javafx.fxml;
    exports com.example.cab302groupnametbdproject;
    opens com.example.cab302groupnametbdproject to javafx.fxml;

}
