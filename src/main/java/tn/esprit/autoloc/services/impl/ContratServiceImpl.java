package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.repositories.ContratRepository;
import tn.esprit.autoloc.repositories.ReservationRepository;
import tn.esprit.autoloc.services.ContratService;
import java.util.List;

@Service
public class ContratServiceImpl implements ContratService {
    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    public ContratServiceImpl(ContratRepository contratRepository, ReservationRepository reservationRepository) {
        this.contratRepository = contratRepository;
        this.reservationRepository = reservationRepository;
    }
    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        contrat.setReservation(reservationRepository.getReferenceById(contrat.getReservation().getIdReservation()));
        return contratRepository.save(contrat);
    }
    @Override
    public Contrat modifierContrat(Contrat contrat) {
        contrat.setReservation(reservationRepository.getReferenceById(contrat.getReservation().getIdReservation()));
        return contratRepository.save(contrat);
    }
    @Override
    public void supprimerContrat(Long idContrat) {
        contratRepository.deleteById(idContrat);
    }
    @Override
    public Contrat recupererContrat(Long idContrat) {
        return contratRepository.findById(idContrat).orElse(null);
    }
    @Override
    public List<Contrat> recupererTousLesContrats() {
        return contratRepository.findAll();
    }
}
