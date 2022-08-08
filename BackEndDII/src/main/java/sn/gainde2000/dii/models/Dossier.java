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
public class Dossier implements Serializable {
    @Id
    private String numero;
    private String email;
    private String telephone;
    private boolean paye = false;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Temporal(TemporalType.DATE)
    private Date datePaiement;
    private String numeroChassis;

}
