package Services;

import Data.Data;
import Model.Bill;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
public class DashService {
    Data data;
   // public void show
    public DashService(){
        data = new Data();
    }
    public List<Bill> getBill(Date from, Date to){

        List<Bill> list= new ArrayList<>();
        Bill b;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(from);
        String toDate = dateFormat.format(to);
        String sql = "SELECT * FROM bill WHERE date_create BETWEEN '" + fromDate + "' AND '" + toDate + "'";
        ResultSet rs = data.ExcuteQueryGetTable(sql);
        try{
            while (rs.next()){
                b = new Bill(rs.getString("idBill"), rs.getString("idStaff"), rs.getInt("Total"),
                        rs.getDate("date_create"), rs.getString("note"));
                list.add(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Bill> chartBill(Date from, Date to) {

        Data data = new Data();
        List<Bill> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(from);
        String toDate = dateFormat.format(to);
        String sql = "SELECT date_create, SUM(Total) AS total " +
                "FROM bill " +
                "WHERE date_create BETWEEN '" + fromDate + "' AND '" + toDate + "' " +
                "GROUP BY date_create " +
                "ORDER BY date_create ASC;";
        ResultSet rs = data.ExcuteQueryGetTable(sql);
        try {
            while (rs.next()) {
                Date date = rs.getDate("date_create");
                int total = rs.getInt("Total");
                System.out.println("Date: " + date + " total: " + total);
                Bill b = new Bill(" ", " ", total, date, " ");
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            data.Close();
        }

        return list;
    }

}
