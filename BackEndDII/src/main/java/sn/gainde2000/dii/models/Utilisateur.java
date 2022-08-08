package sn.gainde2000.dii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateur")
@Entity
public class Utilisateur implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "statut", nullable = false)
    private Boolean Statut = true;

    @Column(name = "mail")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "numero_dii")
    private String numeroDii;

    @CreationTimestamp
    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "new_connexion", nullable = false)
    private boolean newConnexion = true;

    @ManyToOne
    @JoinColumn(name = "profil_id")
    private Profil profil;

    @OneToMany(mappedBy = "utilisateur")
    private List<Importation> importations;

    public void setProfil(Profil profil) {
    	this.profil=profil;
    }
}
