package sn.gainde2000.dii.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profil")
public class Profil implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    public Profil(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
    public Profil(String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
    public String getLibelle() {
    	return this.libelle;
    }
    
}
