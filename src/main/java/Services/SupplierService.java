package Services;

import Data.Data;
import Model.Supplier;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierService {
    String getAllSql = "select * from supplier ";
     Data _db;
    public SupplierService(){
        _db = new Data();
    }

    public  List<Supplier> getAll() {

        ResultSet rs = _db.ExcuteQueryGetTable(getAllSql);
        List<Supplier> suppliers = new ArrayList<>();
       try {
           while (rs.next()){
               Supplier sup = new Supplier(
                       rs.getString("idSup"),
                       rs.getString("nameSup"),
                       rs.getString("addressSup"),
                       rs.getString("phoneSup")
               );
               suppliers.add(sup);
           }
       }
       catch (Exception ex){
        ex.printStackTrace();
       }
       finally {
           _db.Close();
       }
       return suppliers;
    }
    public boolean upDate(String id, String name, String phone, String address){
       try{
           String sql = "UPDATE supplier " +
                   "SET nameSup = '"+name+"' , phoneSup = '"+phone+ "', addressSup =' "+address+"' " +
                   " WHERE idSup = '"+id+"'";
           _db.ExcuteQueryUpdateDB(sql);
           return true;
       }
       catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
       finally {
           _db.Close();
       }

    }
    public  boolean add(String id, String name, String phone, String address) {
        String sql = "INSERT INTO supplier (idSup,nameSup,phoneSup,addressSup ) " +
                "VALUES ('"+id+"','"+name+"','"+phone+"','"+address+"')";
        _db.ExcuteQueryUpdateDB(sql);
        _db.Close();
        return true;
    }
    public List<Supplier> getBySearch(String key){
        String sql = "select * from supplier \n " +
                " where phoneSup like '%:p_key%' or \n " +
                " nameSup like '%:p_key%' or \n " +
                " idSup like '%:p_key%' or \n " +
                " addressSup like '%:p_key%' ";
        sql = sql.replace(":p_key",key);
        List<Supplier> suppliers = new ArrayList<>();
        try {
            ResultSet rs = _db.ExcuteQueryGetTable(sql);
            while (rs.next()){
                Supplier sup = new Supplier(
                        rs.getString("idSup"),
                        rs.getString("nameSup"),
                        rs.getString("addressSup"),
                        rs.getString("phoneSup")
                );
                suppliers.add(sup);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            _db.Close();
        }
        return suppliers;
    }
}
