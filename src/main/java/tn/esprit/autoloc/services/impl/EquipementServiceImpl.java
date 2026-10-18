package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.repositories.EquipementRepository;
import tn.esprit.autoloc.services.EquipementService;
import java.util.List;

@Service
public class EquipementServiceImpl implements EquipementService {
    private final EquipementRepository equipementRepository;
    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }
    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }
    @Override
    public Equipement modifierEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }
    @Override
    public void supprimerEquipement(Long idEquipement) {
        equipementRepository.deleteById(idEquipement);
    }
    @Override
    public Equipement recupererEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement).orElse(null);
    }
    @Override
    public List<Equipement> recupererTousLesEquipements() {
        return equipementRepository.findAll();
    }
}
