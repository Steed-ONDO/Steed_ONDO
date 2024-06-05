package Repositories;

import Entities.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleRepository {
    private Connection connection;

    public ModuleRepository(Connection connection) {
        this.connection = connection;
    }

    public void ajouterModule(Module module) throws SQLException {
        String query = "INSERT INTO Modules (nom) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, module.getNom());
        stmt.executeUpdate();
    }

    public List<Module> listerModules() throws SQLException {
        List<Module> modules = new ArrayList<>();
        String query = "SELECT * FROM Modules";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            modules.add(new Module(id, nom));
        }
        return modules;
    }

    public Module getModuleById(int moduleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModuleById'");
    }
}