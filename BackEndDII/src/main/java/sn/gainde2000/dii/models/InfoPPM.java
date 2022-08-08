package sn.gainde2000.dii.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.models
 * User: Ilo
 * Date: 31/08/2021
 * Time: 10:28
 * Created with IntelliJ IDEA
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "info_ppm")
public class InfoPPM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = true)
    private String codeppm;
    @Column(nullable = true, unique = true, length = 20)
    private String ninea;
    @Column(nullable = false, length = 250, unique = true)
    private String email;
    private String telephone;
    private String adresse;
    private String raisonSociale;


}
