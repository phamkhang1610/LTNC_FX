package Model;

public class BillInDetail {
    String ID_bill;
    String idMe;
    int quantity;
    String money;
    String nameMedi;
    public BillInDetail(String id, String idme,int quantity, String money,String nameMedi){
        this.ID_bill = id;
        this.idMe = idme;
        this.quantity = quantity;
        this.money = money;
        this.nameMedi = nameMedi;
    }

    public String getID_bill() {
        return ID_bill;
    }

    public void setID_bill(String ID_bill) {
        this.ID_bill = ID_bill;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIdMe() {
        return idMe;
    }

    public String getMoney() {
        return money;
    }

    public String getNameMedi() {
        return nameMedi;
    }

    public void setIdMe(String idMe) {
        this.idMe = idMe;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setNameMedi(String nameMedi) {
        this.nameMedi = nameMedi;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
