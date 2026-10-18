package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Vehicule;
import java.util.List;

public interface VehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    void supprimerVehicule(Long idVehicule);
    Vehicule recupererVehicule(Long idVehicule);
    List<Vehicule> recupererTousLesVehicules();
}
