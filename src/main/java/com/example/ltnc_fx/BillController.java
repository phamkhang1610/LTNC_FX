package com.example.ltnc_fx;

import Data.Data;
import Model.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BillController implements Initializable {
    @FXML
    private Button add_btn_bill;
    @FXML
    private TableColumn<Detail_bill, Void> col_dele;

    @FXML
    private TableColumn<Detail_bill, Void> col_fix;
    @FXML
    private TableColumn<Detail_bill, String> col_idme;

    @FXML
    private TableColumn<Detail_bill,String> col_money;

    @FXML
    private TableColumn<Detail_bill, String> col_nmaeme;

    @FXML
    private TableColumn<Detail_bill, String> col_sl;

    @FXML
    private Button colse;

    @FXML
    private TextField idStaff;

    @FXML
    private TextField name;

    @FXML
    private TextArea note;

    @FXML
    private Button pay_btn_bill;

    @FXML
    private TextField size;

    @FXML
    private TableView<Detail_bill> tbl_bill;

    @FXML
    private Label total_bill;
//============================================
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
    public void showData(){
    try{
        col_idme.setCellValueFactory(new PropertyValueFactory<>("idMe"));
        col_nmaeme.setCellValueFactory(new PropertyValueFactory<>("nameMe"));
        col_sl.setCellValueFactory(new PropertyValueFactory<>("size"));
        col_money.setCellValueFactory(new PropertyValueFactory<>("money"));
        col_dele.setCellFactory(column -> {
            TableCell<Detail_bill, Void> cell = new TableCell<>() {
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
                            Detail_bill a = tbl_bill.getSelectionModel().getSelectedItem();
                            // Thực hiện hoạt động khi người dùng nhấp vào biểu tượng xóa
                            for (Detail_bill detailBill : list_detailBil) {
                                if (detailBill.getIdMe() == detailBill.getIdMe()) {
                                    list_detailBil.remove(detailBill);
                                    break; // nếu chỉ muốn xóa một phần tử có idMe = idMe của detaiBill, thì cần break vòng lặp
                                }
                            }
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Thông báo");
                            alert1.setHeaderText(null);
                            alert1.setContentText("bạn chắc chắn muốn xóa chứ?");
                            showData();
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
        col_fix.setCellFactory(column -> {
            TableCell<Detail_bill, Void> cellup = new TableCell<>() {
                FontAwesomeIcon upIcon = new FontAwesomeIcon();
                {
                    // Styling the icon if needed
                    upIcon.getStyleClass().add("up-icon");
                    upIcon.setIconName("PENCIL");
                    upIcon.setSize("20px");
                    upIcon.setCursor(Cursor.HAND);
                    upIcon.setFill(Color.AQUA);
                    upIcon.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                        Detail_bill b = tbl_bill.getSelectionModel().getSelectedItem();
                        name.setText(b.getNameMe());
                        size.setText(Integer.toString(b.getSize()));
                        for (Detail_bill detailBill : list_detailBil) {
                            if (detailBill.getIdMe() == detailBill.getIdMe()) {
                                list_detailBil.remove(detailBill);
                                break; // nếu chỉ muốn xóa một phần tử có idMe = idMe của detaiBill, thì cần break vòng lặp
                            }
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
        tbl_bill.setItems(list_detailBil);
        int money =0;
        for (Detail_bill detailBill : list_detailBil) {
            money += detailBill.getMoney();
        }
        total_bill.setText(Integer.toString(money));

    }catch (Exception e){
        e.printStackTrace();
    }

    }
    private ObservableList<Detail_bill> list_detailBil = FXCollections.observableArrayList();
    public void add_bill(){
        List<Detail_Me> list_detai = new ArrayList<>();
        Data data = new Data();
        Bill bill = new Bill();
        Detail_bill detai ;
        String id;

        String nameMe = name.getText();
        try{// kiểm tra có tên thuocs hay không
            String checkData = "select idMe, price from medicine where nameMedi = '"
                    +name.getText()+"'";
            ResultSet rs = data.ExcuteQueryGetTable(checkData);
            if(rs.next()){
                System.out.println(rs.getString("idMe"));
                // check số lượng
                String check = "select sum(quantity) from detail_me where idMe = '"+rs.getString("idMe")+"'";
                ResultSet kq = data.ExcuteQueryGetTable(check);
                int soluong=0;
                if(kq.next()){
                    soluong = kq.getInt(1);
                    if(soluong >= Integer.parseInt(size.getText())){
                        String listdata = "select * from detail_me where idMe = '"+ rs.getString("idMe")
                                +"' and quantity > 0 ";
                        ResultSet rs1 = data.ExcuteQueryGetTable(listdata);
//                        Detail_Me detailMe ;
//                        while (rs1.next()){
//                            detailMe = new Detail_Me(rs1.getString("idMe"), rs1.getString("id_lo"),
//                                    rs1.getString("idSup"),rs1.getInt("quantity"),
//                                    rs1.getDate("expiry"));
//                            list_detai.add(detailMe);
//                        }
                        detai = new Detail_bill("mx1",rs.getString("idMe"),name.getText(),
                                Integer.parseInt(size.getText()),Integer.parseInt(rs.getString("price"))*Integer.parseInt(size.getText()));
                        list_detailBil.add(detai);
                        showData();
                        // in ra số lượng cần mua
                        for (Detail_bill a:list_detailBil){
                            System.out.println(a.getSize());
                        }

                    }else {
                        erro("Số lượng thốc "+ name.getText()+" chỉ còn: "+ soluong+".");
                    }
                }
            }else {
                erro("Thuốc "+ name.getText()+" không có hoặc sai tên");
            }
            name.setText("");
            size.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void Pay(){
        List<Detail_Me> list_detai = new ArrayList<>();
        if(Integer.parseInt(total_bill.getText())<= 0){
            noti("không có sản phẩm nào cả");
        }else{
            if(idStaff.getText().isEmpty()){
                erro("Chưa điền mã nhân viên");
            }else {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = new Date(timestamp.getTime());
                String time = String.valueOf(System.currentTimeMillis());
                Data data = new Data();
                try{
                    String sql1 = "insert into bill (idBill, idStaff, date_create, Total,note) " +
                            "value ('"+time+ "','" +idStaff.getText()+"','"+date+"',"
                            +total_bill.getText()+",'"+note.getText()+"');";
                    data.ExcuteQueryUpdateDB(sql1);
                    for(Detail_bill a:list_detailBil){
                        String sql = "select * from detail_me where idMe= '"+a.getIdMe()+"'";
                        ResultSet r1 = data.ExcuteQueryGetTable(sql);
                        while (r1.next()){
                            Detail_Me da = new Detail_Me(r1.getString("idMe"),r1.getString("id_lo"),
                                    r1.getString("idSup"), r1.getInt("quantity"),r1.getDate("expiry"));
                            list_detai.add(da);
                        }
                        int sl = a.getSize();
                        for(Detail_Me c: list_detai){
                            if(sl == 0){
                                break;

                            }if(sl>=c.getQuantity()) {
                                if (sl == c.getQuantity()) {
                                    sl = 0;
                                    c.setQuantity(0);
                                    break;
                                } else {
                                    sl = sl - c.getQuantity();
                                    c.setQuantity(0);
                                }
                            }else {
                                int m = c.getQuantity() - sl;
                                c.setQuantity(m);
                                sl = 0;
                                break;
                            }
                        }
                        for( Detail_Me c: list_detai) {
                            System.out.println("size: "+ c.getQuantity());
                        }
                        //Cập nhật lại dữ liệu sau mua
                        for( Detail_Me c: list_detai) {
                            String up = "UPDATE detail_me SET quantity =" + c.getQuantity() + " WHERE idMe =" + c.getIdMe()
                                    + " AND id_lo ='" + c.getId_lo() + "';";
                            data.ExcuteQueryUpdateDB(up);
                        }

                        String sql2 = "insert into detailbill (ID_bill,idMe,quantity,money,nameMedi) value ('"+
                                time+"','"+a.getIdMe()+"',"+a.getSize()+","+a.getMoney()+",'"+a.getNameMe()+"')";
                        data.ExcuteQueryUpdateDB(sql2);

                    }
                    noti("Thêm thành công");
                    reset();

                }catch (Exception ex){ex.printStackTrace();}
            }
        }


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
            getData.state = "bill";
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void reset(){
        name.setText("");
        size.setText("");
        idStaff.setText("");
        note.setText("");
        total_bill.setText("0.0");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idStaff.setText(getData.account.getIdStaff());
    }
}
