package bitrix.entity;

import java.util.List;

public class ResidentialOut {
    private Integer id; // IBLOCK_ELEMENT_ID
    private String name;
    private String coordinates; // PROPERTY_1120
    private String address; // PROPERTY_1121
    private String district; // PROPERTY_1122
    private String developer; // PROPERTY_1123
    private String subway; // PROPERTY_1124

    private Integer subwayMin; // PROPERTY_1125
    private String bus; // PROPERTY_1126
    private Integer busMin; // PROPERTY_1127
    private Integer parking; // PROPERTY_1128
    private String comfort; // PROPERTY_1129
    private List<String> externals; // PROPERTY_1130
    private List<String> internals; // PROPERTY_1131
    private List<String> ecologicals; // PROPERTY_1132
    private List<String> securities; // PROPERTY_1133
    private List<String> accomplishments; // PROPERTY_1134
    private String image;
    private String imageBig;
    private String previewText;
    private String detailText;
    private String form1;
    private String form2;
    private List<HouseOut> houses;

    private String deadlin;
    private Integer floor;

    public ResidentialOut() {
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public Integer getSubwayMin() {
        return subwayMin;
    }

    public void setSubwayMin(Integer subwayMin) {
        this.subwayMin = subwayMin;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public Integer getBusMin() {
        return busMin;
    }

    public void setBusMin(Integer busMin) {
        this.busMin = busMin;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public List<String> getExternals() {
        return externals;
    }

    public void setExternals(List<String> externals) {
        this.externals = externals;
    }

    public List<String> getInternals() {
        return internals;
    }

    public void setInternals(List<String> internals) {
        this.internals = internals;
    }

    public List<String> getEcologicals() {
        return ecologicals;
    }

    public void setEcologicals(List<String> ecologicals) {
        this.ecologicals = ecologicals;
    }

    public List<String> getSecurities() {
        return securities;
    }

    public void setSecurities(List<String> securities) {
        this.securities = securities;
    }

    public List<String> getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(List<String> accomplishments) {
        this.accomplishments = accomplishments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getDeadlin() {
        return deadlin;
    }

    public void setDeadlin(String deadlin) {
        this.deadlin = deadlin;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public void toMin() {
        this.address = "";
        this.subway = "";
        this.subwayMin = 0;
        this.bus = "";
        this.busMin = 0;
        this.parking = 0;
        this.externals = null;
        this.internals = null;
        this.ecologicals = null;
        this.securities = null;
        this.accomplishments = null;
        this.imageBig = "";
        this.previewText = "";
        this.detailText = "";
    }

    public boolean search(String searchString) {

        searchString = searchString.toLowerCase();

        if(name != null) {
            if (name.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(address != null) {
            if (address.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(district != null) {
            if (district.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(developer != null) {
            if (developer.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(subway != null) {
            if (subway.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(bus != null) {
            if (bus.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(comfort != null) {
            if (comfort.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        if(externals != null) {
            for (String item : externals) {
                if (item.toLowerCase().contains(searchString)) {
                    return true;
                }
            }
        }

        if(internals != null) {
            for (String item : internals) {
                if (item.toLowerCase().contains(searchString)) {
                    return true;
                }
            }
        }

        if(ecologicals != null) {
            for (String item : ecologicals) {
                if (item.toLowerCase().contains(searchString)) {
                    return true;
                }
            }
        }

        if(securities != null) {
            for (String item : securities) {
                if (item.toLowerCase().contains(searchString)) {
                    return true;
                }
            }
        }

        if(accomplishments != null) {
            for (String item : accomplishments) {
                if (item.toLowerCase().contains(searchString)) {
                    return true;
                }
            }
        }

        if(deadlin != null) {
            if (deadlin.toLowerCase().contains(searchString)) {
                return true;
            }
        }

        for (HouseOut house: houses) {
            if(house.getDeadline() != null) {
                if (house.getDeadline().toLowerCase().contains(searchString)) {
                    return true;
                }
            }

            if(house.getDecoration() != null) {
                if (house.getDecoration().toLowerCase().contains(searchString)) {
                    return true;
                }
            }

            if(house.getMaterial() != null) {
                if (house.getMaterial().toLowerCase().contains(searchString)) {
                    return true;
                }
            }

            if(house.getPayment() != null) {
                for (String item : house.getPayment()) {
                    if (item != null) {
                        if (item.toLowerCase().contains(searchString)) {
                            return true;
                        }
                    }
                }
            }

            if(house.getBank() != null) {
                for (String item : house.getBank()) {
                    if (item != null) {
                        if (item.toLowerCase().contains(searchString)) {
                            return true;
                        }
                    }
                }
            }

        }

        return false;
    }

    public String getForm1() {
        return form1;
    }

    public void setForm1(String form1) {
        this.form1 = form1;
    }

    public String getForm2() {
        return form2;
    }

    public void setForm2(String form2) {
        this.form2 = form2;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<HouseOut> getHouses() {
        return houses;
    }

    public void setHouses(List<HouseOut> houses) {
        this.houses = houses;
    }
}
