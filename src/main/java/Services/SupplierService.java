package Services;

import Data.Data;
import Model.Supplier;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierService {
    String getAllSql = "select * from supplier ";

    public  List<Supplier> getAll() {
        Data db = new Data();
        ResultSet rs = db.ExcuteQueryGetTable(getAllSql);
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

       }
       return suppliers;
    }
}
