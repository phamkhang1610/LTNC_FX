package Model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Staff implements Serializable{
    private String idStaff;
    private String nameStaff;
    private String cmnd;
    private String sexStaff;
    private String addresStaff;
    private Date ngaysinh;
    private String phoneStaff;
    private String image;

    public Staff() {
    }

    public Staff(String idStaff, String nameStaff, String cmnd, String sexStaff, String addresStaff, Date ngaysinh, String phoneStaff,String image) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.cmnd = cmnd;
        this.sexStaff = sexStaff;
        this.addresStaff = addresStaff;
        this.ngaysinh = ngaysinh;
        this.phoneStaff = phoneStaff;
        this.image = image;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }



    public String getIdStaff() {
        return idStaff;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public String getSexStaff() {
        return sexStaff;
    }

    public String getAddresStaff() {
        return addresStaff;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public String getPhoneStaff() {
        return phoneStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public void setSexStaff(String sexStaff) {
        this.sexStaff = sexStaff;
    }

    public void setAddresStaff(String addresStaff) {
        this.addresStaff = addresStaff;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setPhoneStaff(String phoneStaff) {
        this.phoneStaff = phoneStaff;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}

