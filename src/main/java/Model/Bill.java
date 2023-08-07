package Model;

import java.sql.Date;

public class Bill {
    private String idBill;
    private String idStaff;
    private int total;
    private Date date;
    private String note;
    public Bill(){}
    public Bill(String idBill, String idStaff, int total, Date date, String note){
        this.idBill = idBill;
        this.idStaff = idStaff;
        this.total = total;
        this.date = date;
        this.note = note;
    }

    public String getIdBill() {
        return idBill;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public int getTotal() {
        return total;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
