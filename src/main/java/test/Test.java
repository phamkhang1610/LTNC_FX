package test;

import Model.Supplier;
import Services.SupplierService;

import java.util.List;

public class Test {
    public static void main(String[] args){
        SupplierService sr = new SupplierService();
        List<Supplier> rs = sr.getAll();
        try {
            for(Supplier r : rs){
                System.out.println(r.getNameSup());
            }
        }
        catch (Exception ex){

        }
    }
}
