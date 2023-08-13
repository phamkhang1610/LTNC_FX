package Model;

import java.sql.Date;

public class BillIn {
     String idBill;
     String idStaff;
     int total;
     Date date;
     String note;
     String ncc;

    public BillIn(String idBill,String idStaff, int total, Date date, String note, String ncc){
        this.idBill = idBill;
        this.idStaff = idStaff;
        this.total = total;
        this.date = date;
        this.ncc = ncc;
        this.note = note;
    }

    public String getIdBill() {
        return idBill;
    }

    public Date getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public String getNcc() {
        return ncc;
    }

    public String getNote() {
        return note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
