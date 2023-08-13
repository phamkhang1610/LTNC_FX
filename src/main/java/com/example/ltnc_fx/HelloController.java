package com.example.ltnc_fx;

import Data.Data;
import Model.Account;
import Model.getData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Button close_btn;

    @FXML
    private Button log_btn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

//    @FXML
//    void close_btn(MouseEvent event) {
//
//    }
    //data tool
    private PreparedStatement pre;
    private Connection connect;
    private ResultSet result;

    //public static Account acc;
    public void loginAdmin() throws SQLException, IOException {
        String sql = "select * from staff where user = ? and pass = ?";
        Data data = new Data();
        try{

            ResultSet resultSet = data.login(sql,username.getText(), password.getText());
            Alert alert;
            if(username.getText().isEmpty()||password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR MESSAGE");
                alert.setHeaderText(null);
                alert.setContentText("Chưa điền đầy đủ thông tin");
                alert.showAndWait();
            }else {
                if(resultSet.next()){
                    Data da= new Data();
                    String a = "select * from role where ID='"+resultSet.getString("role_id")+"'";
                    ResultSet rs= da.ExcuteQueryGetTable(a);
                    if(rs.next()){
                        System.out.println(rs.getString("RoleName"));
                        Account ac = new Account();
                        ac.setIdStaff(resultSet.getString("idStaff"));
                        ac.setUsername(resultSet.getString("user"));
                        ac.setPassword(resultSet.getString("pass"));
                        ac.setRole(rs.getString("RoleName"));
                        getData.account =ac;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("SUCCESSFULL MESSAGE");
                        alert.setHeaderText(null);
                        alert.setContentText("Đăng nhập thành công");
                        alert.showAndWait();
                        // ẩn form login
                        log_btn.getScene().getWindow().hide();
                        //thông báo đăng nhập thành công và trờ về trang chủ
                        Parent root = FXMLLoader.load(getClass().getResource("Databroad.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                    }else {
                        System.out.println("ko thấy");
                    }

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Đăng nhập thất bại");
                    alert.setHeaderText(null);
                    alert.setContentText("Mật khẩu hoặc tên đang nhập sai");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            data.Close();
        }
    }


    public void close_btn(){
        System.exit(0);
    }
}