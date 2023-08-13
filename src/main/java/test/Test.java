package test;

import Model.BillIn;
import Model.Supplier;
import Services.BillInService;
import Services.SupplierService;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Test {
    public static void main(String[] args){

        try {
            UUID uuid1 = UUID.randomUUID();
            System.out.println("UUID 1: " + uuid1);

            Date date = new Date(2023,02,01);
            BillInService service = new BillInService();
             List<BillIn> billIns = service.GetByCondition("",date,"");
            System.out.println(billIns);

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
