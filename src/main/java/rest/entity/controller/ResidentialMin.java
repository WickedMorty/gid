package rest.entity.controller;

import bitrix.entity.ResidentialOut;
import rest.entity.ResidentialCache;

public class ResidentialMin {
    private Integer id;
    private String name;
    private String district;
    private String developer;
    private String comfort;
    private String image;
    private String deadline;
    private boolean form1 = false;
    private boolean form2 = false;
    private boolean contact = false;

    private boolean checkString(String value) {
        if(value == null) {
            return false;
        }

        if(value.isEmpty()) {
            return false;
        }

        return true;
    }

    public ResidentialMin(ResidentialCache residential) {
        id = residential.getId();
        name = residential.getName();
        district = residential.getDistrict().getName();
        developer = residential.getDeveloper().getName();
        comfort = residential.getComfort().getName();
        image = residential.getImage().toString();
        deadline = residential.getDeadline();
        contact = checkString(residential.getDetailText());
        form1 = checkString(residential.getForm1());
        form2 = checkString(residential.getForm2());
    }

    public boolean isForm1() {
        return form1;
    }

    public void setForm1(boolean form1) {
        this.form1 = form1;
    }

    public boolean isForm2() {
        return form2;
    }

    public void setForm2(boolean form2) {
        this.form2 = form2;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
