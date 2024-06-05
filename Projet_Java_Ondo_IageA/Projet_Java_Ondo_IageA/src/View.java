import Entities.Cours;
import Entities.Module;
import Entities.Professeur;
import Services.CoursService;
import Services.ModuleService;
import Services.ProfesseurService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_ism";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Scanner scanner = new Scanner(System.in);
    private static ModuleService moduleService;
    private static CoursService coursService;
    private static ProfesseurService professeurService;

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            moduleService = new ModuleService(connection);
            coursService = new CoursService(connection);
            professeurService = new ProfesseurService(connection);

            while (true) {
                System.out.println("Choisissez une option :");
                System.out.println("1. Ajouter un Module");
                System.out.println("2. Lister les Modules");
                System.out.println("3. Créer un cours");
                System.out.println("4. Lister tous les cours");
                System.out.println("5. Lister les cours d'un module et d'un professeur");
                System.out.println("6. Quitter");

                int choix = 0;
                try {
                    choix = scanner.nextInt();
                    scanner.nextLine(); // Consommer le retour à la ligne
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    scanner.nextLine(); // Consommer l'entrée invalide
                    continue;
                }

                switch (choix) {
                    case 1:
                        System.out.print("Nom du module : ");
                        String nom = scanner.nextLine();
                        Module module = new Module();
                        module.setNom(nom);

                        try {
                            moduleService.ajouterModule(module);
                            System.out.println("Module ajouté avec succès.");
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de l'ajout du module : " + e.getMessage());
                        }
                        break;
                    
                    case 2:
                        try {
                            List<Module> modules = moduleService.listerModules();
                            if (modules.isEmpty()) {
                                System.out.println("Aucun module trouvé.");
                            } else {
                                modules.forEach(m -> 
                                    System.out.println("ID: " + m.getId() + ", Nom: " + m.getNom())
                                );
                            }
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de la récupération des modules : " + e.getMessage());
                        }
                        break;
                    
                    case 3:
                        try {
                            System.out.print("ID du professeur : ");
                            int professeurId = scanner.nextInt();
                            System.out.print("ID du module : ");
                            int moduleId = scanner.nextInt();
                            System.out.print("Date (AAAA-MM-JJ) : ");
                            LocalDate date = LocalDate.parse(scanner.next());
                            System.out.print("Heure de début (HH:MM) : ");
                            LocalTime heureDebut = LocalTime.parse(scanner.next());
                            System.out.print("Heure de fin (HH:MM) : ");
                            LocalTime heureFin = LocalTime.parse(scanner.next());

                            Professeur professeur = new Professeur();
                            professeur.setId(professeurId);

                            Module mod = new Module();
                            mod.setId(moduleId);

                            Cours cours = new Cours();
                            cours.setDate(date);
                            cours.setHeureDebut(heureDebut);
                            cours.setHeureFin(heureFin);
                            cours.setProfesseur(professeur);
                            cours.setModule(mod);

                            coursService.creerCours(cours);
                            System.out.println("Cours créé avec succès.");
                        } catch (InputMismatchException e) {
                            System.err.println("Entrée invalide. Veuillez réessayer.");
                            scanner.nextLine(); // Consommer l'entrée invalide
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de la création du cours : " + e.getMessage());
                        }
                        break;
                    
                    case 4:
                        try {
                            List<Cours> coursList = coursService.listerTousLesCours();
                            if (coursList.isEmpty()) {
                                System.out.println("Aucun cours trouvé.");
                            } else {
                                coursList.forEach(c -> 
                                    System.out.println("ID: " + c.getId() + ", Date: " + c.getDate() + 
                                        ", Heure de début: " + c.getHeureDebut() + ", Heure de fin: " + c.getHeureFin() + 
                                        ", Professeur ID: " + c.getProfesseur().getId() + ", Module ID: " + c.getModule().getId())
                                );
                            }
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de la récupération des cours : " + e.getMessage());
                        }
                        break;
                    
                    case 5:
                        try {
                            System.out.print("ID du professeur : ");
                            int profId = scanner.nextInt();
                            System.out.print("ID du module : ");
                            int modId = scanner.nextInt();

                            List<Cours> coursList = coursService.listerCoursParModuleEtProfesseur(modId, profId);
                            if (coursList.isEmpty()) {
                                System.out.println("Aucun cours trouvé pour ce professeur et ce module.");
                            } else {
                                coursList.forEach(c -> 
                                    System.out.println("ID: " + c.getId() + ", Date: " + c.getDate() + 
                                        ", Heure de début: " + c.getHeureDebut() + ", Heure de fin: " + c.getHeureFin())
                                );
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Entrée invalide. Veuillez réessayer.");
                            scanner.nextLine(); // Consommer l'entrée invalide
                        } catch (SQLException e) {
                            System.err.println("Erreur lors de la récupération des cours : " + e.getMessage());
                        }
                        break;
                    
                    case 6:
                        System.out.println("Au revoir !");
                        return;
                    
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
    }
}