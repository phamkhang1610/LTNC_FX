package com.example.ltnc_fx;

import Data.Data;
import Model.BillInDetail;
import Model.MechidenAdd;
import Model.getData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class InPutBillController implements Initializable {
    @FXML
    private Text TongTien;
    @FXML
    private Button xacnhan_save;

    @FXML
    private TableView<MechidenAdd> bill_in_tbl;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_mathuoc;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_solo;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_soluong;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_tenthuoc;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_tinhtien;

    @FXML
    private TableColumn<MechidenAdd, String> bill_in_tbl_xoa;

    @FXML
    private TextField bill_nhap_mancc;

    @FXML
    private TextField bill_nhap_sl;

    @FXML
    private TextField bill_nhap_solo;
    @FXML
    private Label tongtien_bill;

    @FXML
    private TextField bill_nhap_tenthuoc;
    @FXML
    private TextField bill_nhap_dongia;
    @FXML
    private DatePicker hansudung;

    @FXML
    public void OnAdd(ActionEvent event) {
        AddAcion();
    }

    @FXML
    public void OnTinhTien(ActionEvent event) {
        int tong =0;
            for(MechidenAdd a : getData.mechidenAddList){
                tong+= a.getSoluong()*a.getTinhTien();
            }

        tongtien_bill.setText(tong+"");
    }

    public void OnXacnhan(ActionEvent event){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new Date(timestamp.getTime());
        String time = String.valueOf(System.currentTimeMillis());
        Data data = new Data();
        String sql = "INSERT INTO billin (`idBill`, `idStaff`, `date_create`, `Total`, `ncc`) " +
                " VALUES ('"+timestamp+"', '"+3+"', '"+date+"', '"+tongtien_bill.getText()+"', '"+bill_nhap_mancc.getText()+"');\n";

        try {
            data.ExcuteQueryUpdateDB(sql);
            for (MechidenAdd m: getData.mechidenAddList){
                String sql2 = "INSERT INTO detailbillin (`ID_bill`, `idMe`, `quantity`, `money`, `nameMedi`, `hsd`) " +
                        " VALUES ('"+ timestamp+"', '"+m.getMathuoc()+"', '"+m.getSoluong()+"', '"+m.getTinhTien()+"', '"+m.getTenthuoc()+"', '"+m.getDate()+"'); ";

                data.ExcuteQueryUpdateDB(sql2);
                String sql3 = "INSERT INTO detail_me (`idMe`, `id_lo`, `quantity`, `idSup`, `expiry`) " +
                        " VALUES ('"+m.getMathuoc()+"', '"+timestamp+"', '"+m.getSoluong()+"', '"+bill_nhap_mancc.getText()+"', '"+m.getDate()+"');";

                data.ExcuteQueryUpdateDB(sql3);
            }
        }catch (Exception ex){

        }
        finally {
            data.Close();
            noti("Thành Công");
            getData.mechidenAddList = null;
            xacnhan_save.getScene().getWindow().hide();

        }

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public  void AddAcion() {
        String mancc = bill_nhap_mancc.getText();
        String tenthuoc = bill_nhap_tenthuoc.getText();
        LocalDate date = hansudung.getValue();
        int dongia = Integer.parseInt(bill_nhap_dongia.getText());
        int soluong = Integer.parseInt(bill_nhap_sl.getText());


        String sql = "SELECT * FROM medicine where nameMedi = '" + tenthuoc + "' ";
        Data db = new Data();
        ResultSet rs = db.ExcuteQueryGetTable(sql);
        String id = "";
        String name = "";
        try {
            while (rs.next()) {
                id = rs.getString("idMe");
                name = rs.getString("nameMedi");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        if (id.isEmpty() && name.isEmpty()) {
            erro("Khong ton tai loai thuoc");
        }
        else{
        MechidenAdd mechidenAdds = new  MechidenAdd(id,name,soluong,dongia, java.sql.Date.valueOf(date),".");
           getData.mechidenAddList.add(mechidenAdds);
        }

        InSertDataTotableSell();
    }
    private void InSertDataTotableSell(){
        bill_in_tbl_mathuoc.setCellValueFactory(new PropertyValueFactory<>("mathuoc"));
        bill_in_tbl_tenthuoc.setCellValueFactory(new PropertyValueFactory<>("tenthuoc"));
        bill_in_tbl_soluong.setCellValueFactory(new PropertyValueFactory<>("soluong"));
        bill_in_tbl_tinhtien.setCellValueFactory(new PropertyValueFactory<>("tinhTien"));
        bill_in_tbl_solo.setCellValueFactory(new PropertyValueFactory<>("date"));

        bill_in_tbl.setItems(FXCollections.observableList(getData.mechidenAddList));
    }

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







}
