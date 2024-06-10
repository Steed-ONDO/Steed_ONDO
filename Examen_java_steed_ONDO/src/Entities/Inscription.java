package Entities;

public class Inscription {
    private int id;
    private String dateI;
    // Relation ManyToOne
    private Classe classe;
    // Relation ManyToOne
    private Etudiant etudiant;

    public String getDateI() {
        return dateI;
    }
    public void setDateI(String dateI) {
        this.dateI = dateI;
    }
   

    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
  
    
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public Inscription() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Inscription [classe=" + classe + ", etudiant=" + etudiant + "]";
    }
 
}
