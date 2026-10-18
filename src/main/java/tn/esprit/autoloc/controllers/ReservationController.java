package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.services.ReservationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) { this.reservationService = reservationService; }
    @GetMapping public List<Reservation> recupererToutesLesReservations() { return reservationService.recupererToutesLesReservations(); }
    @GetMapping("/{idReservation}") public ResponseEntity<Reservation> recupererReservation(@PathVariable Long idReservation) { return ResponseEntity.of(Optional.ofNullable(reservationService.recupererReservation(idReservation))); }
    @PostMapping public ResponseEntity<Reservation> ajouterReservation(@RequestBody Reservation reservation) { return new ResponseEntity<>(reservationService.ajouterReservation(reservation), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Reservation> modifierReservation(@RequestBody Reservation reservation) { return ResponseEntity.ok(reservationService.modifierReservation(reservation)); }
    @DeleteMapping("/{idReservation}") public ResponseEntity<Void> supprimerReservation(@PathVariable Long idReservation) { reservationService.supprimerReservation(idReservation); return ResponseEntity.noContent().build(); }
}
