package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Paiement;
import java.util.List;

public interface PaiementService {
    Paiement ajouterPaiement(Paiement paiement);
    Paiement modifierPaiement(Paiement paiement);
    void supprimerPaiement(Long idPaiement);
    Paiement recupererPaiement(Long idPaiement);
    List<Paiement> recupererTousLesPaiements();
}
