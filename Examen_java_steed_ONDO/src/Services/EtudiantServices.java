package Services;

import java.util.List;

import Entities.Etudiant;
import Entities.EtudiantClasse;
import Repositories.EtudiantClasseRepository;
import Repositories.EtudiantRepository;

public class EtudiantServices {
    EtudiantRepository etudiantRepository = new EtudiantRepository();
    EtudiantClasseRepository etudiantClasseRepository = new EtudiantClasseRepository();

    public List<Etudiant> listerEtudiants() {
        return etudiantRepository.selectAll();
    }

    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        // Insérer l'étudiant dans la base de données
        etudiantRepository.insert(etudiant);
        
        // Récupérer l'ID de l'étudiant inséré
        Etudiant etudiantInsere = etudiantRepository.selectEtudiantByMatri(etudiant.getMatri());
        
        if (etudiantInsere != null) {
            // Retourner l'objet Etudiant inséré
            return etudiantInsere;
        } else {
            // Gérer l'échec de l'insertion de l'étudiant
            return null;
        }
    }
    
    public Etudiant rechercheEtudiantParMatri(String matri) {
        return etudiantRepository.selectEtudiantByMatri(matri);
    }

    // Méthode pour insérer une association étudiant-classe
    public void ajouterEtudiantClasse(EtudiantClasse etudiantClasse) {
        etudiantClasseRepository.insert(etudiantClasse);
    }

    public Etudiant rechercheEtudiantParId(int id) {
        return etudiantRepository.selectEtudiantById(id);
    }

    public List<Etudiant> listerEtudiantsParClasse(String libelle) {
        return etudiantClasseRepository.selectEtudiantsByClasse(libelle);
    }
}
