package com.example.ltnc_fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BillController implements Initializable {
    @FXML
    private Button add_btn_bill;

    @FXML
    private TableColumn<?, ?> col_idme;

    @FXML
    private TableColumn<?, ?> col_money;

    @FXML
    private TableColumn<?, ?> col_nmaeme;

    @FXML
    private TableColumn<?, ?> col_sl;

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
    private TableView<?> tbl_bill;

    @FXML
    private Label total_bill;

    @FXML
    void close(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
