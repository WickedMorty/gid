package bitrix.entity;

import javax.persistence.*;

@Entity
@Table(name = "b_iblock_element_prop_s80")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IBLOCK_ELEMENT_ID")
    private Integer id;

    @Column(name = "PROPERTY_1135")
    private Integer PROPERTY_1135;

    @Column(name = "PROPERTY_1136")
    private String PROPERTY_1136;

    @Column(name = "PROPERTY_1137")
    private Integer PROPERTY_1137;

    @Column(name = "PROPERTY_1138")
    private String PROPERTY_1138;

    @Column(name = "PROPERTY_1139")
    private String PROPERTY_1139;

    @Column(name = "PROPERTY_1140")
    private String PROPERTY_1140;

    @Column(name = "PROPERTY_1141")
    private String PROPERTY_1141;

    @Column(name = "PROPERTY_1142")
    private String PROPERTY_1142;

    public House() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPROPERTY_1137() {
        return PROPERTY_1137;
    }

    public void setPROPERTY_1137(Integer PROPERTY_1137) {
        this.PROPERTY_1137 = PROPERTY_1137;
    }

    public String getPROPERTY_1138() {
        return PROPERTY_1138;
    }

    public void setPROPERTY_1138(String PROPERTY_1138) {
        this.PROPERTY_1138 = PROPERTY_1138;
    }

    public Integer getPROPERTY_1135() {
        return PROPERTY_1135;
    }

    public void setPROPERTY_1135(Integer PROPERTY_1135) {
        this.PROPERTY_1135 = PROPERTY_1135;
    }

    public String getPROPERTY_1136() {
        return PROPERTY_1136;
    }

    public void setPROPERTY_1136(String PROPERTY_1136) {
        this.PROPERTY_1136 = PROPERTY_1136;
    }

    public String getPROPERTY_1139() {
        return PROPERTY_1139;
    }

    public void setPROPERTY_1139(String PROPERTY_1139) {
        this.PROPERTY_1139 = PROPERTY_1139;
    }

    public String getPROPERTY_1141() {
        return PROPERTY_1141;
    }

    public void setPROPERTY_1141(String PROPERTY_1141) {
        this.PROPERTY_1141 = PROPERTY_1141;
    }

    public String getPROPERTY_1142() {
        return PROPERTY_1142;
    }

    public void setPROPERTY_1142(String PROPERTY_1142) {
        this.PROPERTY_1142 = PROPERTY_1142;
    }

    public String getPROPERTY_1140() {
        return PROPERTY_1140;
    }

    public void setPROPERTY_1140(String PROPERTY_1140) {
        this.PROPERTY_1140 = PROPERTY_1140;
    }
}
