package bitrix.entity;

import javax.persistence.*;

@Entity
@Table(name = "b_iblock_element_prop_s79")
public class Residential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IBLOCK_ELEMENT_ID")
    private Integer IBLOCK_ELEMENT_ID;

    @Column(name = "PROPERTY_1120")
    private String PROPERTY_1120;

    @Column(name = "PROPERTY_1121")
    private String PROPERTY_1121;

    @Column(name = "PROPERTY_1122")
    private String PROPERTY_1122;

    @Column(name = "PROPERTY_1123")
    private String PROPERTY_1123;

    @Column(name = "PROPERTY_1124")
    private String PROPERTY_1124;

    @Column(name = "PROPERTY_1125")
    private Integer PROPERTY_1125;

    @Column(name = "PROPERTY_1126")
    private String PROPERTY_1126;

    @Column(name = "PROPERTY_1127")
    private Integer PROPERTY_1127;

    @Column(name = "PROPERTY_1128")
    private Integer PROPERTY_1128;

    @Column(name = "PROPERTY_1129")
    private String PROPERTY_1129;

    @Column(name = "PROPERTY_1130")
    private String PROPERTY_1130;

    @Column(name = "PROPERTY_1131")
    private String PROPERTY_1131;

    @Column(name = "PROPERTY_1132")
    private String PROPERTY_1132;

    @Column(name = "PROPERTY_1133")
    private String PROPERTY_1133;

    @Column(name = "PROPERTY_1134")
    private String PROPERTY_1134;

    @Column(name = "PROPERTY_1143")
    private String PROPERTY_1143;

    @Column(name = "PROPERTY_1144")
    private String PROPERTY_1144;

    @Column(name = "PROPERTY_1145")
    private String PROPERTY_1145;

    @Column(name = "PROPERTY_1146")
    private String PROPERTY_1146;

    public Residential() {
        this.PROPERTY_1125 = 0;
        this.PROPERTY_1127 = 0;
        this.PROPERTY_1128 = 0;
    }

    public Integer getIBLOCK_ELEMENT_ID() {
        return IBLOCK_ELEMENT_ID;
    }

    public void setIBLOCK_ELEMENT_ID(Integer IBLOCK_ELEMENT_ID) {
        this.IBLOCK_ELEMENT_ID = IBLOCK_ELEMENT_ID;
    }

    public String getPROPERTY_1120() {
        return PROPERTY_1120;
    }

    public void setPROPERTY_1120(String PROPERTY_1120) {
        this.PROPERTY_1120 = PROPERTY_1120;
    }

    public String getPROPERTY_1121() {
        return PROPERTY_1121;
    }

    public void setPROPERTY_1121(String PROPERTY_1121) {
        this.PROPERTY_1121 = PROPERTY_1121;
    }

    public String getPROPERTY_1122() {
        return PROPERTY_1122;
    }

    public void setPROPERTY_1122(String PROPERTY_1122) {
        this.PROPERTY_1122 = PROPERTY_1122;
    }

    public String getPROPERTY_1123() {
        return PROPERTY_1123;
    }

    public void setPROPERTY_1123(String PROPERTY_1123) {
        this.PROPERTY_1123 = PROPERTY_1123;
    }

    public String getPROPERTY_1124() {
        return PROPERTY_1124;
    }

    public void setPROPERTY_1124(String PROPERTY_1124) {
        this.PROPERTY_1124 = PROPERTY_1124;
    }

    public Integer getPROPERTY_1125() {
        return PROPERTY_1125;
    }

    public void setPROPERTY_1125(Integer PROPERTY_1125) {
        this.PROPERTY_1125 = PROPERTY_1125;
    }

    public String getPROPERTY_1126() {
        return PROPERTY_1126;
    }

    public void setPROPERTY_1126(String PROPERTY_1126) {
        this.PROPERTY_1126 = PROPERTY_1126;
    }

    public Integer getPROPERTY_1127() {
        return PROPERTY_1127;
    }

    public void setPROPERTY_1127(Integer PROPERTY_1127) {
        this.PROPERTY_1127 = PROPERTY_1127;
    }

    public Integer getPROPERTY_1128() {
        return PROPERTY_1128;
    }

    public void setPROPERTY_1128(Integer PROPERTY_1128) {
        this.PROPERTY_1128 = PROPERTY_1128;
    }

    public String getPROPERTY_1129() {
        return PROPERTY_1129;
    }

    public void setPROPERTY_1129(String PROPERTY_1129) {
        this.PROPERTY_1129 = PROPERTY_1129;
    }

    public String getPROPERTY_1130() {
        return PROPERTY_1130;
    }

    public void setPROPERTY_1130(String PROPERTY_1130) {
        this.PROPERTY_1130 = PROPERTY_1130;
    }

    public String getPROPERTY_1131() {
        return PROPERTY_1131;
    }

    public void setPROPERTY_1131(String PROPERTY_1131) {
        this.PROPERTY_1131 = PROPERTY_1131;
    }

    public String getPROPERTY_1132() {
        return PROPERTY_1132;
    }

    public void setPROPERTY_1132(String PROPERTY_1132) {
        this.PROPERTY_1132 = PROPERTY_1132;
    }

    public String getPROPERTY_1133() {
        return PROPERTY_1133;
    }

    public void setPROPERTY_1133(String PROPERTY_1133) {
        this.PROPERTY_1133 = PROPERTY_1133;
    }

    public String getPROPERTY_1134() {
        return PROPERTY_1134;
    }

    public void setPROPERTY_1134(String PROPERTY_1134) {
        this.PROPERTY_1134 = PROPERTY_1134;
    }

    public String getPROPERTY_1143() {
        return PROPERTY_1143;
    }

    public void setPROPERTY_1143(String PROPERTY_1143) {
        this.PROPERTY_1143 = PROPERTY_1143;
    }

    public String getPROPERTY_1144() {
        return PROPERTY_1144;
    }

    public void setPROPERTY_1144(String PROPERTY_1144) {
        this.PROPERTY_1144 = PROPERTY_1144;
    }

    public String getPROPERTY_1145() {
        return PROPERTY_1145;
    }

    public void setPROPERTY_1145(String PROPERTY_1145) {
        this.PROPERTY_1145 = PROPERTY_1145;
    }

    public String getPROPERTY_1146() {
        return PROPERTY_1146;
    }

    public void setPROPERTY_1146(String PROPERTY_1146) {
        this.PROPERTY_1146 = PROPERTY_1146;
    }
}
