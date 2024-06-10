package Services;

import java.util.List;
import Entities.Classe;
import Repositories.ClasseRepository;

public class ClasseServices {
    private ClasseRepository classeRepository = new ClasseRepository();

    public List<Classe> listerClasses() {
        return classeRepository.selectAll();
    }

    public void ajouterClasse(Classe classe) {
        classeRepository.insert(classe);
    }

    public Classe rechercheClasseParId(int id) {
        return classeRepository.selectClasseById(id);
    }

    public Classe rechercheClasseParLibelle(String libelle) {
        return classeRepository.selectClasseByLibelle(libelle);
    }
}
