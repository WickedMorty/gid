package bitrix.entity;

import javax.persistence.*;

@Entity
@Table(name = "b_iblock_element")
public class Iblock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PREVIEW_PICTURE")
    private Integer previewPicture;

    @Column(name = "PREVIEW_TEXT")
    private String previewText;

    @Column(name = "DETAIL_PICTURE")
    private Integer detailPicture;

    @Column(name = "DETAIL_TEXT")
    private String detailText;

    public Iblock() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPreviewPicture() {
        return previewPicture;
    }

    public void setPreviewPicture(Integer previewPicture) {
        this.previewPicture = previewPicture;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public Integer getDetailPicture() {
        return detailPicture;
    }

    public void setDetailPicture(Integer detailPicture) {
        this.detailPicture = detailPicture;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }
}
