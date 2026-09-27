package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Agence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "agence")
    @Builder.Default private List<Employe> employes = new ArrayList<>();

    @OneToMany(mappedBy = "agence")
    @Builder.Default private List<Vehicule> vehicules = new ArrayList<>();
}
