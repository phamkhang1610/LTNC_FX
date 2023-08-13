package Data;

import Model.Bill;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


//            up_select.setVisible(true);
//                    add_btn_me.setVisible(false);
//                    idMe.setText(getData.medicine.getIdMe());
//                    nameMe.setText(getData.medicine.getNameMe());
//                    location.setText(getData.medicine.getLocation());
//                    price.setText(String.valueOf(getData.medicine.getPrice()));
//                    note.setText(getData.medicine.getNote());
//                    quantity.setText(String.valueOf(getData.medicine.getQuantity()));
//                    id_up_supme.setText(getData.medicine.getIdSup());
//                    image = new Image(getData.medicine.getImage().toString(),167,190,false,true);
//                    image_Me.setImage(image);
//                    getData.path = getData.medicine.getImage();
public class Test {
    public static void chartBill(Date from, Date to){
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

        // return list;
    }
    public static void main(String[] args) {
        Date from = Date.valueOf("2023-08-03");
        Date to = Date.valueOf("2023-08-10");
        chartBill(from,to);
    }

}
