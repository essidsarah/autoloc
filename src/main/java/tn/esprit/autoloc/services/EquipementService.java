package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Equipement;
import java.util.List;

public interface EquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    void supprimerEquipement(Long idEquipement);
    Equipement recupererEquipement(Long idEquipement);
    List<Equipement> recupererTousLesEquipements();
}
