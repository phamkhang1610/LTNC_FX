package com.example.ltnc_fx;

import Model.Staff;
import Model.getData;
import Data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class upStaff implements Initializable {
    @FXML
    private TextField add;

    @FXML
    private TextField cmnd;

    @FXML
    private DatePicker date;

    @FXML
    private TextField id;

    @FXML
    private ImageView image_upStaff;

    @FXML
    private Button imp;

    @FXML
    private TextField name;

    @FXML
    private TextField sdt;

    @FXML
    private ComboBox<?> sex;

    @FXML
    private Button up;
    @FXML
    private AnchorPane up_form;


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
        alert.setTitle("Thông báo lỗi");
        alert.setHeaderText(null);
        alert.setContentText(not);
        alert.showAndWait();
    }
    public void close(){
        System.exit(0);
    }
    private String[] sexlist ={"Nam","Nữ"};
    private Image image;
    public void add_up_sex(){
        List<String> li = new ArrayList<>();
        for (String a: sexlist){
            li.add(a);
        }
        ObservableList list = FXCollections.observableArrayList(li);
        sex.setItems(list);
    }

     public void showData(){
        String sql = "select * from staff where idStaff='"+ getData.user+"';";
        Data data =new Data();
        try{
            ResultSet rs = data.ExcuteQueryGetTable(sql);
            while(rs.next()){
                id.setText(rs.getString("idStaff"));
                        //rs.getString("phoneStaff"),rs.getString("image");
                name.setText(rs.getString("nameStaff"));
                String sexValue = rs.getString("sexStaff");
                // sửa lại đoạn này vì vẫn chưa tối ưu.
                List<String> li = new ArrayList<>();
                for (String a : sexlist) {
                    li.add(a);
                }
                ObservableList<String> list = FXCollections.observableArrayList(li);
                int selectedIndex = list.indexOf(sexValue);
                if (selectedIndex >= 0) {
                    sex.getSelectionModel().select(selectedIndex);
                }
                date.setValue(rs.getDate("date").toLocalDate());
                cmnd.setText(rs.getString("cmnd"));
                sdt.setText(rs.getString("phoneStaff"));
                add.setText(rs.getString("addresStaff"));
                image = new Image(rs.getString("image").toString(),151,166,false,true);
                image_upStaff.setImage(image);
                getData.path = rs.getString("image");
  //              String uri = getData.path;
 //               uri = uri.replace("\\","\\\\");
//                a.setImage(uri);

            }

        }catch (Exception e){ e.printStackTrace();}
     }
    public void upStaff(){
        Staff a = new Staff();
        Data data = new Data();
        if(id.getText().isEmpty() || name.getText().isEmpty()
                || cmnd.getText().isEmpty() || add.getText().isEmpty()
                || sdt.getText().isEmpty()|| getData.path == null|| getData.path==""){
            erro("Chưa điền đầy đủ thông tin");
        }else {
            try{
                    a.setIdStaff(id.getText());
                    a.setNameStaff(name.getText());
                    a.setCmnd(cmnd.getText());
                    a.setAddresStaff(add.getText());
                    a.setPhoneStaff(sdt.getText());
                    LocalDate loc = date.getValue();
                    Date sqlDate = java.sql.Date.valueOf(loc);
                    a.setNgaysinh(sqlDate);
                    a.setSexStaff((String) sex.getSelectionModel().getSelectedItem());
                    String uri = getData.path;
                    uri = uri.replace("\\","\\\\");
                    a.setImage(uri);
                    String sql = "update staff set " +
                        "nameStaff='" + a.getNameStaff() + "', " +
                        "sexStaff='" + a.getSexStaff() + "', " +
                        "cmnd='" + a.getCmnd() + "', " +
                        "date='" + a.getNgaysinh() + "', " +
                        "addresStaff='" + a.getAddresStaff() + "', " +
                        "phoneStaff='" + a.getPhoneStaff() + "', " +
                        "image='" + a.getImage() + "' " +
                        "where idStaff='" + a.getIdStaff() + "'";
                    data.ExcuteQueryUpdateDB(sql);
                    showData();
                    noti("Sửa thành công");
                    Parent root = FXMLLoader.load(getClass().getResource("databroad.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    //ẩn trang cũ
                    up.getScene().getWindow().hide();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
    public void impStaffImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Thêm ảnh");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file","*jpg","*png"));

        File file = open.showOpenDialog(up_form.getScene().getWindow());
        if(file != null) {
            image = new Image(file.toURI().toString(),161,200,false,true);
            image_upStaff.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_up_sex();
        showData();
    }
}
