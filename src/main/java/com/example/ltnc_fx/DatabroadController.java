package com.example.ltnc_fx;

import Data.Data;
import Model.Account;
import Model.Staff;
import Model.getData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



public class DatabroadController implements Initializable {
    @FXML
    private Button groupMedicine;

    @FXML
    private Button add_btn_bill;

    @FXML
    private Button add_btn_staff;

    @FXML
    private TextField addre_staff;

    @FXML
    private Button bill;
    @FXML
    private Button imp_btn_staff;

    @FXML
    private AnchorPane bill_form;

    @FXML
    private TextField cmnd;

    @FXML
    private TableColumn<?, ?> col_medicineID_bill;

    @FXML
    private TableColumn<?, ?> col_medicine_bill;

    @FXML
    private TableColumn<?, ?> col_price_bill;

    @FXML
    private TableColumn<Staff, String> col_search_addre;

    @FXML
    private TableColumn<Staff, String> col_search_cmnd;

    @FXML
    private TableColumn<Staff, String> col_search_date;

    @FXML
    private TableColumn<Staff, String> col_search_idstaff;

    @FXML
    private TableColumn<Staff, String> col_search_namestaff;

    @FXML
    private TableColumn<Staff, String> col_search_sdt;

    @FXML
    private TableColumn<Staff, String> col_search_sex;

    @FXML
    private TableColumn<?, ?> col_size_bill;

    @FXML
    private TableColumn<?, ?> col_type_bill;

    @FXML
    private Button colse;

    @FXML
    private Button dash;

    @FXML
    private AreaChart<?, ?> dash_chart;

    @FXML
    private AnchorPane dash_form;

    @FXML
    private DatePicker date_staff;

    @FXML
    private Button de_btn_staff;

    @FXML
    private TextArea ghi_bill;

    @FXML
    private TextField id_medicine_bill;

    @FXML
    private TextField id_staff;

    @FXML
    private TextField id_staff_bill;

    @FXML
    private Button medicine;

    @FXML
    private Button mini;

    @FXML
    private TextField name_medicine_bill;

    @FXML
    private TextField name_staff;

    @FXML
    private Button pay_btn_bill;

    @FXML
    private Button re_btn_staff;

    @FXML
    private TextField sdt_staff;

    @FXML
    private ComboBox<?> sex_staff;

    @FXML
    private ComboBox<?> size_medicine_bill;

    @FXML
    private Button staff;

    @FXML
    private AnchorPane staff_form;

    @FXML
    private Button supplier;

    @FXML
    private TableView<?> tbl_bill;

    @FXML
    private TableView<Staff> tbl_search_staff;
    @FXML
    private TableView<Staff> tbl_list_staff;
    @FXML
    private TableColumn<Staff, String> col_list_addre;

    @FXML
    private TableColumn<Staff, String> col_list_cmnd;

    @FXML
    private TableColumn<Staff, String> col_list_date;

    @FXML
    private TableColumn<Staff, String> col_list_idstaff;

    @FXML
    private TableColumn<Staff, String> col_list_namestaff;

    @FXML
    private TableColumn<Staff, String> col_list_sdt;

    @FXML
    private TableColumn<Staff, String> col_list_sex;

    @FXML
    private Label total_bill;

    @FXML
    private Button up_btn_staff;
    @FXML
    private Button sign_out;
    @FXML
    private Label user_label;

    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane medicine_form;
    @FXML
    private AnchorPane supplier_form;
    @FXML
    private ImageView image_staff;
    @FXML
    private Button btn_search_staff;
    @FXML
    private TextField search_staff;
    // bắt đầu điều khiển
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
    public void displayUser(){
        user_label.setText(Account.username.toUpperCase());
    }
    //chọn tab
    public void switchForm(ActionEvent event) {
        if (event.getSource() == dash) {
            dash_form.setVisible(true);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            dash.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
            staff.setStyle("-fx-background-color: transparent");
            medicine.setStyle("-fx-background-color: transparent");
            bill.setStyle("-fx-background-color: transparent");
            supplier.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == staff) {
            dash_form.setVisible(false);
            staff_form.setVisible(true);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            staff.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
            dash.setStyle("-fx-background-color: transparent");
            medicine.setStyle("-fx-background-color: transparent");
            bill.setStyle("-fx-background-color: transparent");
            supplier.setStyle("-fx-background-color: transparent");
            showStaff();
            add_list_sex();
        } else if (event.getSource() == medicine) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(true);
            supplier_form.setVisible(false);
            medicine.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
            dash.setStyle("-fx-background-color: transparent");
            bill.setStyle("-fx-background-color: transparent");
            staff.setStyle("-fx-background-color: transparent");
            supplier.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == bill) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(true);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            bill.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
            dash.setStyle("-fx-background-color: transparent");
            medicine.setStyle("-fx-background-color: transparent");
            staff.setStyle("-fx-background-color: transparent");
            supplier.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == supplier) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(true);
            supplier.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
            dash.setStyle("-fx-background-color: transparent");
            medicine.setStyle("-fx-background-color: transparent");
            bill.setStyle("-fx-background-color: transparent");
            staff.setStyle("-fx-background-color: transparent");
        }
    }

    // nút thoạt đăng nhập
    public void logout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thông tin");
        alert.setHeaderText(null);
        alert.setContentText("bạn chắc chắn muốn thoát đăng nhập chứ?");
        Optional<ButtonType> op = alert.showAndWait();
        if(op.get().equals(ButtonType.OK)){
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            //ẩn trang cũ
            sign_out.getScene().getWindow().hide();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void mine(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void close(){
        System.exit(0);
    }

    //=======================================DATA Staff=======================
    //ha lấy ra một list staff trong sql
    private String[] sexlist ={"Nam","Nữ"};
    private Image image;
    public void add_list_sex(){
        List<String> li = new ArrayList<>();
        for (String a: sexlist){
            li.add(a);
        }
        ObservableList list = FXCollections.observableArrayList(li);
        sex_staff.setItems(list);
    }
    public ObservableList<Staff> listStaff() {
        String sql = "select * from staff";
        Staff staff;
        Data data = new Data();
        ObservableList<Staff> list = FXCollections.observableArrayList();
        try{
            ResultSet rs = data.ExcuteQueryGetTable(sql);
            while(rs.next()){
               staff = new Staff(
                       rs.getString("idStaff"), rs.getString("nameStaff"),
                       rs.getString("cmnd"), rs.getString("sexStaff"),
                       rs.getString("addresStaff"), rs.getDate("date"),
                       rs.getString("phoneStaff"),rs.getString("image")
               );
               list.add(staff);
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
    }
    // hiển thị list staff lên bảng
    private ObservableList<Staff> list_Satff;
    public void showStaff() {
        try{
            list_Satff = listStaff();
            col_list_idstaff.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
            col_list_namestaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
            col_list_sex.setCellValueFactory(new PropertyValueFactory<>("sexStaff"));
            col_list_date.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
            col_list_cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
            col_list_addre.setCellValueFactory(new PropertyValueFactory<>("addresStaff"));
            col_list_sdt.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));

            tbl_list_staff.setItems(list_Satff);
        }catch (Exception e){ e.printStackTrace();}
    }
    public void addStaff(){
        Staff a = new Staff();
        Data data = new Data();
        if(id_staff.getText().isEmpty() || name_staff.getText().isEmpty()
        || cmnd.getText().isEmpty() || addre_staff.getText().isEmpty()
        || sdt_staff.getText().isEmpty()|| getData.path == null|| getData.path==""){
            erro("Chưa điền đầy đủ thông tin");
        }else {
            try{
                String checkData = "select idStaff from staff where idStaff = '"
                        +id_staff.getText()+"'";
                ResultSet rs = data.ExcuteQueryGetTable(checkData);
                if(rs.next()){
                    erro("ID: "+ id_staff.getText()+" đã tồn tại");
                }else {
                    a.setIdStaff(id_staff.getText());
                    a.setNameStaff(name_staff.getText());
                    a.setCmnd(cmnd.getText());
                    a.setAddresStaff(addre_staff.getText());
                    a.setPhoneStaff(sdt_staff.getText());
                    LocalDate loc = date_staff.getValue();
                    Date sqlDate = java.sql.Date.valueOf(loc);
                    a.setNgaysinh(sqlDate);
                    a.setSexStaff((String) sex_staff.getSelectionModel().getSelectedItem());
                    String uri = getData.path;
                    uri = uri.replace("\\","\\\\");
                    a.setImage(uri);
                    String sql ="insert into staff values('"+ a.getIdStaff()+"','"+a.getNameStaff()+"','"+
                            a.getSexStaff()+"','"+a.getCmnd()+"','"+a.getNgaysinh()+"','"
                            +a.getAddresStaff()+"','"+a.getPhoneStaff()+"','"+a.getImage()+"');";
                    data.ExcuteQueryUpdateDB(sql);
                    showStaff();
                    noti("Thêm thành công");
                    id_staff.setText("");name_staff.setText("");date_staff.setValue(null);
                    cmnd.setText("");addre_staff.setText("");sdt_staff.setText("");
                    image_staff.setImage(null);
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
    //search Staff
    public void searchStaff(){
        Staff a = new Staff();
        Data da = new Data();
        String sql = "select * from staff where idStaff = '" + search_staff.getText()+"'";
        ObservableList<Staff> list = FXCollections.observableArrayList();
        try{
            ResultSet rs = da.ExcuteQueryGetTable(sql);
            while(rs.next()){
                a = new Staff(
                        rs.getString("idStaff"), rs.getString("nameStaff"),
                        rs.getString("cmnd"), rs.getString("sexStaff"),
                        rs.getString("addresStaff"), rs.getDate("date"),
                        rs.getString("phoneStaff"),rs.getString("image")
                );
                list.add(a);
            }
            col_search_idstaff.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
            col_search_namestaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
            col_search_sex.setCellValueFactory(new PropertyValueFactory<>("sexStaff"));
            col_search_date.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
            col_search_cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
            col_search_addre.setCellValueFactory(new PropertyValueFactory<>("addresStaff"));
            col_search_sdt.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));

            tbl_search_staff.setItems(list);
            search_staff.setText("");
        }catch (Exception e){e.printStackTrace();}
    }
    // lấy ảnh add vào
    public void importStaffImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Thêm ảnh");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file","*jpg","*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());
        if(file != null) {
            image = new Image(file.toURI().toString(),151,166,false,true);
            image_staff.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
    public void addStaffSelect(){
        Staff staff = tbl_list_staff.getSelectionModel().getSelectedItem();
        int num = tbl_list_staff.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){return;}
        id_staff.setText(String.valueOf(staff.getIdStaff()));
        name_staff.setText(String.valueOf(staff.getNameStaff()));
        cmnd.setText(String.valueOf(staff.getCmnd()));
        addre_staff.setText(String.valueOf(staff.getAddresStaff()));
        sdt_staff.setText(String.valueOf(staff.getPhoneStaff()));
        String url= "file:"+staff.getImage();
        image = new Image(url,151,166,false,true);
        image_staff.setImage(image);
        getData.path = staff.getImage();
    }


    //=============================================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUser();
        showStaff();
        add_list_sex();
    }
}
