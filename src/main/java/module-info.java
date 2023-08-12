module com.example.ltnc_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;


    opens com.example.ltnc_fx to javafx.fxml;
    exports com.example.ltnc_fx;
    opens Model to javafx.base ;
    exports test;
    opens test to javafx.fxml;
}