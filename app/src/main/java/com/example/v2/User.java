package com.example.v2;

public class User {
    private int id;
    private String email;
    private String type;
    private String nom;
    private String prenom;
    private String telephone;
    private String cin;
    private int isVerified;
    // Getters and setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
    public String getCin() {return cin;}
    public void setCin(String cin) {this.cin = cin;}
    public int getIsVerified() {return isVerified;}
    public void setIsVerified(int isVerified) {this.isVerified = isVerified;}
}

