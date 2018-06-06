package bitrix.entity.param;

import javax.persistence.*;

@Entity
@Table(name = "banks")
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "UF_NAME")
    private String name;

    @Column(name = "UF_XML_ID")
    private String xmlId;

    public Banks() {
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

    public String getXmlId() {
        return xmlId;
    }

    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }
}
