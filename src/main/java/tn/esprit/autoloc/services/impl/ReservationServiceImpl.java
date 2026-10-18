package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repositories.ReservationRepository;
import tn.esprit.autoloc.services.ReservationService;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Override
    public Reservation modifierReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Override
    public void supprimerReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }
    @Override
    public Reservation recupererReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }
    @Override
    public List<Reservation> recupererToutesLesReservations() {
        return reservationRepository.findAll();
    }
}
