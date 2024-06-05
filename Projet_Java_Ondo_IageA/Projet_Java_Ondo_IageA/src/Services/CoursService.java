package Services;

import Entities.Cours;
import Repositories.CoursRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CoursService {
    private CoursRepository coursRepository;

    public CoursService(Connection connection) {
        this.coursRepository = new CoursRepository(connection);
    }

    public void creerCours(Cours cours) throws SQLException {
        coursRepository.creerCours(cours);
    }

    public List<Cours> listerTousLesCours() throws SQLException {
        return coursRepository.listerTousLesCours();
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int moduleId, int professeurId) throws SQLException {
        return coursRepository.listerCoursParModuleEtProfesseur(moduleId, professeurId);
    }
}
