package Model;

public class Account {
    public String idStaff;
    public String username;
    public  String password;
    public String role;
    public Account(){}
    public Account(String idStaff,String username,String password, String role){
        this.idStaff = idStaff;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
