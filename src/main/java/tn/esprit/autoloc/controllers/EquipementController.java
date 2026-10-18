package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.services.EquipementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {
    private final EquipementService equipementService;
    public EquipementController(EquipementService equipementService) { this.equipementService = equipementService; }
    @GetMapping public List<Equipement> recupererTousLesEquipements() { return equipementService.recupererTousLesEquipements(); }
    @GetMapping("/{idEquipement}") public ResponseEntity<Equipement> recupererEquipement(@PathVariable Long idEquipement) { return ResponseEntity.of(Optional.ofNullable(equipementService.recupererEquipement(idEquipement))); }
    @PostMapping public ResponseEntity<Equipement> ajouterEquipement(@RequestBody Equipement equipement) { return new ResponseEntity<>(equipementService.ajouterEquipement(equipement), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Equipement> modifierEquipement(@RequestBody Equipement equipement) { return ResponseEntity.ok(equipementService.modifierEquipement(equipement)); }
    @DeleteMapping("/{idEquipement}") public ResponseEntity<Void> supprimerEquipement(@PathVariable Long idEquipement) { equipementService.supprimerEquipement(idEquipement); return ResponseEntity.noContent().build(); }
}
