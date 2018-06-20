package bitrix.entity;

import javax.persistence.*;

@Entity
@Table(name = "b_file")
public class IFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SUBDIR")
    private String subdir;

    @Column(name = "FILE_NAME")
    private String name;

    public IFile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubdir() {
        return subdir;
    }

    public void setSubdir(String subdir) {
        this.subdir = subdir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return subdir + "/" + name;
    }
}
