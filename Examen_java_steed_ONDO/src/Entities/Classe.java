package Entities;

public class Classe {
    private int id;
    private String Libelle;



    public Classe(int id, String libelle) {
        this.id = id;
        Libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }
}