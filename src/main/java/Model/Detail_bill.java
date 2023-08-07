package Model;

public class Detail_bill {
    private String idBill;
    private String idMe;
    private String nameMe;
    private int size;
    private int money;
    public Detail_bill(){}
    public Detail_bill(String idBill, String idMe, String nameMe,int size, int money){
         this.idBill = idBill;
         this.idMe = idMe;
         this.nameMe = nameMe;
         this.size = size;
         this.money = money;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setIdMe(String idMe) {
        this.idMe = idMe;
    }

    public void setNameMe(String nameMe) {
        this.nameMe = nameMe;
    }

    public String getNameMe() {
        return nameMe;
    }

    public String getIdMe() {
        return idMe;
    }

    public int getMoney() {
        return money;
    }

    public int getSize() {
        return size;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
