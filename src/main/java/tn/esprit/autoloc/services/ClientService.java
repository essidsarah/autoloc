package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Client;
import java.util.List;

public interface ClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    void supprimerClient(Long idClient);
    Client recupererClient(Long idClient);
    List<Client> recupererTousLesClients();
}
