package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Maintenance;
import java.util.List;

public interface MaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Maintenance maintenance);
    void supprimerMaintenance(Long idMaintenance);
    Maintenance recupererMaintenance(Long idMaintenance);
    List<Maintenance> recupererToutesLesMaintenances();
}
