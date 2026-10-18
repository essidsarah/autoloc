package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.services.VehiculeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {
    private final VehiculeService vehiculeService;
    public VehiculeController(VehiculeService vehiculeService) { this.vehiculeService = vehiculeService; }
    @GetMapping public List<Vehicule> recupererTousLesVehicules() { return vehiculeService.recupererTousLesVehicules(); }
    @GetMapping("/{idVehicule}") public ResponseEntity<Vehicule> recupererVehicule(@PathVariable Long idVehicule) { return ResponseEntity.of(Optional.ofNullable(vehiculeService.recupererVehicule(idVehicule))); }
    @PostMapping public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) { return new ResponseEntity<>(vehiculeService.ajouterVehicule(vehicule), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Vehicule> modifierVehicule(@RequestBody Vehicule vehicule) { return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule)); }
    @DeleteMapping("/{idVehicule}") public ResponseEntity<Void> supprimerVehicule(@PathVariable Long idVehicule) { vehiculeService.supprimerVehicule(idVehicule); return ResponseEntity.noContent().build(); }
}
