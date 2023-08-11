package test;

import Model.Supplier;
import Services.SupplierService;

import java.util.List;
import java.util.UUID;

public class Test {
    public static void main(String[] args){

        try {
            UUID uuid1 = UUID.randomUUID();
            System.out.println("UUID 1: " + uuid1);

            // Tạo một UUID từ một chuỗi
            String uuidString = "550e8400-e29b-41d4-a716-446655440000";
            UUID uuid2 = UUID.fromString(uuidString);
            System.out.println("UUID 2: " + uuid2);

        }
        catch (Exception ex){

        }
    }
}
