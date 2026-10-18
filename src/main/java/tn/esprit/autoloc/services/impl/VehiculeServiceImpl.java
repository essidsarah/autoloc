package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.VehiculeRepository;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.services.VehiculeService;
import java.util.List;

@Service
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, AgenceRepository agenceRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
    }
    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        vehicule.setAgence(agenceRepository.getReferenceById(vehicule.getAgence().getIdAgence()));
        return vehiculeRepository.save(vehicule);
    }
    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        vehicule.setAgence(agenceRepository.getReferenceById(vehicule.getAgence().getIdAgence()));
        return vehiculeRepository.save(vehicule);
    }
    @Override
    public void supprimerVehicule(Long idVehicule) {
        vehiculeRepository.deleteById(idVehicule);
    }
    @Override
    public Vehicule recupererVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule).orElse(null);
    }
    @Override
    public List<Vehicule> recupererTousLesVehicules() {
        return vehiculeRepository.findAll();
    }
}
