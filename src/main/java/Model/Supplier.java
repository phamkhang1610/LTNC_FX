package Model;

public class Supplier {
    private String idSup;
    private String nameSup;
    private String addresSup;
    private String sdtSup;
    public Supplier(){}
    public Supplier(String id, String name, String address, String sdt){
        this.idSup = id;
        this.nameSup = name;
        this.addresSup = address;
        this.sdtSup  = sdt;
    }

    public String getIdSup() {
        return idSup;
    }

    public String getNameSup() {
        return nameSup;
    }

    public String getAddresSup() {
        return addresSup;
    }

    public String getSdtSup() {
        return sdtSup;
    }

    public void setIdSup(String idSup) {
        this.idSup = idSup;
    }

    public void setNameSup(String nameSup) {
        this.nameSup = nameSup;
    }

    public void setAddresSup(String addresSup) {
        this.addresSup = addresSup;
    }

    public void setSdtSup(String sdtSup) {
        this.sdtSup = sdtSup;
    }
}
