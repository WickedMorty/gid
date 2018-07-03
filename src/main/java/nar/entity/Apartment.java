package nar.entity;

public class Apartment {
    private String building;
    private String address;
    private String floor;
    private String image;
    private String rooms;
    private String area;
    private String price;

    public Apartment() {
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "developer='" + building + '\'' +
                ", address='" + address + '\'' +
                ", floor='" + floor + '\'' +
                ", image='" + image + '\'' +
                ", rooms='" + rooms + '\'' +
                ", area='" + area + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
