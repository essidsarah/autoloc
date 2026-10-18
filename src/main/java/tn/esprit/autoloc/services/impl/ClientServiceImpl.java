package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.repositories.ClientRepository;
import tn.esprit.autoloc.services.ClientService;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public Client modifierClient(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public void supprimerClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }
    @Override
    public Client recupererClient(Long idClient) {
        return clientRepository.findById(idClient).orElse(null);
    }
    @Override
    public List<Client> recupererTousLesClients() {
        return clientRepository.findAll();
    }
}
