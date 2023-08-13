package Model;

import Data.Data;

import java.sql.Date;

public class MechidenAdd {
    String mathuoc;
    String tenthuoc;
    String solo;
    Date date;
    int soluong;
    int tinhTien;
    public MechidenAdd(String mathuoc,String tenthuoc, int soluong,int tinhTien) {
        this.mathuoc = mathuoc;
        this.tenthuoc = tenthuoc;
        this.soluong = soluong;
        this.tinhTien = tinhTien;
    }
    public MechidenAdd(String mathuoc,String tenthuoc, int soluong,int tinhTien, Date date,String solo) {
        this.mathuoc = mathuoc;
        this.tenthuoc = tenthuoc;
        this.soluong = soluong;
        this.tinhTien = tinhTien;
        this.date = date;
        this.solo = solo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getMathuoc() {
        return mathuoc;
    }

    public String getSolo() {
        return solo;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public int getTinhTien() {
        return tinhTien;
    }

    public void setMathuoc(String mathuoc) {
        this.mathuoc = mathuoc;
    }

    public void setSolo(String solo) {
        this.solo = solo;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public void setTinhTien(int tinhTien) {
        this.tinhTien = tinhTien;
    }

}
