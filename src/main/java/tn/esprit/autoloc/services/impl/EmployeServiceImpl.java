package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.repositories.EmployeRepository;
import tn.esprit.autoloc.repositories.AgenceRepository;
import tn.esprit.autoloc.services.EmployeService;
import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository, AgenceRepository agenceRepository) {
        this.employeRepository = employeRepository;
        this.agenceRepository = agenceRepository;
    }
    @Override
    public Employe ajouterEmploye(Employe employe) {
        employe.setAgence(agenceRepository.getReferenceById(employe.getAgence().getIdAgence()));
        return employeRepository.save(employe);
    }
    @Override
    public Employe modifierEmploye(Employe employe) {
        employe.setAgence(agenceRepository.getReferenceById(employe.getAgence().getIdAgence()));
        return employeRepository.save(employe);
    }
    @Override
    public void supprimerEmploye(Long idEmploye) {
        employeRepository.deleteById(idEmploye);
    }
    @Override
    public Employe recupererEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye).orElse(null);
    }
    @Override
    public List<Employe> recupererTousLesEmployes() {
        return employeRepository.findAll();
    }
}
