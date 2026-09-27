package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;
    @Column(unique = true, nullable = false) private String immatriculation;
    private String marque;
    private String modele;
    @Enumerated(EnumType.STRING) private CategorieVehicule categorie;
    private BigDecimal tarifJournalier;
    @Enumerated(EnumType.STRING) private StatutVehicule statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "agence_id")
    private Agence agence;

    @OneToMany(mappedBy = "vehicule")
    @Builder.Default private List<Maintenance> maintenances = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id"))
    @Builder.Default private List<Equipement> equipements = new ArrayList<>();

    @OneToMany(mappedBy = "vehicule")
    @Builder.Default private List<Reservation> reservations = new ArrayList<>();
}
