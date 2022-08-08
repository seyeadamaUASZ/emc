package sn.gainde2000.dii.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Project: UploadFileProject
 * Package: com.openumeric.upload
 * User: Ilo
 * Date: 02/09/2021
 * Time: 18:24
 * Created with IntelliJ IDEA
 */
@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;
    private Long importationId;

    @Lob
    private byte[] data;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data, Long id) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.importationId = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getImportationId() {
        return importationId;
    }

    public void setImportationId(Long importationId) {
        this.importationId = importationId;
    }
}