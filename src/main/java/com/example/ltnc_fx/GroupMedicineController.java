package com.example.ltnc_fx;

import Data.Data;
import Model.Type;
import Model.getData;
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
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class GroupMedicineController implements Initializable {
    @FXML
    private Button add_type_btn;

    @FXML
    private Button close_group_medicine;

    @FXML
    private TextField description_type;

    @FXML
    private TextField id_type;

    @FXML
    private TextField name_type;

    @FXML
    private TextField position_type;

    @FXML
    private Button update_type_btn;

    public void err(String err) {
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo lỗi");
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }

    public void notice(String not) {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(not);
        alert.showAndWait();
    }

    public void close() {
        try {
            reset();
            Stage currentStage = (Stage) close_group_medicine.getScene().getWindow();
            currentStage.close();
            //thông báo đăng nhập thành công và trờ về trang chủ
//            Parent root = FXMLLoader.load(getClass().getResource("Databroad.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(root);
//            stage.initStyle(StageStyle.TRANSPARENT);
//            stage.setScene(scene);
//            stage.show();
            getData.ma = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        id_type.setText("");
        name_type.setText("");
        position_type.setText("");
        description_type.setText("");
    }

    public void addType() {
        Data data = new Data();
        Type grMedicine = new Type();
        if (id_type.getText().isEmpty() || name_type.getText().isEmpty() || position_type.getText().isEmpty() || description_type.getText().isEmpty()) {
            err("Chưa điền đầy đủ thông tin!");
        } else {
            try {
                String checkRepeat = "select id_group from groupmedi where id_group = '" + id_type.getText() + "' or name_group = '" + name_type.getText() + "'";
                ResultSet result = data.ExcuteQueryGetTable(checkRepeat);
                if (result.next()) {
                    err("Nhóm " + name_type.getText() + " đã tồn tại");
                } else {
                    grMedicine.setIdType(id_type.getText());
                    grMedicine.setNameType(name_type.getText());
                    grMedicine.setDescribe(description_type.getText());
                    grMedicine.setPosition(position_type.getText());
                    String sql = "INSERT INTO groupmedi (id_group, name_group, describeG, locationG) VALUES ('" + grMedicine.getIdType() + "','" + grMedicine.getNameType() + "', '" + grMedicine.getDescribe() + "', '" + grMedicine.getPosition() + "')";
                    data.ExcuteQueryUpdateDB(sql);
                    notice("Thêm nhóm thuốc thành công");
                    reset();
                    close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void showData() {
        if(getData.type == null){

        }else {
            update_type_btn.setVisible(true);
            add_type_btn.setVisible(false);
            id_type.setText(getData.type.getIdType());
            name_type.setText(getData.type.getNameType());
            description_type.setText(getData.type.getDescribe());
            position_type.setText(getData.type.getPosition());
        }
    }
    public void updateType(){
        Data data = new Data();
        Type type = new Type();
        if(id_type.getText().isEmpty() || name_type.getText().isEmpty() || description_type.getText().isEmpty() || position_type.getText().isEmpty()){
            err("Chưa điền đầy đủ thông tin");
        }else {
            try {
                type.setIdType(id_type.getText());
                type.setNameType(name_type.getText());
                type.setPosition(position_type.getText());
                type.setDescribe(description_type.getText());
                String sql = "UPDATE groupmedi SET id_group = '"+ type.getIdType() + "', name_group = '" + type.getNameType() + "', describeG = '" + type.getDescribe() + "', locationG = '" + type.getPosition() + "' WHERE id_group = '" + type.getIdType() + "'";
                data.ExcuteQueryUpdateDB(sql);
                notice("Sửa nhóm thuốc thành công");
                reset();
                close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_type_btn.setVisible(true);
        update_type_btn.setVisible(false);
        showData();
    }
}
