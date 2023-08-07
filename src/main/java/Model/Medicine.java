package Model;

import java.sql.Date;

public class Medicine {
    private String idMe;
    private String nameMe;
    private String idSup;
    private String idType;
    private int quantity;
    private int price;
    private String location;
    private String image;
    private Date expiry;
    private String note;
    public Medicine(){}
    public Medicine(String id, String name, String idSup,
                    String idType, int quantity, int price, String loction,
                    String image, Date date, String note){
        this.idMe = id; this.nameMe = name; this.idSup = idSup;
        this.idType = idType; this.quantity =quantity; this.price = price;
        this.location = loction; this.image = image; this.expiry = date;
        this.note = note;
    }

    public String getIdMe() {
        return idMe;
    }
    public String getIdType() {
        return idType;
    }

    public String getIdSup() {
        return idSup;
    }

    public String getNameMe() {
        return nameMe;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public String getImage() {
        return image;
    }
    public Date getExpiry() {
        return expiry;
    }
    public String getLocation() {
        return location;
    }
    public String getNote() {
        return note;
    }
    public void setIdMe(String idMe) {
        this.idMe = idMe;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public void setIdSup(String idSup) {
        this.idSup = idSup;
    }
    public void setNameMe(String nameMe) {
        this.nameMe = nameMe;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setLocation(String loction) {
        this.location = loction;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
