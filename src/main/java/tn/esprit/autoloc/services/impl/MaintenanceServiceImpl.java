package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.repositories.MaintenanceRepository;
import tn.esprit.autoloc.repositories.VehiculeRepository;
import tn.esprit.autoloc.services.MaintenanceService;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, VehiculeRepository vehiculeRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehiculeRepository = vehiculeRepository;
    }
    @Override
    public Maintenance ajouterMaintenance(Maintenance maintenance) {
        maintenance.setVehicule(vehiculeRepository.getReferenceById(maintenance.getVehicule().getIdVehicule()));
        return maintenanceRepository.save(maintenance);
    }
    @Override
    public Maintenance modifierMaintenance(Maintenance maintenance) {
        maintenance.setVehicule(vehiculeRepository.getReferenceById(maintenance.getVehicule().getIdVehicule()));
        return maintenanceRepository.save(maintenance);
    }
    @Override
    public void supprimerMaintenance(Long idMaintenance) {
        maintenanceRepository.deleteById(idMaintenance);
    }
    @Override
    public Maintenance recupererMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance).orElse(null);
    }
    @Override
    public List<Maintenance> recupererToutesLesMaintenances() {
        return maintenanceRepository.findAll();
    }
}
