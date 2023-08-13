package com.example.ltnc_fx;

import Data.Data;
import Model.*;
import Services.BillInService;
import Services.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;
import java.time.ZoneId;
import java.util.*;

//import static jdk.jpackage.internal.WixAppImageFragmentBuilder.Id.Icon
public class DatabroadController implements Initializable {
    @FXML
    private Button add_bill;
    //region setup
    @FXML
    private Button groupMedicine;
    @FXML
    private AnchorPane group_medicine_form;
    @FXML
    private Button add_group_medicine;
    @FXML
    private Button add_btn_bill;
    @FXML
    private Button add_btn_staff;
    @FXML
    private Label no_result;
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
    private Label noty_search;
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
    private TableColumn<Staff, Void> col_search_dele;
    @FXML
    private TableColumn<Staff,Void> col_search_up;

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
    private  TextField search_type;
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
    private TableColumn<Staff, Void> col_list_delete;
    @FXML
    private TableColumn<Staff, Void> col_list_up;
    @FXML
    private Label label_search;
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
    private AnchorPane type_table;
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
    //======================fxml medicine=============
    @FXML
    private TextField search_Me;
    @FXML
    private Button add_btl_Me;
    @FXML
    private Button btn_search_me;
    @FXML
    private TableView<Medicine> tbl_medicine;
    @FXML
    private TableColumn<Medicine, Void> col_deleMe;
    @FXML
    private TableColumn<Medicine, String> col_expiry;
    @FXML
    private TableColumn<Medicine, String> col_idMe;
    @FXML
    private TableColumn<Medicine, String> col_idSup_me;
    @FXML
    private TableColumn<Medicine, String> col_location;
    @FXML
    private TableColumn<Medicine,String> col_nameMe;
    @FXML
    private TableColumn<Medicine, String> col_price;
    @FXML
    private TableColumn<Medicine,String> col_quantity;
    @FXML
    private TableColumn<Medicine, Void> col_upMe;
    @FXML
    private Button reload_me;
    @FXML
    private TableView<Type> table_view_type;
    @FXML
    private TableColumn<Type, Void> delete_type_column;
    @FXML
    private TableColumn<Type, String> describe_type_column;
    @FXML
    private TableColumn<Type, Void> edit_type_column;
    @FXML
    private TableColumn<Type, String> position_type_column;
    @FXML
    private TableColumn<Type, String> name_type_column;
    @FXML
    private TableColumn<Type, String> id_type_column;
    @FXML
    private TableColumn<?, ?> col_malo;
    // endregion

    //region Nha cung cap
    @FXML
    private Tab ncc_1;
    @FXML
    private TableView<Supplier> ncc_1_tbl;
    @FXML
    private TableView<Supplier> ncc_2_tbl;

    @FXML
    private TableColumn<Supplier, String> ncc_1_tbl_adress;


    @FXML
    private TableColumn<Supplier, Void> ncc_1_tbl_deletea;

    @FXML
    private TableColumn<Supplier, Void> ncc_1_tbl_edita;

    @FXML
    private TableColumn<Supplier, String> ncc_1_tbl_id;

    @FXML
    private TableColumn<Supplier, String> ncc_1_tbl_name;

    @FXML
    private TableColumn<Supplier, String> ncc_1_tbl_sdt;

    @FXML
    private Tab ncc_2;

    @FXML
    private TextField ncc_2_adress;

    @FXML
    private TextField ncc_2_id;

    @FXML
    private TextField ncc_2_name;

    @FXML
    private TextField ncc_2_numberphone;

    @FXML
    private Button ncc_2_reset;

    @FXML
    private TextField ncc_2_search;

    @FXML
    private FontAwesomeIcon ncc_2_searchaction;

    @FXML
    private TableColumn<Supplier, String> ncc_2_tbl_adress;

    @FXML
    private TableColumn<Supplier, String> ncc_2_tbl_id;

    @FXML
    private TableColumn<Supplier, String>ncc_2_tbl_name;

    @FXML
    private TableColumn<Supplier, String> ncc_2_tbl_sdt;

    @FXML
    private Button ncc_2_them;
    // endregion
    //================================== hóa đơn===============
    //region hoa đơn
    @FXML
    private TableColumn<Bill,Void> col_bill_chitiet;
    @FXML
    private TableColumn<Bill,String> col_bill_date;
    @FXML
    private TableColumn<Bill,String> col_bill_id;
    @FXML
    private TableColumn<Bill,String> col_bill_idMe;
    @FXML
    private TableColumn<Bill,String> col_bill_money;
    @FXML
    private TableColumn<Bill,String>col_bill_note;
    @FXML
    private TableView<Bill> tbl_bill_search;
    @FXML
    private Button search_bill;
    @FXML
    private TextField search_bill_idMe;
    @FXML
    private TableColumn<Detail_bill,String> col_idme;
    @FXML
    private TableColumn<Detail_bill,String> col_money;
    @FXML
    private TableColumn<Detail_bill,String> col_nmaeme;
    @FXML
    private TableColumn<Detail_bill,String> col_sl;
    @FXML
    private TableView<Detail_bill> tbl_bill;
    @FXML
    private AnchorPane chitiet_bill;
    @FXML
    private Label chitiet_bill_date;
    @FXML
    private Label chitiet_bill_idbill;
    @FXML
    private Label chitiet_bill_idstaff;
    @FXML
    private Label chitiet_bill_note;
    @FXML
    private Label chitiet_bill_total;
    @FXML
    private Label noti_search_bill;
    @FXML
    private DatePicker search_bill_date;
    //endregion

    //region hoa don xuat
    @FXML
    private DatePicker bill_search_date;

    @FXML
    private TextField bill_search_mnv;

    @FXML
    private TextField bill_search_ncc;

    @FXML
    private TableView<BillIn> bill_tbl;

    @FXML
    private TableColumn<BillIn,String> bill_tbl_mabill;

    @FXML
    private TableColumn<BillIn,String> bill_tbl_mancc;

    @FXML
    private TableColumn<BillIn,String> bill_tbl_manv;

    @FXML
    private TableColumn<BillIn,String> bill_tbl_ngaytao;

    @FXML
    private TableColumn<BillIn,String> bill_tbl_tongso;
    @FXML
    private TableColumn<BillIn,Void> bill_tbl_view;

    @FXML
    private Button billout_nhap;
    //endregion
    // bắt đầu điều khiển
    //region phamkhang
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
//    public void displayUser(){
//        user_label.setText(Account.username.toUpperCase());
//    }
    //chọn tab
    public void switchForm(ActionEvent event) {
        if (event.getSource() == dash) {
            dash_form.setVisible(true);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            group_medicine_form.setVisible(false);

        } else if (event.getSource() == staff) {
            dash_form.setVisible(false);
            staff_form.setVisible(true);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            group_medicine_form.setVisible(false);
            showStaff();
            add_list_sex();
            label_search.setVisible(false);
            tbl_search_staff.setVisible(false);
        } else if (event.getSource() == medicine) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(true);
            supplier_form.setVisible(false);
            group_medicine_form.setVisible(false);
            tbl_medicine.setVisible(false);
            noty_search.setVisible(false);
        } else if (event.getSource() == bill) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(true);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            group_medicine_form.setVisible(false);

        } else if (event.getSource() == supplier) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(true);
            group_medicine_form.setVisible(false);
//            supplier.setStyle(" -fx-background-color: linear-gradient(to bottom right, #e86de8, #c936c9,#8a1c8a,#970897);");
//            dash.setStyle("-fx-background-color: transparent");
//            medicine.setStyle("-fx-background-color: transparent");
//            bill.setStyle("-fx-background-color: transparent");
//            staff.setStyle("-fx-background-color: transparent");
            InserSupplierToTable1();
        }else if (event.getSource() == groupMedicine) {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
            group_medicine_form.setVisible(true);
            initalTableValue();           
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
    //public static Staff up;
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
            // Create a new TableColumn for the "Delete" icon
            //TableColumn<Staff, Void> col_list_delete = new TableColumn<>("");
            // Set the cell factory to display the "Trash" icon
            col_list_delete.setCellFactory(column -> {
                TableCell<Staff, Void> cell = new TableCell<>() {
                    FontAwesomeIcon deleIcon = new FontAwesomeIcon();
                    {
                        // Styling the icon if needed
                        deleIcon.getStyleClass().add("delete-icon");
                        deleIcon.setIconName("TRASH");
                        deleIcon.setSize("20px");
                        deleIcon.setCursor(Cursor.HAND);
                        deleIcon.setFill(Color.RED);
                        deleIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Xác nhận thông tin");
                            alert.setHeaderText(null);
                            alert.setContentText("bạn chắc chắn muốn xóa chứ?");
                            Optional<ButtonType> op = alert.showAndWait();
                            if(op.get().equals(ButtonType.OK)){
                                Staff staff = tbl_list_staff.getSelectionModel().getSelectedItem();
                                // Thực hiện hoạt động khi người dùng nhấp vào biểu tượng xóa
                                String sql = "delete from staff where idStaff ='"+staff.getIdStaff()+"';";
                                Data data = new Data();
                                data.ExcuteQueryUpdateDB(sql);
                                noti("Xóa thành công");
                                showStaff();
                            }

                        });
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleIcon);
                        }
                    }
                };
                return cell;
            });
            // icon update
            //TableColumn<Staff, Void> col_ list_up = new TableColumn<>("");
            //  Set the cell factory to display the "Trash" icon
            col_list_up.setCellFactory(column -> {
                TableCell<Staff, Void> cellup = new TableCell<>() {
                    FontAwesomeIcon upIcon = new FontAwesomeIcon();
                    {
                        // Styling the icon if needed
                        upIcon.getStyleClass().add("up-icon");
                        upIcon.setIconName("PENCIL");
                        upIcon.setSize("20px");
                        upIcon.setCursor(Cursor.HAND);
                        upIcon.setFill(Color.AQUA);
                        upIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                            Staff staff = tbl_list_staff.getSelectionModel().getSelectedItem();
                            getData.user = staff.getIdStaff();
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("up_staff.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                //ẩn trang cũ
                                upIcon.getScene().getWindow().hide();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setScene(scene);
                                stage.show();
                                showStaff();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(upIcon);
                        }
                    }
                };
                return cellup;
            });
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
            if (!list.isEmpty()) {
                col_search_idstaff.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
                col_search_namestaff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
                col_search_sex.setCellValueFactory(new PropertyValueFactory<>("sexStaff"));
                col_search_date.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
                col_search_cmnd.setCellValueFactory(new PropertyValueFactory<>("cmnd"));
                col_search_addre.setCellValueFactory(new PropertyValueFactory<>("addresStaff"));
                col_search_sdt.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));
                col_search_dele.setCellFactory(column -> {
                    TableCell<Staff, Void> cellSearch = new TableCell<>() {
                        FontAwesomeIcon deleIcon_search = new FontAwesomeIcon();
                        {
                            // Styling the icon if needed
                            deleIcon_search.getStyleClass().add("delete-icon");
                            deleIcon_search.setIconName("TRASH");
                            deleIcon_search.setSize("20px");
                            deleIcon_search.setCursor(Cursor.HAND);
                            deleIcon_search.setFill(Color.RED);
                            deleIcon_search.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Xác nhận thông tin");
                                alert.setHeaderText(null);
                                alert.setContentText("bạn chắc chắn muốn xóa chứ?");
                                Optional<ButtonType> op = alert.showAndWait();
                                if(op.get().equals(ButtonType.OK)){
                                    Staff staff = tbl_search_staff.getSelectionModel().getSelectedItem();
                                    // Thực hiện hoạt động khi người dùng nhấp vào biểu tượng xóa
                                    String sql = "delete from staff where idStaff ='"+staff.getIdStaff()+"';";
                                    Data data = new Data();
                                    data.ExcuteQueryUpdateDB(sql);
                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                    alert1.setTitle("THông báo");
                                    alert1.setHeaderText(null);
                                    alert1.setContentText("bạn chắc chắn muốn xóa chứ?");
                                    showStaff();
                                }
                            });
                        }
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(deleIcon_search);
                            }
                        }
                    };
                    return cellSearch;
                });
                col_search_up.setCellFactory(column -> {
                    TableCell<Staff, Void> cellup_Search = new TableCell<>() {
                        FontAwesomeIcon upIcon_search = new FontAwesomeIcon();
                        {
                            // Styling the icon if needed
                            upIcon_search.getStyleClass().add("up-icon");
                            upIcon_search.setIconName("PENCIL");
                            upIcon_search.setSize("20px");
                            upIcon_search.setCursor(Cursor.HAND);
                            upIcon_search.setFill(Color.AQUA);
                            upIcon_search.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                Staff staff = tbl_search_staff.getSelectionModel().getSelectedItem();
                                getData.user = staff.getIdStaff();
                                try {
                                    // Lưu lại scene hiện tại
                                    Scene currentScene = upIcon_search.getScene();
                                    getData.current = currentScene;
                                    // Đóng stage hiện tại
                                    Stage currentStage = (Stage) upIcon_search.getScene().getWindow();
                                    currentStage.close();
                                    // Mở trang mới
                                    Parent root = FXMLLoader.load(getClass().getResource("up_staff.fxml"));
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(root);
                                    stage.initStyle(StageStyle.TRANSPARENT);
                                    stage.setScene(scene);
                                    stage.show();
//                                    Parent root = FXMLLoader.load(getClass().getResource("up_staff.fxml"));
//                                    Stage stage = new Stage();
//                                    Scene scene = new Scene(root);
//                                    //ẩn trang cũ
//                                    upIcon_search.getScene().getWindow().hide();
//                                    stage.initStyle(StageStyle.TRANSPARENT);
//                                    stage.setScene(scene);
//                                    stage.show();
                                    showStaff();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(upIcon_search);
                            }
                        }
                    };
                    return cellup_Search;
                });
                tbl_search_staff.setItems(list);
                tbl_search_staff.setVisible(true);
                label_search.setVisible(false);
                search_staff.setText("");
            } else {
                tbl_search_staff.setVisible(false);
                label_search.setVisible(true);
                search_staff.setText("");
            }
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
    public void reset(){
        id_staff.setText("");name_staff.setText("");date_staff.setValue(null);
        cmnd.setText("");addre_staff.setText("");sdt_staff.setText("");
        image_staff.setImage(null);
    }

    //=============================================
    // ====================Medicine==================

    public void addMedicine(){

        //thông báo đăng nhập thành công và trờ về trang chủ

        try {
            Parent root = FXMLLoader.load(getClass().getResource("upMe.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            //ẩn trang cũ
            add_btl_Me.getScene().getWindow().hide();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void search_nameMe(){
        String id ;
        Data da = new Data();
        String check = "select medicine.idMe from medicine where nameMedi like  '%"+search_Me.getText()+"%';";
        try {
            ResultSet checkdata = da.ExcuteQueryGetTable(check) ;
            if(checkdata.next()){
                id = checkdata.getString("idMe");
                String sql = "SELECT medicine.idMe, medicine.nameMedi,medicine.price, medicine.location,\n" +
                        "detail_me.quantity, detail_me.id_lo, detail_me.expiry, detail_me.idSup\n" +
                        "FROM nhathuocdb.medicine\n" +
                        "join nhathuocdb.detail_me on medicine.idMe = detail_me.idMe\n" +
                        "where medicine.idMe = '"+id+"';";
                ObservableList<Medicine> list = FXCollections.observableArrayList();
                ResultSet rs = da.ExcuteQueryGetTable(sql);
                while(rs.next()){
                    Medicine me = new Medicine();
                    me.setIdMe(rs.getString("idMe"));
                    me.setNameMe(rs.getString("nameMedi"));
                    me.setIdSup(rs.getString("idSup"));
                    me.setPrice(Integer.parseInt(rs.getString("price")));
                    me.setLocation(rs.getString("location"));
                    me.setQuantity(Integer.parseInt(rs.getString("quantity")));
                    me.setExpiry(Date.valueOf(rs.getString("expiry")));
                    me.setMalo(rs.getString("id_lo"));
                    list.add(me);

                }
                if (!list.isEmpty()) {
                    col_idMe.setCellValueFactory(new PropertyValueFactory<>("idMe"));
                    col_nameMe.setCellValueFactory(new PropertyValueFactory<>("nameMe"));
                    col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
                    col_expiry.setCellValueFactory(new PropertyValueFactory<>("expiry"));
                    col_idSup_me.setCellValueFactory(new PropertyValueFactory<>("idSup"));
                    col_malo.setCellValueFactory(new PropertyValueFactory<>("malo"));
                    col_deleMe.setCellFactory(column -> {
                        TableCell<Medicine, Void> cellSearch = new TableCell<>() {
                            FontAwesomeIcon deleIcon_search = new FontAwesomeIcon();
                            {
                                // Styling the icon if needed
                                deleIcon_search.getStyleClass().add("delete-icon");
                                deleIcon_search.setIconName("TRASH");
                                deleIcon_search.setSize("20px");
                                deleIcon_search.setCursor(Cursor.HAND);
                                deleIcon_search.setFill(Color.RED);
                                deleIcon_search.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Xác nhận thông tin");
                                    alert.setHeaderText(null);
                                    alert.setContentText("bạn chắc chắn muốn xóa chứ?");
                                    Optional<ButtonType> op = alert.showAndWait();
                                    if(op.get().equals(ButtonType.OK)){
                                        Medicine medi = tbl_medicine.getSelectionModel().getSelectedItem();
                                        // Thực hiện hoạt động khi người dùng nhấp vào biểu tượng xóa
                                        String sql1 = "delete from medicine where idMe ='"+medi.getIdMe()+"';";
                                        String sql2 = "delete from detail_me where idMe ='"+medi.getIdMe()+"';";
                                        Data data = new Data();
                                        data.ExcuteQueryUpdateDB(sql1);
                                        data.ExcuteQueryUpdateDB(sql2);
                                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                        alert1.setTitle("Thông báo");
                                        alert1.setHeaderText(null);
                                        alert1.setContentText("bạn chắc chắn muốn xóa chứ?");
                                        //showStaff();
                                        noty_search.setVisible(false);
                                        tbl_medicine.setVisible(false);
                                        //noty_search.setVisible(false);
                                    }
                                });
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(deleIcon_search);
                                }
                            }
                        };
                        return cellSearch;
                    });
                    col_upMe.setCellFactory(column -> {
                        TableCell<Medicine, Void> cellup_Search = new TableCell<>() {
                            FontAwesomeIcon upIcon_search = new FontAwesomeIcon();
                            {
                                // Styling the icon if needed
                                upIcon_search.getStyleClass().add("up-icon");
                                upIcon_search.setIconName("PENCIL");
                                upIcon_search.setSize("20px");
                                upIcon_search.setCursor(Cursor.HAND);
                                upIcon_search.setFill(Color.AQUA);
                                upIcon_search.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    Medicine medi = tbl_medicine.getSelectionModel().getSelectedItem();
                                    getData.ma = medi.getIdMe()+ " "+ medi.getMalo();
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("upMe.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(root);
                                        //ẩn trang cũ
                                        upIcon_search.getScene().getWindow().hide();
                                        stage.initStyle(StageStyle.TRANSPARENT);
                                        stage.setScene(scene);
                                        stage.show();
                                        //showStaff();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(upIcon_search);
                                }
                            }
                        };
                        return cellup_Search;
                    });
                    tbl_medicine.setItems(list);
                    tbl_medicine.setVisible(true);
                    noty_search.setVisible(false);
                    search_Me.setText("");
                } else {
                    tbl_medicine.setVisible(false);
                    noty_search.setVisible(true);
                    search_Me.setText("");
                }
            }else {
                search_Me.setText("");
                noty_search.setVisible(true);
                tbl_medicine.setVisible(false);

            }

        }catch (Exception e){e.printStackTrace();}

    }
    //==========================================
    //===========GROUP MEDICINE=================
    public void showGrMedicineForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("group-medicine.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            add_group_medicine.getScene().getWindow().hide();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void initalTableValue(){
        Data data = new Data();
        table_view_type.setVisible(true);
        ObservableList<Type> listType = FXCollections.observableArrayList();
        String sql = "SELECT * FROM groupmedi";
        try {
            ResultSet resultSet = data.ExcuteQueryGetTable(sql);
            while (resultSet.next()){
                Type type = new Type();
                type.setIdType(resultSet.getString("id_group"));
                type.setNameType(resultSet.getString("name_group"));
                type.setDescribe(resultSet.getString("describeG"));
                type.setPosition(resultSet.getString("locationG"));
                System.out.println(type.getIdType());
                listType.add(type);
            }
            if (!listType.isEmpty()) {
                id_type_column.setCellValueFactory(new PropertyValueFactory<>("idType"));
                name_type_column.setCellValueFactory(new PropertyValueFactory<>("nameType"));
                describe_type_column.setCellValueFactory(new PropertyValueFactory<>("describe"));
                position_type_column.setCellValueFactory(new PropertyValueFactory<>("position"));
                delete_type_column.setCellFactory(column -> {
                    TableCell<Type, Void> delete_cell = new TableCell<>() {
                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        {
                            deleteIcon.getStyleClass().add("delete-icon");
                            deleteIcon.setIconName("TRASH");
                            deleteIcon.setSize("20px");
                            deleteIcon.setCursor(Cursor.HAND);
                            deleteIcon.setFill(Color.RED);
                            deleteIcon.setOnMouseClicked((EventHandler<? super MouseEvent>) event -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Xoá nhóm thuốc");
                                alert.setHeaderText(null);
                                alert.setContentText("Bạn chắc chắn muốn xoá nhóm thuốc này không?");
                                Optional<ButtonType> optional = alert.showAndWait();
                                if (optional.get().equals(ButtonType.OK)) {
                                    Type type = table_view_type.getSelectionModel().getSelectedItem();
                                    String sql = "DELETE from groupmedi WHERE id_group = '" + type.getIdType() + "';";
                                    Data data = new Data();
                                    data.ExcuteQueryUpdateDB(sql);
                                    noti("Xoá nhóm thuốc thành công!");
                                    initalTableValue();
                                    no_result.setVisible(false);
                                }
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(deleteIcon);
                            }
                        }
                    };
                    return delete_cell;
                });
                edit_type_column.setCellFactory(column -> {
                    TableCell<Type, Void> update_cell = new TableCell<>() {
                        FontAwesomeIcon update_icon = new FontAwesomeIcon();

                        {
                            update_icon.getStyleClass().add("up-icon");
                            update_icon.setIconName("PENCIL");
                            update_icon.setSize("20px");
                            update_icon.setCursor(Cursor.HAND);
                            update_icon.setFill(Color.AQUA);
                            update_icon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                getData.type = table_view_type.getSelectionModel().getSelectedItem();
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("group-medicine.fxml"));
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(root);
                                    update_icon.getScene().getWindow().hide();
                                    stage.initStyle(StageStyle.TRANSPARENT);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(update_icon);
                            }
                        }
                    };
                    return update_cell;
                });
                table_view_type.setItems(listType);
                search_type.setText("");
                table_view_type.setVisible(true);
                no_result.setVisible(false);
            }else {
                table_view_type.setVisible(false);
                no_result.setVisible(true);
                search_type.setText("");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void searchTypeByName() {
        String idType;
        Data data = new Data();
        table_view_type.setVisible(true);
        ObservableList<Type> listType = FXCollections.observableArrayList();
        String searchSql = "SELECT * FROM groupmedi WHERE name_group LIKE '%" + search_type.getText() + "%';";
        try{
            ResultSet resultSet = data.ExcuteQueryGetTable(searchSql);
            while(resultSet.next()) {
                Type type = new Type();
                type.setIdType(resultSet.getString("id_group"));
                type.setNameType(resultSet.getString("name_group"));
                type.setDescribe(resultSet.getString("describeG"));
                type.setPosition(resultSet.getString("locationG"));
                System.out.println(type.getIdType());
                listType.add(type);
            }
                if (!listType.isEmpty()) {
                    id_type_column.setCellValueFactory(new PropertyValueFactory<>("idType"));
                    name_type_column.setCellValueFactory(new PropertyValueFactory<>("nameType"));
                    describe_type_column.setCellValueFactory(new PropertyValueFactory<>("describe"));
                    position_type_column.setCellValueFactory(new PropertyValueFactory<>("position"));
                    delete_type_column.setCellFactory(column -> {
                        TableCell<Type, Void> delete_cell = new TableCell<>() {
                            FontAwesomeIcon deleteIcon = new FontAwesomeIcon();

                            {
                                deleteIcon.getStyleClass().add("delete-icon");
                                deleteIcon.setIconName("TRASH");
                                deleteIcon.setSize("20px");
                                deleteIcon.setCursor(Cursor.HAND);
                                deleteIcon.setFill(Color.RED);
                                deleteIcon.setOnMouseClicked((EventHandler<? super MouseEvent>) event -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Xoá nhóm thuốc");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Bạn chắc chắn muốn xoá nhóm thuốc này không?");
                                    Optional<ButtonType> optional = alert.showAndWait();
                                    if (optional.get().equals(ButtonType.OK)) {
                                        Type type = table_view_type.getSelectionModel().getSelectedItem();
                                        String sql = "DELETE from groupmedi WHERE id_group = '" + type.getIdType() + "';";
                                        Data data = new Data();
                                        data.ExcuteQueryUpdateDB(sql);
                                        noti("Xoá nhóm thuốc thành công!");
                                        no_result.setVisible(false);
                                        table_view_type.setVisible(false);
                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(deleteIcon);
                                }
                            }
                        };
                        return delete_cell;
                    });
                    edit_type_column.setCellFactory(column -> {
                        TableCell<Type, Void> update_cell = new TableCell<>() {
                            FontAwesomeIcon update_icon = new FontAwesomeIcon();

                            {
                                update_icon.getStyleClass().add("up-icon");
                                update_icon.setIconName("PENCIL");
                                update_icon.setSize("20px");
                                update_icon.setCursor(Cursor.HAND);
                                update_icon.setFill(Color.AQUA);
                                update_icon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    getData.type = table_view_type.getSelectionModel().getSelectedItem();
                                    try {
                                        Parent root = FXMLLoader.load(getClass().getResource("group-medicine.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(root);
                                        update_icon.getScene().getWindow().hide();
                                        stage.initStyle(StageStyle.TRANSPARENT);
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(update_icon);
                                }
                            }
                        };
                        return update_cell;
                    });
                    table_view_type.setItems(listType);
                    search_type.setText("");
                    table_view_type.setVisible(true);
                    no_result.setVisible(false);
                }else {
                    table_view_type.setVisible(false);
                    no_result.setVisible(true);
                    search_type.setText("");
                }

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //endregion
    //region bill in
    public void billSearchAction(ActionEvent even){
        BillInService services = new BillInService();
            String mnv = bill_search_mnv.getText();
            String ncc = bill_search_ncc.getText();
            LocalDate localDate = bill_search_date.getValue();
            java.util.Date utilDate = new java.util.Date();
            try {
                utilDate  = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
            catch (Exception ex){
                utilDate = null;
            }
            List<BillIn> billIns = services.GetByCondition(mnv,utilDate,ncc);
        ObservableList<BillIn> bills = FXCollections.observableList(billIns);
        bill_tbl_mabill.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        bill_tbl_manv.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
        bill_tbl_ngaytao.setCellValueFactory(new PropertyValueFactory<>("date"));
        bill_tbl_tongso.setCellValueFactory(new PropertyValueFactory<>("total"));
        bill_tbl_mancc.setCellValueFactory(new PropertyValueFactory<>("ncc"));
        bill_tbl_view.setCellFactory(column ->{
            TableCell<BillIn, Void> cellup = new TableCell<>() {
                FontAwesomeIcon viewIcon = new FontAwesomeIcon();
                {
                    // Styling the icon if needed
                    viewIcon.getStyleClass().add("delete-icon");
                    viewIcon.setIconName("EYE");
                    viewIcon.setSize("20px");
                    viewIcon.setCursor(Cursor.HAND);
                    viewIcon.setFill(Color.AQUA);
                    viewIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                        BillIn billInstate = bill_tbl.getSelectionModel().getSelectedItem();
                        getData.idBillIn = billInstate.getIdBill();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("ViewDetailBillIn.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(scene);
                            stage.setOnHidden(e -> {

                            });

                            stage.show();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(viewIcon);
                    }
                }
            };
            return cellup;
        });
        bill_tbl.setItems(bills);
    }

    public void onNhapbillIn(ActionEvent even){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InputBill.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setOnHidden(e -> {

            });

            stage.show();
        }
        catch (Exception ex){ex.printStackTrace();
        }
    }
    //endregion
    // =======================Bill===============
    public void addBill(){

        //thông báo đăng nhập thành công và trờ về trang chủ

        try {
            Parent root = FXMLLoader.load(getClass().getResource("bill.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            //ẩn trang cũ
            //add_bill.getScene().getWindow().hide();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private ObservableList<Bill> list_bill_searh = FXCollections.observableArrayList();
    public void search_bill(){
        Data data = new Data();
        list_bill_searh.clear();
        if(search_bill_idMe.getText().isEmpty() && search_bill_date.getValue() == null){
            noti("Chưa điền thông tin");
        } else if (!search_bill_idMe.getText().isEmpty() && search_bill_date.getValue() == null) {
            try {
                tbl_bill.setVisible(false);
                chitiet_bill.setVisible(false);
                noti_search_bill.setVisible(false);
                tbl_bill_search.setVisible(false);
                Bill bill;
                String sql = "select * from bill where idStaff ='"+search_bill_idMe.getText()+"'";
                ResultSet rs = data.ExcuteQueryGetTable(sql);
                while (rs.next()){
                    bill = new Bill(rs.getString("idBill"),rs.getString("idStaff"),rs.getInt("Total"),rs.getDate("date_create"),rs.getString("note"));
                    list_bill_searh.add(bill);
                }
                if(list_bill_searh.isEmpty()){
                    noti_search_bill.setVisible(true);

                }else {
                    tbl_bill_search.setVisible(true);
                    col_bill_id.setCellValueFactory(new PropertyValueFactory<>("idBill"));
                    col_bill_idMe.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
                    col_bill_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    col_bill_money.setCellValueFactory(new PropertyValueFactory<>("total"));
                    col_bill_note.setCellValueFactory(new PropertyValueFactory<>("note"));
                    col_bill_chitiet.setCellFactory(column -> {
                        TableCell<Bill, Void> cell = new TableCell<>() {
                            FontAwesomeIcon deleIcon = new FontAwesomeIcon();
                            {
                                // Styling the icon if needed
                                deleIcon.getStyleClass().add("delete-icon");
                                deleIcon.setIconName("EYE");
                                deleIcon.setSize("20px");
                                deleIcon.setCursor(Cursor.HAND);
                                deleIcon.setFill(Color.AQUA);
                                deleIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    Bill b = tbl_bill_search.getSelectionModel().getSelectedItem();
                                    detailBill(b);
                                });
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(deleIcon);
                                }
                            }
                        };
                        return cell;
                    });
                    tbl_bill_search.setItems(list_bill_searh);
                    search_bill_idMe.setText("");
                }

            }catch (Exception ex){ex.printStackTrace();}
        } else if (!(search_bill_date.getValue() == null) && search_bill_idMe.getText().isEmpty() ) {
            tbl_bill.setVisible(false);
            chitiet_bill.setVisible(false);
            noti_search_bill.setVisible(false);
            tbl_bill_search.setVisible(false);
            Bill bill;
            LocalDate loc = search_bill_date.getValue();
            Date sqlDate = java.sql.Date.valueOf(loc);
            try {
                String sql = "select * from bill where date_create ='"+sqlDate+"'";
                ResultSet rs = data.ExcuteQueryGetTable(sql);
                while (rs.next()){
                    bill = new Bill(rs.getString("idBill"),rs.getString("idStaff"),rs.getInt("Total"),rs.getDate("date_create"),rs.getString("note"));
                    list_bill_searh.add(bill);
                }
                if(list_bill_searh.isEmpty()){
                    noti_search_bill.setVisible(true);
                    noti_search_bill.setText("Ngày mà bạn tìm không có hóa đơn nào đuược lập ra");

                }else {
                    tbl_bill_search.setVisible(true);
                    col_bill_id.setCellValueFactory(new PropertyValueFactory<>("idBill"));
                    col_bill_idMe.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
                    col_bill_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    col_bill_money.setCellValueFactory(new PropertyValueFactory<>("total"));
                    col_bill_note.setCellValueFactory(new PropertyValueFactory<>("note"));
                    col_bill_chitiet.setCellFactory(column -> {
                        TableCell<Bill, Void> cell = new TableCell<>() {
                            FontAwesomeIcon deleIcon = new FontAwesomeIcon();
                            {
                                // Styling the icon if needed
                                deleIcon.getStyleClass().add("delete-icon");
                                deleIcon.setIconName("EYE");
                                deleIcon.setSize("20px");
                                deleIcon.setCursor(Cursor.HAND);
                                deleIcon.setFill(Color.AQUA);
                                deleIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    Bill b = tbl_bill_search.getSelectionModel().getSelectedItem();
                                    detailBill(b);
                                });
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(deleIcon);
                                }
                            }
                        };
                        return cell;
                    });
                    tbl_bill_search.setItems(list_bill_searh);
                    search_bill_idMe.setText("");
                    search_bill_date.setValue(null);
                }

            }catch (Exception ex){ex.printStackTrace();}
        } else if (!(search_bill_date.getValue() == null) && !search_bill_idMe.getText().isEmpty()) {
            tbl_bill.setVisible(false);
            chitiet_bill.setVisible(false);
            noti_search_bill.setVisible(false);
            tbl_bill_search.setVisible(false);
            Bill bill;
            LocalDate loc = search_bill_date.getValue();
            Date sqlDate = java.sql.Date.valueOf(loc);
            try {
                String sql = "select * from bill where date_create ='"+sqlDate+"' and idStaff ='"+search_bill_idMe.getText()+"'";
                ResultSet rs = data.ExcuteQueryGetTable(sql);
                while (rs.next()){
                    bill = new Bill(rs.getString("idBill"),rs.getString("idStaff"),rs.getInt("Total"),rs.getDate("date_create"),rs.getString("note"));
                    list_bill_searh.add(bill);
                }
                if(list_bill_searh.isEmpty()){
                    noti_search_bill.setVisible(true);

                }else {
                    tbl_bill_search.setVisible(true);
                    col_bill_id.setCellValueFactory(new PropertyValueFactory<>("idBill"));
                    col_bill_idMe.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
                    col_bill_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                    col_bill_money.setCellValueFactory(new PropertyValueFactory<>("total"));
                    col_bill_note.setCellValueFactory(new PropertyValueFactory<>("note"));
                    col_bill_chitiet.setCellFactory(column -> {
                        TableCell<Bill, Void> cell = new TableCell<>() {
                            FontAwesomeIcon deleIcon = new FontAwesomeIcon();
                            {
                                // Styling the icon if needed
                                deleIcon.getStyleClass().add("delete-icon");
                                deleIcon.setIconName("EYE");
                                deleIcon.setSize("20px");
                                deleIcon.setCursor(Cursor.HAND);
                                deleIcon.setFill(Color.AQUA);
                                deleIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                                    Bill b = tbl_bill_search.getSelectionModel().getSelectedItem();
                                    detailBill(b);
                                });
                            }
                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(deleIcon);
                                }
                            }
                        };
                        return cell;
                    });
                    tbl_bill_search.setItems(list_bill_searh);
                    search_bill_idMe.setText("");
                    search_bill_date.setValue(null);
                }

            }catch (Exception ex){ex.printStackTrace();}
        }


    }
    public void detailBill(Bill bill){
        Data data = new Data();
        ObservableList<Detail_bill> list_detaiBill = FXCollections.observableArrayList();
        noti_search_bill.setVisible(false);
        tbl_bill_search.setVisible(false);
        tbl_bill.setVisible(true);
        chitiet_bill.setVisible(true);
        chitiet_bill_idbill.setText(bill.getIdBill());
        chitiet_bill_idstaff.setText(bill.getIdStaff());
        chitiet_bill_date.setText(String.valueOf(bill.getDate()));
        chitiet_bill_total.setText(String.valueOf(bill.getTotal()));
        chitiet_bill_note.setText(bill.getNote());
        try{
            String sql = "select * from detailbill where ID_bill = '"+bill.getIdBill()+"'";
            ResultSet rs = data.ExcuteQueryGetTable(sql);
            Detail_bill a;
            while(rs.next()){
                a=new Detail_bill(rs.getString("ID_bill"),rs.getString("idMe"),rs.getString("nameMedi"),
                        rs.getInt("quantity"), rs.getInt("money"));
                list_detaiBill.add(a);
            }
            col_idme.setCellValueFactory(new PropertyValueFactory<>("idMe"));
            col_nmaeme.setCellValueFactory(new PropertyValueFactory<>("nameMe"));
            col_sl.setCellValueFactory(new PropertyValueFactory<>("size"));
            col_money.setCellValueFactory(new PropertyValueFactory<>("money"));
            tbl_bill.setItems(list_detaiBill);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //displayUser();
        showStaff();
        add_list_sex();
        if(getData.state == "bill"){
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(true);
            medicine_form.setVisible(false);
            supplier_form.setVisible(false);
        } else if (getData.state == "medicine") {
            dash_form.setVisible(false);
            staff_form.setVisible(false);
            bill_form.setVisible(false);
            medicine_form.setVisible(true);
            supplier_form.setVisible(false);

        }
//        showStaff();
//        add_list_sex();
        InserSupplierToTable1();

    }

    //region nha cung cap
    public void InserSupplierToTable1()  {
        SupplierService supplierService = new SupplierService();
        ObservableList<Supplier> suppliers = FXCollections.observableList(supplierService.getAll());

        ncc_1_tbl_id.setCellValueFactory(new PropertyValueFactory<>("idSup"));
        ncc_1_tbl_name.setCellValueFactory(new PropertyValueFactory<>("nameSup"));
        ncc_1_tbl_adress.setCellValueFactory(new PropertyValueFactory<>("addresSup"));
        ncc_1_tbl_sdt.setCellValueFactory(new PropertyValueFactory<>("sdtSup"));
        ncc_1_tbl_deletea.setCellFactory(column -> {
            TableCell<Supplier, Void> cell = new TableCell<>() {
                FontAwesomeIcon deleIcon = new FontAwesomeIcon();
                {
                    // Styling the icon if needed
                    deleIcon.getStyleClass().add("delete-icon");
                    deleIcon.setIconName("TRASH");
                    deleIcon.setSize("20px");
                    deleIcon.setCursor(Cursor.HAND);
                    deleIcon.setFill(Color.RED);
                    deleIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Xác nhận thông tin");
                        alert.setHeaderText(null);
                        alert.setContentText("bạn chắc chắn muốn xóa chứ?");
                        Optional<ButtonType> op = alert.showAndWait();
                        if(op.get().equals(ButtonType.OK)){
                            Supplier supplierState = ncc_1_tbl.getSelectionModel().getSelectedItem();
                            // Thực hiện hoạt động khi người dùng nhấp vào biểu tượng xóa
                            String sql = "delete from supplier where idSup ='"+supplierState.getIdSup()+"';";
                            Data data = new Data();
                            data.ExcuteQueryUpdateDB(sql);
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("THông báo");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Success");
                            InserSupplierToTable1();
                        }
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleIcon);
                    }
                }
            };
            return cell;
        });
        ncc_1_tbl_edita.setCellFactory(column -> {
            TableCell<Supplier, Void> cellup = new TableCell<>() {
                FontAwesomeIcon upIcon = new FontAwesomeIcon();
                {
                    // Styling the icon if needed
                    upIcon.getStyleClass().add("up-icon");
                    upIcon.setIconName("PENCIL");
                    upIcon.setSize("20px");
                    upIcon.setCursor(Cursor.HAND);
                    upIcon.setFill(Color.AQUA);
                    upIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                        Supplier supplierState = ncc_1_tbl.getSelectionModel().getSelectedItem();
                        getData.supplier = supplierState;
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("up_supplier.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(scene);
                            stage.setOnHidden(e -> {
                                // Thực hiện các hành động sau khi cửa sổ kết thúc
                                InserSupplierToTable1();
                            });

                            stage.show();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(upIcon);
                    }
                }
            };
            return cellup;
        });
        ncc_1_tbl.setItems(suppliers);
        UUID uuid1 = UUID.randomUUID();
        ncc_2_id.setText(uuid1+"");
    }

    public void addNCC(ActionEvent event){
        String id = ncc_2_id.getText();
        String name = ncc_2_name.getText();
        String sdt = ncc_2_numberphone.getText();
        String address = ncc_2_adress.getText();
        if(id.isEmpty()||name.isEmpty()||sdt.isEmpty()||address.isEmpty()){
            erro("Vui lòng nhập đủ thông tin");
        }
        else {
            SupplierService service = new SupplierService();
            service.add(id,name,sdt,address);
            noti("Success");
            InserSupplierToTable1();
        }
    }
    public void resetNCC(ActionEvent event){
         UUID uuid1 = UUID.randomUUID();
         ncc_2_id.setText(uuid1+"");
         ncc_2_name.setText("");
         ncc_2_numberphone.setText("");
         ncc_2_adress.setText("");
    }
    public void searchSup(ActionEvent event){
        String keySearch = ncc_2_search.getText();
        SupplierService services = new SupplierService();
        List<Supplier> listt = services.getBySearch(keySearch);
        ObservableList<Supplier> suppliers = FXCollections.observableList(listt);
        if(listt.size()<1){
            noti("Không tìm thấy");
        }
        ncc_2_tbl_id.setCellValueFactory(new PropertyValueFactory<>("idSup"));
        ncc_2_tbl_name.setCellValueFactory(new PropertyValueFactory<>("nameSup"));
        ncc_2_tbl_sdt.setCellValueFactory(new PropertyValueFactory<>("sdtSup"));
        ncc_2_tbl_adress.setCellValueFactory(new PropertyValueFactory<>("addresSup"));
        ncc_2_tbl.setItems(suppliers);
    }
    //endregion


}
