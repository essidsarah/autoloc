package tn.esprit.autoloc.services.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.repositories.EmployeRepository;
import tn.esprit.autoloc.services.EmployeService;
import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeRepository employeRepository;
    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }
    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }
    @Override
    public Employe modifierEmploye(Employe employe) {
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
