package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.services.AgenceService;
import java.util.List;

@Service
public class AgenceServiceImpl implements AgenceService {
    private final AgenceRepository agenceRepository;
    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }
    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }
    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }
    @Override
    public void supprimerAgence(Long idAgence) {
        agenceRepository.deleteById(idAgence);
    }
    @Override
    public Agence recupererAgence(Long idAgence) {
        return agenceRepository.findById(idAgence).orElse(null);
    }
    @Override
    public List<Agence> recupererToutesLesAgences() {
        return agenceRepository.findAll();
    }
}
