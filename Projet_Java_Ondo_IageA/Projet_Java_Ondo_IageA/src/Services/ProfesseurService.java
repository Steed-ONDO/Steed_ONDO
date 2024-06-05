package Services;

import Entities.Professeur;
import Repositories.ProfesseurRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfesseurService {
    private ProfesseurRepository professeurRepository;

    public ProfesseurService(Connection connection) {
        this.professeurRepository = new ProfesseurRepository(connection);
    }

    public List<Professeur> listerProfesseurs() throws SQLException {
        return professeurRepository.listerProfesseurs();
    }
}
