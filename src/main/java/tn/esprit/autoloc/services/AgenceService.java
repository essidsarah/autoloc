package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Agence;
import java.util.List;

public interface AgenceService {
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Agence agence);
    void supprimerAgence(Long idAgence);
    Agence recupererAgence(Long idAgence);
    List<Agence> recupererToutesLesAgences();
}
