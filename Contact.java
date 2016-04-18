package com.nwo.prodigy.care4project;

/**
 * Created by Prodigy on 16-04-14.
 */
public class Contact {
    private String messageId;
    private String nomUtilisateur;
    private String nom;
    private String prenom;
    private String telephone;

    Contact(String messageId, String nomUtilisateur,String nom, String prenom, String telephone){
            this(nomUtilisateur, nom,prenom,telephone);
            this.messageId = messageId;

        }

        Contact(String nomUtilisateur,String nom, String prenom, String telephone) {
            this.nomUtilisateur = nomUtilisateur;
            this.nom = nom;
            this.prenom = prenom;
            this.telephone = telephone;
        }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
