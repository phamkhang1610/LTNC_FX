package com.example.ltnc_fx;

import Data.Data;
import Model.Medicine;
import Model.getData;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpMeController implements Initializable {

    @FXML
    private Button add_btn_me;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<?> category;

    @FXML
    private Button colse;

    @FXML
    private TextField idMe;

    @FXML
    private ImageView image_Me;

    @FXML
    private Button ipm_btl_me;

    @FXML
    private TextField location;

    @FXML
    private TextField nameMe;

    @FXML
    private TextField note;

    @FXML
    private Button up_btl_me;
    @FXML
    private AnchorPane add_up_form;
    @FXML
    private TextField expiry;

    @FXML
    private TextField id_up_supme;
    @FXML
    private TextField quantity;
    @FXML
    private AnchorPane up_select;

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
    private Image image;
    private String[] categorylist ={"Thực phẩm chức năng","Thuốc bổ", "Thuốc chữa bệnh"};
    public void add_list_category(){
        List<String> li = new ArrayList<>();
        for (String a: categorylist){
            li.add(a);
        }
        ObservableList list = FXCollections.observableArrayList(li);
        category.setItems(list);
    }
    public void close(){
        try{
            reset();
            Stage currentStage = (Stage) colse.getScene().getWindow();
            currentStage.close();
            //thông báo đăng nhập thành công và trờ về trang chủ
            Parent root = FXMLLoader.load(getClass().getResource("Databroad.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            getData.ma = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void importStaffImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Thêm ảnh");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file","*jpg","*png"));

        File file = open.showOpenDialog(add_up_form.getScene().getWindow());
        if(file != null) {
            image = new Image(file.toURI().toString(),151,166,false,true);
            image_Me.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
    public void reset(){
        idMe.setText("");nameMe.setText("");
        location.setText("");price.setText("");
        note.setText("");image_Me.setImage(null);
        up_select.setVisible(false);
    }
    public void add_Medicine(){
        Data data = new Data();
        Medicine me = new Medicine();
        if(idMe.getText().isEmpty()|| nameMe.getText().isEmpty()||location.getText().isEmpty()|| getData.path == null|| getData.path==""){
            erro("Chưa điền đầy đủ thông tin.");
        }else {

            try{
                String checkData = "select nameMedi from medicine where nameMedi = '"
                        +nameMe.getText()+"'";
                ResultSet rs = data.ExcuteQueryGetTable(checkData);
                if(rs.next()){
                    erro("Thuốc "+ nameMe.getText()+" đã tồn tại");
                }else {
                    // thêm thuốc mới
                    me.setIdMe(idMe.getText());
                    me.setNameMe(nameMe.getText());
                    me.setLocation(location.getText());
                    me.setNote(note.getText());
                    me.setPrice(Integer.parseInt(price.getText()));
                    me.setIdType((String) category.getSelectionModel().getSelectedItem());
                    String uri = getData.path;
                    uri = uri.replace("\\","\\\\");
                    me.setImage(uri);
                    String sql = "INSERT INTO medicine (idMe, id_group, nameMedi, describeM, price, location, image) VALUES ('"+me.getIdMe()+"','"+me.getIdType()+"','"+me.getNameMe()
                            +"','"+me.getNote()+"','"+me.getPrice()+"','"+me.getLocation()+"','"+me.getImage()+"')";
                    data.ExcuteQueryUpdateDB(sql);

                    String khoi = "INSERT INTO `nhathuocdb`.`detail_me` (`idMe`, `id_lo`, `quantity`, `idSup`, `expiry`) VALUES ('"+me.getIdMe()+"', '0', '0', '0', '0000-01-1');";
                    data.ExcuteQueryUpdateDB(khoi);
                    noti("Thêm thuốc mới thành công");
                    reset();
                    close();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void showData(){
        if(getData.ma == null){
            ///
        }else{
            String[] mang = getData.ma.split(" ");
            up_select.setVisible(true);
            Data data = new Data();
            try{
                String sql1 = "select * from medicine where idMe ='"+mang[0]+"';";
                String sql2 = "select * from detail_me where idMe ='"+mang[0]+"'and id_lo ='"+mang[1]+"';";
                ResultSet rs1 = data.ExcuteQueryGetTable(sql1);
                ResultSet rs2 = data.ExcuteQueryGetTable(sql2);
                while (rs1.next() && rs2.next()){
                    idMe.setText(rs1.getString("idMe"));
                    nameMe.setText(rs1.getString("nameMedi"));
                    location.setText(rs1.getString("location"));
                    price.setText(rs1.getString("price"));
                    note.setText(rs1.getString("describeM"));
                    quantity.setText(rs2.getString("quantity"));
                    expiry.setText(rs2.getString("expiry"));
                    id_up_supme.setText(rs2.getString("idSup"));
                    image = new Image(rs1.getString("image").toString(),167,190,false,true);
                    image_Me.setImage(image);
                    getData.path = rs1.getString("image");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void upMe(){
        Data data = new Data();
        Medicine me = new Medicine();
        if(idMe.getText().isEmpty()|| nameMe.getText().isEmpty()||location.getText().isEmpty()|| getData.path == null|| getData.path==""){
            erro("Chưa điền đầy đủ thông tin.");
        }else {
            try{
                // thêm thuốc mới
                me.setIdMe(idMe.getText());
                me.setNameMe(nameMe.getText());
                me.setLocation(location.getText());
                me.setNote(note.getText());
                me.setPrice(Integer.parseInt(price.getText()));
                me.setIdType((String) category.getSelectionModel().getSelectedItem());
                me.setQuantity(Integer.parseInt(quantity.getText()));
                me.setExpiry(Date.valueOf(expiry.getText()));
                me.setIdSup(id_up_supme.getText());
                String[] mang = getData.ma.split(" ");
                me.setMalo(mang[1]);
                String uri = getData.path;
                uri = uri.replace("\\","\\\\");
                me.setImage(uri);
                String sql1 = "UPDATE medicine SET id_group = '" + me.getIdType() + "', nameMedi = '"
                        + me.getNameMe() + "', describeM = '" + me.getNote() + "', price = "
                        + me.getPrice() + ", location = '" + me.getLocation() + "', image = '"
                        + me.getImage() + "' WHERE idMe = '" + me.getIdMe() + "'";
                data.ExcuteQueryUpdateDB(sql1);
                String sql2 = "update detail_me set quantity = '"+me.getQuantity()+"', idSup = '"
                        +me.getIdSup()+"', expiry = '"+me.getExpiry()+"' where idMe = '"+me.getIdMe()
                        +"' and id_lo = '"+me.getMalo()+"';";
                data.ExcuteQueryUpdateDB(sql2);

                noti("Sửa thành công");
                reset();
                close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
        up_select.setVisible(false);
        showData();
        add_list_category();
    }
    
}
