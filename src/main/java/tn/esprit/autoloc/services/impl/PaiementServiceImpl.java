package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.repositories.PaiementRepository;
import tn.esprit.autoloc.services.PaiementService;
import java.util.List;

@Service
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository paiementRepository;
    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }
    @Override
    public Paiement ajouterPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }
    @Override
    public Paiement modifierPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }
    @Override
    public void supprimerPaiement(Long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }
    @Override
    public Paiement recupererPaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement).orElse(null);
    }
    @Override
    public List<Paiement> recupererTousLesPaiements() {
        return paiementRepository.findAll();
    }
}
