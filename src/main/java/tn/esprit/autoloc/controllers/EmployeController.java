package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.services.EmployeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    private final EmployeService employeService;
    public EmployeController(EmployeService employeService) { this.employeService = employeService; }
    @GetMapping public List<Employe> recupererTousLesEmployes() { return employeService.recupererTousLesEmployes(); }
    @GetMapping("/{idEmploye}") public ResponseEntity<Employe> recupererEmploye(@PathVariable Long idEmploye) { return ResponseEntity.of(Optional.ofNullable(employeService.recupererEmploye(idEmploye))); }
    @PostMapping public ResponseEntity<Employe> ajouterEmploye(@RequestBody Employe employe) { return new ResponseEntity<>(employeService.ajouterEmploye(employe), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Employe> modifierEmploye(@RequestBody Employe employe) { return ResponseEntity.ok(employeService.modifierEmploye(employe)); }
    @DeleteMapping("/{idEmploye}") public ResponseEntity<Void> supprimerEmploye(@PathVariable Long idEmploye) { employeService.supprimerEmploye(idEmploye); return ResponseEntity.noContent().build(); }
}
