package Model;

public class Type {
    private String idType;
    private String nameType;
    private String position;
    private String describe;
    public Type(){}
    public Type(String id, String name, String position, String describe){
        this.idType = id;
        this.nameType = name;
        this.position = position;
        this.describe = describe;
    }

    public String getIdType() {
        return idType;
    }
    public String getNameType() {
        return nameType;
    }

    public String getPosition() {
        return position;
    }

    public String getDescribe() {
        return describe;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
