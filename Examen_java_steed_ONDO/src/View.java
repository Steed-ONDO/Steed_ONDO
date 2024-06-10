import java.util.List;
import java.util.Scanner;



import Entities.Classe;
import Entities.Etudiant;
import Entities.EtudiantClasse;
import Entities.Inscription;
import Entities.Professeur;
import Entities.ProfesseurClasse;
import Services.ClasseServices;
import Services.EtudiantServices;
import Services.InscriptionServices;
import Services.ProfesseurServices;

public class View {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        EtudiantServices etudiantService= new EtudiantServices();
        InscriptionServices inscriptionService= new InscriptionServices();
        ClasseServices classeService=new ClasseServices();
        ProfesseurServices professeurService=new ProfesseurServices();
        List<Classe> classes= classeService.listerClasses();
        List<Etudiant> etudiants=  etudiantService.listerEtudiants();
        List<Professeur> professeurs= professeurService.listerProfesseurs();
        int choix;
        do {
            System.out.println("1-Ajouter une Classe");
            System.out.println("2-Lister les Classes");
            System.out.println("3-Ajouter un Professeur");
            System.out.println("4-Lister les Professeurs");
            System.out.println("5-Lister les Professeurs d'une Classe");
            System.out.println("6-Ajouter Etudiant");
            System.out.println("7-Faire une Réinscription");
            System.out.println("8-Lister les Etudiants d'une Classe");
            System.out.println("9-Faire une Inscription");
            System.out.println("10-Attribuer un professeur a une classe");
            System.out.println("11-Attribuer un etudiant a une classe");
            System.out.println("12-Quitter");
            System.out.println("Faites un choix");
            choix=sc.nextInt();
            sc.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Entrer le Libellé");
                    String Libelle=sc.nextLine();
                    Classe cl=new Classe(choix, Libelle);
                    cl.setLibelle(Libelle);
                    classeService.ajouterClasse(cl);
                break;
                case 2:
                    
                    classes=  classeService.listerClasses();
                    for (Classe classe: classes) {
                        System.out.println("Libelle "+ classe.getLibelle());
                        System.out.println("=================================");
                    }
                break;
                case 3:
                    System.out.println("Entrer le grade du professeur");
                    String grade=sc.nextLine();
                    System.out.println("Entrer le nom et le prenom du professeur");
                    String nomCompletp=sc.nextLine();
                    System.out.println("Entrer le NCI du professeur");
                    String NCI=sc.nextLine();
                    
                    Professeur prof=new Professeur();
                    prof.setGrade(grade);
                    prof.setNci(NCI);
                    prof.setNomCompletP(nomCompletp);
                    professeurService.ajouterProfesseur(prof);
                   
                    
                break;
                case 4:
                professeurs=  professeurService.listerProfesseurs();
                for (Professeur professeur: professeurs) {
                    System.out.println("nom "+ professeur.getNomCompletP());
                    System.out.println("=================================");
                }
                break;

                case 5:
                

                break;
                case 6:
                   // Demander les informations sur l'étudiant à l'utilisateur
                    Etudiant etd = new Etudiant();
                    System.out.println("Entrez le nom de l'étudiant : ");
                    String nomComplet = sc.nextLine();
                    etd.setNomComplet(nomComplet);
                    System.out.println("Entrez le numéro de matricule : ");
                    String matricule = sc.nextLine();
                    etd.setMatri(matricule);
                    System.out.println("Entrez le nom du tuteur : ");
                    String tuteur = sc.nextLine();
                    etd.setTuteur(tuteur);

                    // Insérer l'étudiant dans la base de données
                    Etudiant etudiantInsere = etudiantService.ajouterEtudiant(etd);

                   
                       

                    
                    
                break;
                case 7:
                    // Rechercher l'etudiant
                    System.out.println("Entrez l'id  de l'étudiant : ");
                    int idInscEtudiantR= sc.nextInt();
                    Etudiant idEtudiantR = etudiantService.rechercheEtudiantParId(idInscEtudiantR);
                    sc.nextLine();
                    if(idEtudiantR != null){
                        String dateR;
                        System.out.println("Saisir la date de reinscription: ");
                        dateR=sc.nextLine();
                        //Recherche la classe
                        System.out.println("Entrez le libelle de la classe à laquelle vous souhaitez ajouter l'étudiant : ");
                        String libClasseReins = sc.nextLine();
                        Classe classeReins = classeService.rechercheClasseParLibelle(libClasseReins);
                        //Créez un objet Inscription
                        Inscription inscription = new Inscription();
                        inscription.setDateI(dateR);
                        inscription.setEtudiant(idEtudiantR);
                        inscription.setClasse(classeReins);
                        //System.out.println(inscription);
                            // Enregistrez l'inscription dans la base de données
                        inscriptionService.inscrireEtudiant(inscription);
                    }else{
                        System.out.println("L'etudiant n'existe pas! Veuillez d'abord vous inscrire.");
                    }
                   
                    
                  
            
                break;
                case 8:
                    System.out.println("Entrez le libelle de la classe: ");
                    String libClasseRecherche = sc.nextLine();
                    etudiants=  etudiantService.listerEtudiantsParClasse(libClasseRecherche);
                    for (Etudiant etudiant: etudiants) {
                        System.out.println("nom "+ etudiant.getNomComplet());
                        System.out.println("=================================");
                    }
            
                break;
                case 9:
                    String dateI;
                    System.out.println("Saisir la date d'inscription: ");
                    dateI=sc.nextLine();
                    //Recherche la classe
                    System.out.println("Entrez le libelle de la classe à laquelle vous souhaitez ajouter l'étudiant : ");
                    String libClasseIns = sc.nextLine();
                    Classe classeIns = classeService.rechercheClasseParLibelle(libClasseIns);
                    
                     // Rechercher l'etudiant
                    System.out.println("Entrez l'id  de l'étudiant : ");
                    int idInscEtudiant= sc.nextInt();
                    Etudiant idEtudiant = etudiantService.rechercheEtudiantParId(idInscEtudiant);
                    //Créez un objet Inscription
                    Inscription inscription = new Inscription();
                    inscription.setDateI(dateI);
                    inscription.setEtudiant(idEtudiant);
                    inscription.setClasse(classeIns);
                    //System.out.println(inscription);
                        // Enregistrez l'inscription dans la base de données
                    inscriptionService.inscrireEtudiant(inscription);
                break;
                case 10:
                  
                        // Ajouter le prof à une classe
                        System.out.println("Entrez le libelle de la classe à laquelle vous souhaitez ajouter le professeur : ");
                        //int idClasseProf = Integer.parseInt(sc.nextLine());
                        String libelle = sc.nextLine();
                        //Classe classeProf = classeService.rechercheClasseParId(idClasseProf);
                        
                        System.out.println("Entrez l'ID du professeur à laquelle vous souhaitez ajouter : ");
                        int idProfesseur = sc.nextInt();

                        //Recuperation du professeur et de la classe
                        Classe classeProf = classeService.rechercheClasseParLibelle(libelle);
                        Professeur profClasse = professeurService.rechercheProfesseurParId(idProfesseur);

                        // Créer un objet ProfesseurClasse avec l'ID de la classe et l'ID du professeur inséré
                        ProfesseurClasse professeurClasse = new ProfesseurClasse();
                        professeurClasse.setClasse(classeProf);
                        professeurClasse.setProfesseur(profClasse);; // Utiliser l'ID de le professeur inséré
                        
                        // Ajouter le professeur à la classe
                      
                        professeurService.ajouterProfesseurClasse(professeurClasse);
                        System.out.println("Le professeur " + professeurClasse.getProfesseur().getNomCompletP() +" a été ajouté à la classe " + professeurClasse.getClasse().getLibelle() +" avec succès" );                    
                break;
                case 11:

                         // Ajouter l'étudiant à une classe
                         System.out.println("Entrez le libelle de la classe à laquelle vous souhaitez ajouter l'etudiant : ");
                         String libelle1 = sc.nextLine();
                        
                        /*  System.out.println("Entrez le matricule de l'etudiant à laquelle vous souhaitez ajouter : ");
                         String matriculeE = sc.nextLine(); */

                         
                        System.out.println("Entrez l'ID de l'étudiant à laquelle vous souhaitez ajouter : ");
                        int idEtudiant1 = sc.nextInt();

                         //Recuperation du professeur et de la classe
                         Etudiant etuClasse = etudiantService.rechercheEtudiantParId(idEtudiant1);
                         Classe classeE = classeService.rechercheClasseParLibelle(libelle1);

                         // Créer un objet EtudiantClasse avec l'ID de la classe et l'ID de l'étudiant inséré
                         EtudiantClasse etudiantClasse = new EtudiantClasse();
                         etudiantClasse.setClasse(classeE);
                         etudiantClasse.setEtudiant(etuClasse); // Utiliser l'ID de l'étudiant inséré
                         
                         // Ajouter l'étudiant à la classe
                         etudiantService.ajouterEtudiantClasse(etudiantClasse);
 
                         
                         System.out.println("L'étudiant "+ etudiantClasse.getEtudiant().getNomComplet() +" a été ajouté à la classe avec succès dans la classe " + classeE.getLibelle());
                        
                break;

            }
        }while(choix!=12);
        sc.close();
    }
}

