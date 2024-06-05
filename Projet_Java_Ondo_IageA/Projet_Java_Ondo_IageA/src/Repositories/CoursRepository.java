package Repositories;

import Entities.Cours;
import Entities.Module;
import Entities.Professeur;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CoursRepository {
    private Connection connection;

    public CoursRepository(Connection connection) {
        this.connection = connection;
    }

    public void creerCours(Cours cours) throws SQLException {
        String query = "INSERT INTO Cours (date, heureDebut, heureFin, professeurId, moduleId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, Date.valueOf(cours.getDate()));
        stmt.setTime(2, Time.valueOf(cours.getHeureDebut()));
        stmt.setTime(3, Time.valueOf(cours.getHeureFin()));
        stmt.setInt(4, cours.getProfesseur().getId());
        stmt.setInt(5, cours.getModule().getId());
        stmt.executeUpdate();
    }

    public List<Cours> listerTousLesCours() throws SQLException {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM Cours";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate date = rs.getDate("date").toLocalDate();
            LocalTime heureDebut = rs.getTime("heureDebut").toLocalTime();
            LocalTime heureFin = rs.getTime("heureFin").toLocalTime();
            int professeurId = rs.getInt("professeurId");
            int moduleId = rs.getInt("moduleId");

            Professeur professeur = new ProfesseurRepository(connection).getProfesseurById(professeurId);
            Module module = new ModuleRepository(connection).getModuleById(moduleId);

            coursList.add(new Cours(id, date, heureDebut, heureFin, professeur, module));
        }
        return coursList;
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int moduleId, int professeurId) throws SQLException {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM Cours WHERE professeurId = ? AND moduleId = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, professeurId);
        stmt.setInt(2, moduleId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            LocalDate date = rs.getDate("date").toLocalDate();
            LocalTime heureDebut = rs.getTime("heureDebut").toLocalTime();
            LocalTime heureFin = rs.getTime("heureFin").toLocalTime();

            Professeur professeur = new ProfesseurRepository(connection).getProfesseurById(professeurId);
            Module module = new ModuleRepository(connection).getModuleById(moduleId);

            coursList.add(new Cours(id, date, heureDebut, heureFin, professeur, module));
        }
        return coursList;
    }
}