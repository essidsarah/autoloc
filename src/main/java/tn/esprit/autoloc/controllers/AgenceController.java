package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.services.AgenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {
    private final AgenceService agenceService;
    public AgenceController(AgenceService agenceService) { this.agenceService = agenceService; }
    @GetMapping public List<Agence> recupererToutesLesAgences() { return agenceService.recupererToutesLesAgences(); }
    @GetMapping("/{idAgence}") public ResponseEntity<Agence> recupererAgence(@PathVariable Long idAgence) { return ResponseEntity.of(Optional.ofNullable(agenceService.recupererAgence(idAgence))); }
    @PostMapping public ResponseEntity<Agence> ajouterAgence(@RequestBody Agence agence) { return new ResponseEntity<>(agenceService.ajouterAgence(agence), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Agence> modifierAgence(@RequestBody Agence agence) { return ResponseEntity.ok(agenceService.modifierAgence(agence)); }
    @DeleteMapping("/{idAgence}") public ResponseEntity<Void> supprimerAgence(@PathVariable Long idAgence) { agenceService.supprimerAgence(idAgence); return ResponseEntity.noContent().build(); }
}
