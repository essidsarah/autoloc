package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.ContratService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {
    private final ContratService contratService;
    public ContratController(ContratService contratService) { this.contratService = contratService; }
    @GetMapping public List<Contrat> recupererTousLesContrats() { return contratService.recupererTousLesContrats(); }
    @GetMapping("/{idContrat}") public ResponseEntity<Contrat> recupererContrat(@PathVariable Long idContrat) { return ResponseEntity.of(Optional.ofNullable(contratService.recupererContrat(idContrat))); }
    @PostMapping public ResponseEntity<Contrat> ajouterContrat(@RequestBody Contrat contrat) { return new ResponseEntity<>(contratService.ajouterContrat(contrat), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Contrat> modifierContrat(@RequestBody Contrat contrat) { return ResponseEntity.ok(contratService.modifierContrat(contrat)); }
    @DeleteMapping("/{idContrat}") public ResponseEntity<Void> supprimerContrat(@PathVariable Long idContrat) { contratService.supprimerContrat(idContrat); return ResponseEntity.noContent().build(); }
}
