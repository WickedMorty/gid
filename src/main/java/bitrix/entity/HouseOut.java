package bitrix.entity;

import java.util.List;

public class HouseOut {
    private Integer id;
    private String name;
    private Integer floor;
    private String deadline;
    private String decoration;
    private String material;
    private List<String> payment;
    private List<String> bank;

    public HouseOut() {
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

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<String> getPayment() {
        return payment;
    }

    public void setPayment(List<String> payment) {
        this.payment = payment;
    }

    public List<String> getBank() {
        return bank;
    }

    public void setBank(List<String> bank) {
        this.bank = bank;
    }
}
