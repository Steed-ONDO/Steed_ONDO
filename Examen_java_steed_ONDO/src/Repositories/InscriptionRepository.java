package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entities.Inscription;

public class InscriptionRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/examen_iage";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static final String INSERT_QUERY = "INSERT INTO inscription (Id_inscription, date_inscription, nom_classe, etudiant_id) VALUES (?, ?, ?, ?)";

    public void insert(Inscription inscription) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            statement = conn.prepareStatement(INSERT_QUERY);
            statement.setInt(1, inscription.getId());
            statement.setString(2, inscription.getDateI());
            statement.setString(3, inscription.getClasse().getLibelle());
            statement.setInt(4, inscription.getEtudiant().getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'inscription a été insérée avec succès !");
            } else {
                System.out.println("Échec de l'insertion de l'inscription.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver JDBC : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de l'insertion de l'inscription : " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }
}

/* public List<Inscription> selectAll() {
    List<Inscription> inscriptions = new ArrayList<>();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen_iage", "root", "");
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM `inscription`";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Inscription inscription = new Inscription();
            inscription.setDateI(rs.getString("date_inscription"));
            inscription.setIdclasse(rs.getInt("classe_id"));
            // Notez qu'ici, nous ne récupérons pas l'objet Etudiant et la Classe, car ils ne sont pas directement liés à la table inscription.
            inscriptions.add(inscription);
        }
        statement.close();
        rs.close();
        conn.close();
    } catch (ClassNotFoundException e) {
        System.out.println("Erreur de chargement de Driver");
    } catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
    }
    return inscriptions;
}
 */

