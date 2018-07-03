package rest.entity;

import bitrix.entity.House;
import nar.entity.Apartment;
import rest.entity.controller.CustomParameter;

import java.util.ArrayList;
import java.util.List;

public class HouseCache {
    private Integer id;
    private String name;
    private Integer floor;
    private String address;
    private CustomParameter deadline = new CustomParameter();
    private CustomParameter decoration = new CustomParameter();
    private CustomParameter material = new CustomParameter();
    private List<CustomParameter> payment = new ArrayList<>();
    private List<CustomParameter> bank = new ArrayList<>();

    public HouseCache() {
    }

    public HouseCache(House house) {
        setId(house.getId());
        setAddress(house.getPROPERTY_1136());
        setFloor(house.getPROPERTY_1137());
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

    public CustomParameter getDeadline() {
        return deadline;
    }

    public void setDeadline(CustomParameter deadline) {
        this.deadline = deadline;
    }

    public CustomParameter getDecoration() {
        return decoration;
    }

    public void setDecoration(CustomParameter decoration) {
        this.decoration = decoration;
    }

    public CustomParameter getMaterial() {
        return material;
    }

    public void setMaterial(CustomParameter material) {
        this.material = material;
    }

    public List<CustomParameter> getPayment() {
        return payment;
    }

    public void setPayment(List<CustomParameter> payment) {
        this.payment = payment;
    }

    public List<CustomParameter> getBank() {
        return bank;
    }

    public void setBank(List<CustomParameter> bank) {
        this.bank = bank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String banksToString() {
        String ret = "";
        for(CustomParameter cp: bank) {
            ret = ret.concat(cp.getName() + ";");
        }
        return ret;
    }
}
