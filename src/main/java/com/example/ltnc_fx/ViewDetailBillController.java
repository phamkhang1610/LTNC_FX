package com.example.ltnc_fx;

import Model.Bill;
import Model.BillInDetail;
import Model.Supplier;
import Model.getData;
import Services.BillInService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewDetailBillController implements Initializable {
    @FXML
    private TableView<BillInDetail> bii_tbl_detail;

    @FXML
    private TableColumn<BillInDetail, String> bii_tbl_detail_mabill;

    @FXML
    private TableColumn<BillInDetail, String> bii_tbl_detail_mathuoc;

    @FXML
    private TableColumn<BillInDetail, String>  bii_tbl_detail_soluong;

    @FXML
    private TableColumn<BillInDetail, String>  bii_tbl_detail_tenthuoc;

    @FXML
    private TableColumn<BillInDetail, String>  bii_tbl_detail_tongtien;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Insertdatatotable();
    }
    public  void Insertdatatotable() {
        BillInService service = new BillInService();
        ObservableList<BillInDetail> billInDetails = FXCollections.observableList(service.GetBillDetailsById(getData.idBillIn));
        bii_tbl_detail_mabill.setCellValueFactory(new PropertyValueFactory<>("ID_bill"));
        bii_tbl_detail_mathuoc.setCellValueFactory(new PropertyValueFactory<>("idMe"));
        bii_tbl_detail_soluong.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        bii_tbl_detail_tongtien.setCellValueFactory(new PropertyValueFactory<>("money"));
        bii_tbl_detail_tenthuoc.setCellValueFactory(new PropertyValueFactory<>("nameMedi"));
        bii_tbl_detail.setItems(billInDetails);
    }
}
