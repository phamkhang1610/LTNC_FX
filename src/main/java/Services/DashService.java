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
    public List<Bill> chartBill(Date from, Date to){
        List<Bill> list= new ArrayList<>();
        Bill b;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(from);
        String toDate = dateFormat.format(to);
        String sql = "SELECT date_create,sum(Total)\n" +
                "FROM nhathuocdb.bill\n" +
                "WHERE date_create BETWEEN '"+fromDate+"' AND '"+toDate+"'\n" +
                "GROUP BY date_create\n" +
                "ORDER BY timestamp(date_create) ASC;";
        ResultSet rs = data.ExcuteQueryGetTable(sql);
        try {
            while (rs.next()){
                b = new Bill(" "," ",rs.getInt("sum(Total)"),rs.getDate("date_create")," ");
                list.add(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            data.Close();
        }

        return list;
    }

}
