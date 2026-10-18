package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Reservation;
import java.util.List;

public interface ReservationService {
    Reservation ajouterReservation(Reservation reservation);
    Reservation modifierReservation(Reservation reservation);
    void supprimerReservation(Long idReservation);
    Reservation recupererReservation(Long idReservation);
    List<Reservation> recupererToutesLesReservations();
}
