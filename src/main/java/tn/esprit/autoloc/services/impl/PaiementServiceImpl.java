package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.repositories.PaiementRepository;
import tn.esprit.autoloc.repositories.ContratRepository;
import tn.esprit.autoloc.services.PaiementService;
import java.util.List;

@Service
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository, ContratRepository contratRepository) {
        this.paiementRepository = paiementRepository;
        this.contratRepository = contratRepository;
    }
    @Override
    public Paiement ajouterPaiement(Paiement paiement) {
        paiement.setContrat(contratRepository.getReferenceById(paiement.getContrat().getIdContrat()));
        return paiementRepository.save(paiement);
    }
    @Override
    public Paiement modifierPaiement(Paiement paiement) {
        Paiement paiementExistant = paiementRepository.findById(paiement.getIdPaiement()).orElse(null);
        if (paiementExistant == null) {
            return null;
        }
        paiementExistant.setMontant(paiement.getMontant());
        paiementExistant.setDatePaiement(paiement.getDatePaiement());
        paiementExistant.setModePaiement(paiement.getModePaiement());
        paiementExistant.setContrat(contratRepository.getReferenceById(paiement.getContrat().getIdContrat()));
        return paiementRepository.save(paiementExistant);
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
