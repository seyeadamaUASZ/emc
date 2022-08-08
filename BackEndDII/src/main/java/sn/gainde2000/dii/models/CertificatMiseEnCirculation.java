package sn.gainde2000.dii.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CertificatMiseEnCirculation implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String numeroOrdre;
    private String marque;
    private String genre;
    private String type;
    private String numeroSerieType;
    private String numeroImmatriculation;
    @Temporal(TemporalType.DATE)
    private Date dateMiseEnCirculation;
    private String puissanceFiscaleOrigine;
    private String puissanceFiscaleCorrige1;
    private String droitTaxeAcquitte;
    private String droitTaxeExonore;
    private String enregistreLe;
    private String numeroEnregistrement;
    private String compteDe;
    private String numeroChassis;

}
