package Model;

import java.sql.Date;
import java.util.StringTokenizer;

public class Detail_Me {
    private String idMe;
    private String id_lo;
    private String idSup;
    private int quantity;
    private Date expiry;
    public Detail_Me(){}
    public Detail_Me(String idMe, String id_lo, String idSup, int quan, Date ex){
        this.idMe = idMe;
        this.id_lo = id_lo;
        this.idSup = idSup;
        this.quantity = quan;
        this.expiry = ex;
    }
    public String getIdMe() {
        return idMe;
    }
    public String getId_lo() {
        return id_lo;
    }
    public String getIdSup() {
        return idSup;
    }
    public int getQuantity() {
        return quantity;
    }
    public Date getExpiry() {
        return expiry;
    }

    public void setIdMe(String idMe) {
        this.idMe = idMe;
    }
    public void setId_lo(String id_lo) {
        this.id_lo = id_lo;
    }
    public void setIdSup(String idSup) {
        this.idSup = idSup;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
}
