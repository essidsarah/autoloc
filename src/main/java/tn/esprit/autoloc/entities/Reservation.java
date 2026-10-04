package tn.esprit.autoloc.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.autoloc.enums.StatutReservation;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING) private StatutReservation statut;

    @ManyToOne(optional = false) @JoinColumn(name = "client_id") private Client client;
    @ManyToOne(optional = false) @JoinColumn(name = "vehicule_id") private Vehicule vehicule;

    @OneToOne(mappedBy = "reservation")
    private Contrat contrat;
}
