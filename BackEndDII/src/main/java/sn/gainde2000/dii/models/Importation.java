package sn.gainde2000.dii.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Importation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(columnDefinition = "integer default 0")
    private int status;
    @Column(nullable = true)
    private String codeppm;
    @Column(nullable = true, length = 20)
    private String ninea;
    @Column(nullable = false, length = 250)
    private String emailImportateur;
    private String telephoneImportateur;
    private String adresseImportateur;
    private String raisonSocialeImportateur;
    private String raisonSocialeVendeur;
    private String adresseVendeur;
    private String telephoneVendeur;
    private String paysProvenance;
    @Column(nullable = true, length = 250)
    private String emailVendeur;
    private String numeroProforma;
    @Temporal(TemporalType.DATE)
    private Date dateProforma;
    @Column(name = "devise", nullable = true)
    private String devise;
    private Long valeurFacture;
    private String contreValeur;
    private String provenance;
    @Column(length = 500)
    private String observationsParticulieres;
    private String creationCompte;
    @Column(nullable = false, length = 100, unique = true)
    private String numero;
    private int idInvoice;
    @OneToMany(mappedBy = "importation")
    private List<Marchandise> marchandise;
    private String modePaiement;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "utlilisateur_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Utilisateur utilisateur;
    @Column(name = "dii", columnDefinition = "MEDIUMBLOB")
    private byte[] dii;
    @JsonIgnore
    private String document_1;
    @JsonIgnore
    private String document_2;
    @JsonIgnore
    private String document_3;
    @JsonIgnore
    private String document_4;
    @JsonIgnore
    private String document_5;
}
