package com.example.ltnc_fx;

import Model.getData;
import Services.SupplierService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    void cancelUpdate(ActionEvent event) {
        cancel.getScene().getWindow().hide();
    }

    @FXML
    void update(ActionEvent event) {
        try {

            SupplierService service = new SupplierService();
            String name = input_name.getText();
            String sdt = input_sdt.getText();
            String address = input_adress.getText();
            if(name.isEmpty()||sdt.isEmpty()||address.isEmpty()){
                erro("Vui lòng nhập đầy đủ thông tin");
            }
            else {
                boolean rs = service.upDate(getData.supplier.getIdSup(),name,sdt,address);
                if (rs == true) {

                    noti("Succes");
                } else {
                    erro("Faild!");
                }
            }
        }
        catch (Exception ex){

        }
        finally {
            cancel.getScene().getWindow().hide();
        }

    }

    //region common
    public void erro(String err){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo lỗi");
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }
    public void noti(String not){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(not);
        alert.showAndWait();
    }

    //endregion
}
