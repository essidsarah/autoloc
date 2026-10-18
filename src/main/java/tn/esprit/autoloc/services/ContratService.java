package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Contrat;
import java.util.List;

public interface ContratService {
    Contrat ajouterContrat(Contrat contrat);
    Contrat modifierContrat(Contrat contrat);
    void supprimerContrat(Long idContrat);
    Contrat recupererContrat(Long idContrat);
    List<Contrat> recupererTousLesContrats();
}
