package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.VehiculeRepository;
import tn.esprit.autoloc.services.VehiculeService;
import java.util.List;

@Service
public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }
    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }
    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
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
