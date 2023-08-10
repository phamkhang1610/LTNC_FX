package com.example.ltnc_fx;

import Model.getData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpSupplierController implements Initializable {
    @FXML
    private Button cancel;

    @FXML
    private TextField input_adress;

    @FXML
    private TextField input_name;

    @FXML
    private TextField input_sdt;

    @FXML
    private Button save;

    public void ShowData() {
        input_name.setText(getData.supplier.getNameSup());
        input_sdt.setText(getData.supplier.getSdtSup());
        input_adress.setText(getData.supplier.getAddresSup());
    }

@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
                    ShowData();
        }



//    @FXML
//    void cancelUpdate(ActionEvent event) {
//
//    }
//
//    @FXML
//    void update(ActionEvent event) {
//
//    }
}
