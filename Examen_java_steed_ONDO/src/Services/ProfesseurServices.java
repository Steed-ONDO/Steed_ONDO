package Services;

import java.util.List;

import Entities.Professeur;
import Entities.ProfesseurClasse;
import Repositories.ProfesseurClasseRepository;
import Repositories.ProfesseurRepository;

public class ProfesseurServices {
    ProfesseurRepository professeurRepository = new ProfesseurRepository();
    ProfesseurClasseRepository professeurClasseRepository = new ProfesseurClasseRepository();

    public List<Professeur> listerProfesseurs() {
        return professeurRepository.selectAll();
    }

    public void ajouterProfesseur(Professeur professeur) {
        professeurRepository.insert(professeur);
    }

    // Méthode pour insérer une association professeur-classe
    public void ajouterProfesseurClasse(ProfesseurClasse professeurClasse) {
        professeurClasseRepository.insert(professeurClasse);
    }

    public Professeur rechercheProfesseurParId(int id) {
        return professeurRepository.selectProfById(id);
    }
}