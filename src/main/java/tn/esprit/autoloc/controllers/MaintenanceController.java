package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.services.MaintenanceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    public MaintenanceController(MaintenanceService maintenanceService) { this.maintenanceService = maintenanceService; }
    @GetMapping public List<Maintenance> recupererToutesLesMaintenances() { return maintenanceService.recupererToutesLesMaintenances(); }
    @GetMapping("/{idMaintenance}") public ResponseEntity<Maintenance> recupererMaintenance(@PathVariable Long idMaintenance) { return ResponseEntity.of(Optional.ofNullable(maintenanceService.recupererMaintenance(idMaintenance))); }
    @PostMapping public ResponseEntity<Maintenance> ajouterMaintenance(@RequestBody Maintenance maintenance) { return new ResponseEntity<>(maintenanceService.ajouterMaintenance(maintenance), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Maintenance> modifierMaintenance(@RequestBody Maintenance maintenance) { return ResponseEntity.ok(maintenanceService.modifierMaintenance(maintenance)); }
    @DeleteMapping("/{idMaintenance}") public ResponseEntity<Void> supprimerMaintenance(@PathVariable Long idMaintenance) { maintenanceService.supprimerMaintenance(idMaintenance); return ResponseEntity.noContent().build(); }
}
