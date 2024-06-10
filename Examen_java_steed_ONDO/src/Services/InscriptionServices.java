package Services;

import Entities.Inscription;
import Repositories.InscriptionRepository;

public class InscriptionServices {
    private InscriptionRepository inscriptionRepository = new InscriptionRepository();

    public void inscrireEtudiant(Inscription inscription) {
        try {
            if (inscription.getEtudiant() != null) {
                int idEtudiant = inscription.getEtudiant().getId();
                // Autres actions à effectuer avec l'id de l'étudiant
                inscriptionRepository.insert(inscription);
                System.out.println("Inscription réussie !");
            } else {
                System.out.println("L'objet Etudiant dans Inscription est null.");
                // Autres actions à effectuer en cas d'objet Etudiant null, si nécessaire
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'inscription : " + e.getMessage());
            e.printStackTrace();
            // Autres actions à effectuer en cas d'erreur, par exemple journalisation, notification, etc.
        }
    }
}