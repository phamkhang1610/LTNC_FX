package Services;

import Data.Data;
import Model.BillIn;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillInService {
    Data _db;
    public BillInService(){
        _db = new Data();
    }

    public List<BillIn> GetAll() {
        String sql = "select * from billin ";
        List<BillIn> bills = new ArrayList<>();
        try {
            ResultSet rs =  _db.ExcuteQueryGetTable(sql);
            while (rs.next()){
                BillIn bill = new BillIn(
                        rs.getString("idBill"),
                        rs.getString("idStaff"),
                        rs.getInt("Total"),
                        rs.getDate("date_create"),
                        rs.getString("note"),
                        rs.getString("ncc")
                );
                bills.add(bill);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            _db.Close();
        }
        return bills;
    }

    public List<BillIn> GetByCondition(String staff, Date date, String ncc  ) {
        String sql ="select * from billin ";

        boolean c1 = !staff.isEmpty();
        boolean c2 = date!=null;
        boolean c3 = !ncc.isEmpty();
        boolean prevHas = false;

        if(c1){
            sql+= "where idStaff = '"+staff+"' ";
            prevHas = true;
        }
        if(c2){
            java.sql.Date dateSql = new java.sql.Date(date.getTime());
            if(prevHas) sql += " and date_create = '"+dateSql+"' ";
            else{
                sql += " where date_create = '"+dateSql+"' ";
                prevHas = true;
            }

        }
        if(c3){
            if(prevHas) sql += " and ncc = '"+ncc+"' ";
            else {
                sql += " where ncc = '"+ncc+"' ";
            }
        }

        List<BillIn> bills = new ArrayList<>();
        try {
            ResultSet rs =  _db.ExcuteQueryGetTable(sql);
            while (rs.next()){
                BillIn bill = new BillIn(
                        rs.getString("idBill"),
                        rs.getString("idStaff"),
                        rs.getInt("Total"),
                        rs.getDate("date_create"),
                        rs.getString("note"),
                        rs.getString("ncc")
                );
                bills.add(bill);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            _db.Close();
        }
        return bills;
    }
}
