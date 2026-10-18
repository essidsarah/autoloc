package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repositories.ReservationRepository;
import tn.esprit.autoloc.repositories.ClientRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;
import tn.esprit.autoloc.services.ReservationService;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientRepository clientRepository, VehiculeRepository vehiculeRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
    }
    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        reservation.setClient(clientRepository.getReferenceById(reservation.getClient().getIdClient()));
        reservation.setVehicule(vehiculeRepository.getReferenceById(reservation.getVehicule().getIdVehicule()));
        return reservationRepository.save(reservation);
    }
    @Override
    public Reservation modifierReservation(Reservation reservation) {
        reservation.setClient(clientRepository.getReferenceById(reservation.getClient().getIdClient()));
        reservation.setVehicule(vehiculeRepository.getReferenceById(reservation.getVehicule().getIdVehicule()));
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
