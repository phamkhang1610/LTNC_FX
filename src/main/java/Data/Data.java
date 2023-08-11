package Data;

import java.sql.*;

/**
 *
 * @author Admin..................
 */
public class Data {
    String URL = "jdbc:mysql://103.38.236.189:3306/nhathuocdb";
    String username = "Datbeo";
    String password ="Dat.anh3";
     public Connection conn;
    public Data(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loader\n");
            conn = DriverManager.getConnection(URL, username, password);
            if (conn != null) {
                System.out.println("Connect Success!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    public void Close() {
        try{
            conn.close();
        }
        catch (Exception ex){

        }
    }
    //login
    public ResultSet login(String sql,String username, String password){
        try{
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            return rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //select
    public ResultSet ExcuteQueryGetTable(String sql){
        try {
            Statement pre = conn.createStatement();
            ResultSet rs = pre.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return null;
    }
    //insert, delete, update
    public void ExcuteQueryUpdateDB(String sql){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    public static void main(String[] args){
        Data da = new Data();
    }
}
