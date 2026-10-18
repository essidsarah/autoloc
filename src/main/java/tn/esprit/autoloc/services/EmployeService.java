package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Employe;
import java.util.List;

public interface EmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    void supprimerEmploye(Long idEmploye);
    Employe recupererEmploye(Long idEmploye);
    List<Employe> recupererTousLesEmployes();
}
