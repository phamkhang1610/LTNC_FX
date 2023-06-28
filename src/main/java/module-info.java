module com.example.ltnc_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.ltnc_fx to javafx.fxml;
    exports com.example.ltnc_fx;
    opens Model to javafx.base;
}