package Services;

import Entities.Module;
import Repositories.ModuleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ModuleService {
    private ModuleRepository moduleRepository;

    public ModuleService(Connection connection) {
        this.moduleRepository = new ModuleRepository(connection);
    }

    public void ajouterModule(Module module) throws SQLException {
        moduleRepository.ajouterModule(module);
    }

    public List<Module> listerModules() throws SQLException {
        return moduleRepository.listerModules();
    }
}