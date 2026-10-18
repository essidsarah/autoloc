package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.services.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) { this.clientService = clientService; }
    @GetMapping public List<Client> recupererTousLesClients() { return clientService.recupererTousLesClients(); }
    @GetMapping("/{idClient}") public ResponseEntity<Client> recupererClient(@PathVariable Long idClient) { return ResponseEntity.of(Optional.ofNullable(clientService.recupererClient(idClient))); }
    @PostMapping public ResponseEntity<Client> ajouterClient(@RequestBody Client client) { return new ResponseEntity<>(clientService.ajouterClient(client), HttpStatus.CREATED); }
    @PutMapping public ResponseEntity<Client> modifierClient(@RequestBody Client client) { return ResponseEntity.ok(clientService.modifierClient(client)); }
    @DeleteMapping("/{idClient}") public ResponseEntity<Void> supprimerClient(@PathVariable Long idClient) { clientService.supprimerClient(idClient); return ResponseEntity.noContent().build(); }
}
