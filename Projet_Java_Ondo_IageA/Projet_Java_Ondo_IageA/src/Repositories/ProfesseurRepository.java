package Repositories;

import Entities.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurRepository {
    private Connection connection;

    public ProfesseurRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Professeur> listerProfesseurs() throws SQLException {
        List<Professeur> professeurs = new ArrayList<>();
        String query = "SELECT * FROM Professeurs";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String tel = rs.getString("tel");
            professeurs.add(new Professeur(id, nom, prenom, tel));
        }
        return professeurs;
    }

    public Professeur getProfesseurById(int professeurId) {
        throw new UnsupportedOperationException("Unimplemented method 'getProfesseurById'");
    }
}
