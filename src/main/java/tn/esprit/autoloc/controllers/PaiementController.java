package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.services.PaiementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
    private final PaiementService paiementService;
    public PaiementController(PaiementService paiementService) { this.paiementService = paiementService; }
    @GetMapping public List<Paiement> recupererTousLesPaiements() { return paiementService.recupererTousLesPaiements(); }
    @GetMapping("/{idPaiement}") public ResponseEntity<Paiement> recupererPaiement(@PathVariable Long idPaiement) { return ResponseEntity.of(Optional.ofNullable(paiementService.recupererPaiement(idPaiement))); }
    @PostMapping public ResponseEntity<Paiement> ajouterPaiement(@RequestBody Paiement paiement) { return new ResponseEntity<>(paiementService.ajouterPaiement(paiement), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Paiement> modifierPaiement(@RequestBody Paiement paiement) { return ResponseEntity.ok(paiementService.modifierPaiement(paiement)); }
    @DeleteMapping("/{idPaiement}") public ResponseEntity<Void> supprimerPaiement(@PathVariable Long idPaiement) { paiementService.supprimerPaiement(idPaiement); return ResponseEntity.noContent().build(); }
}
