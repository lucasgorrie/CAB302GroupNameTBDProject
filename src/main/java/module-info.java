module com.example.cab302groupnametbdproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.jsoup;


    opens com.example.cab302groupnametbdproject to javafx.fxml;
    exports com.example.cab302groupnametbdproject;
}