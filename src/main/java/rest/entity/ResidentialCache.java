package rest.entity;

import bitrix.entity.House;
import bitrix.entity.HouseOut;
import bitrix.entity.IFile;
import bitrix.entity.Residential;
import nar.entity.Apartment;
import org.springframework.web.util.HtmlUtils;
import rest.entity.controller.CustomParameter;
import rest.entity.controller.ResidentialMin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ResidentialCache {
    private Integer id;
    private String name;
    private String coordinates;
    private String address;
    private CustomParameter district = new CustomParameter();
    private CustomParameter developer = new CustomParameter();
    private CustomParameter subway = new CustomParameter();
    private Integer subwayMin;
    private String bus;
    private Integer busMin;
    private Integer parking;
    private CustomParameter comfort = new CustomParameter();
    private List<CustomParameter> externals = new ArrayList<>();
    private List<CustomParameter> internals = new ArrayList<>();
    private List<CustomParameter> ecologicals = new ArrayList<>();
    private List<CustomParameter> securities = new ArrayList<>();
    private List<CustomParameter> accomplishments = new ArrayList<>();
    private IFile image;
    private IFile imageBig;
    private String previewText;
    private String detailText;
    private String form1;
    private String form2;
    private String percent;
    private String files;
    private List<HouseCache> houses = new ArrayList<>();
    private List<Apartment> apartments = new ArrayList<>();

    public ResidentialCache() {
    }

    public ResidentialCache(Residential residential) {
        setId(residential.getIBLOCK_ELEMENT_ID());
        setAddress(residential.getPROPERTY_1121());
        setCoordinates(residential.getPROPERTY_1120());
        setForm1(residential.getPROPERTY_1143());
        setForm2(residential.getPROPERTY_1144());
        setSubwayMin(residential.getPROPERTY_1125());
        setBus(residential.getPROPERTY_1126());
        setBusMin(residential.getPROPERTY_1127());
        setParking(residential.getPROPERTY_1128());
        if(residential.getPROPERTY_1145() != null) {
            setPercent(residential.getPROPERTY_1145().replaceAll("^(.*)\"TEXT\";s:(\\d*):\"", "").replaceAll("\";s:(\\d*):\"TYPE\"(.*)$", ""));
        }
        if(residential.getPROPERTY_1146() != null) {
            setFiles(residential.getPROPERTY_1146().replaceAll("^(.*)\"TEXT\";s:(\\d*):\"", "").replaceAll("\";s:(\\d*):\"TYPE\"(.*)$", ""));
        }
    }

    public String getDeadline() {
        String res = "none";
        Integer minId = 100000;
        for(HouseCache house: houses) {
            if(house.getDeadline().getId() < minId) {
                res = house.getDeadline().getName();
                minId = house.getDeadline().getId();
            }
        }

        return res;
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

    public CustomParameter getDistrict() {
        if(district == null) {
            return new CustomParameter();
        }
        return district;
    }

    public void setDistrict(CustomParameter district) {
        this.district = district;
    }

    public CustomParameter getDeveloper() {
        return developer;
    }

    public void setDeveloper(CustomParameter developer) {
        this.developer = developer;
    }

    public CustomParameter getSubway() {
        if(subway == null) {
            return new CustomParameter();
        }
        return subway;
    }

    public void setSubway(CustomParameter subway) {
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

    public CustomParameter getComfort() {
        return comfort;
    }

    public void setComfort(CustomParameter comfort) {
        this.comfort = comfort;
    }

    public List<CustomParameter> getExternals() {
        return externals;
    }

    public void setExternals(List<CustomParameter> externals) {
        this.externals = externals;
    }

    public List<CustomParameter> getInternals() {
        return internals;
    }

    public void setInternals(List<CustomParameter> internals) {
        this.internals = internals;
    }

    public List<CustomParameter> getEcologicals() {
        return ecologicals;
    }

    public void setEcologicals(List<CustomParameter> ecologicals) {
        this.ecologicals = ecologicals;
    }

    public List<CustomParameter> getSecurities() {
        return securities;
    }

    public void setSecurities(List<CustomParameter> securities) {
        this.securities = securities;
    }

    public List<CustomParameter> getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(List<CustomParameter> accomplishments) {
        this.accomplishments = accomplishments;
    }

    public IFile getImage() {
        return image;
    }

    public void setImage(IFile image) {
        try {
            image.setName(URLEncoder.encode(image.getName(), "UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.image = image;
    }

    public IFile getImageBig() {
        return imageBig;
    }

    public void setImageBig(IFile imageBig) {
        try {
            imageBig.setName(URLEncoder.encode(imageBig.getName(), "UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.imageBig = imageBig;
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

    public List<HouseCache> getHouses() {
        return houses;
    }

    public void setHouses(List<HouseCache> houses) {
        this.houses = houses;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
