package rest.entity.controller;

public class UserAuthResponse {
    private String email; //ГидПоНовостройкам
    private String name; //ГидПоНовостройкам
    private String mobile; //ГидПоНовостройкам
    private String department; //ГидПоНовостройкам

    public UserAuthResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
