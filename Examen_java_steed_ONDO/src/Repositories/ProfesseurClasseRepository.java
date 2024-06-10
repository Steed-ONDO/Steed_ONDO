package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entities.ProfesseurClasse;

public class ProfesseurClasseRepository {
    private static final String INSERT_QUERY = "INSERT INTO professeur_classe (idPC, Id_classe, id_prof) VALUES (?, ?, ?)";

    public void insert(ProfesseurClasse professeurClasse) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_iage", "root", "");
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, professeurClasse.getId());
            statement.setInt(2, professeurClasse.getClasse().getId());
            statement.setInt(3, professeurClasse.getProfesseur().getIdp());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'association professeur-classe a été insérée avec succès !");
            } else {
                System.out.println("Échec de l'insertion de l'association professeur-classe.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de l'insertion de l'association professeur-classe : " + e.getMessage());
        }
    }
}


