package Entities;

public class Professeur {
    private int idp;
    private String nci;
    private String nomCompletP;
    private String grade;

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getNomCompletP() {
        return nomCompletP;
    }

    public void setNomCompletP(String nomCompletP) {
        this.nomCompletP = nomCompletP;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}