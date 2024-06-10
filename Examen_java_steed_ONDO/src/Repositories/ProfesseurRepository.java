package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Professeur;

public class ProfesseurRepository {
    private static final String INSERT_QUERY = "INSERT INTO professeur (nci_prof, nom_completprof, Grade) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM professeur";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM professeur WHERE id_prof = ?";

    public void insert(Professeur professeur) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_iage", "root", "");
             PreparedStatement statement = conn.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, professeur.getNci());
            statement.setString(2, professeur.getNomCompletP());
            statement.setString(3, professeur.getGrade());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Professeur inséré avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de l'insertion du professeur : " + e.getMessage());
        }
    }

    public List<Professeur> selectAll() {
        List<Professeur> professeurs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_iage", "root", "");
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_QUERY);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Professeur professeur = new Professeur();
                professeur.setIdp(rs.getInt("id_prof"));
                professeur.setNci(rs.getString("nci_prof"));
                professeur.setNomCompletP(rs.getString("nom_completprof"));
                professeur.setGrade(rs.getString("Grade"));
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la récupération des professeurs : " + e.getMessage());
        }
        return professeurs;
    }

    public Professeur selectProfById(int profId) {
        Professeur professeur = null;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_iage", "root", "");
             PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, profId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    professeur = new Professeur();
                    professeur.setIdp(rs.getInt("id_prof"));
                    professeur.setNci(rs.getString("nci_prof"));
                    professeur.setNomCompletP(rs.getString("nom_completprof"));
                    professeur.setGrade(rs.getString("Grade"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de la récupération du professeur : " + e.getMessage());
        }
        return professeur;
    }
}