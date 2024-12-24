package com.example.v2;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Trip implements Serializable {
    //Serializable
    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    @SerializedName("lieu_depart")
    private String lieuDepart;

    @SerializedName("lieu_destination")
    private String lieuDestination;

    @SerializedName("date_depart")
    private String dateDepart;

    @SerializedName("date_destination")
    private String dateDestination;

    @SerializedName("prix")
    private double prix;

    @SerializedName("nb_place")
    private int nbPlace;

    @SerializedName("description")
    private String description;

    @SerializedName("photo")
    private String photo;

    @SerializedName("average_rating")
    private double averageRating;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLieuDepart() { return lieuDepart; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }

    public String getLieuDestination() { return lieuDestination; }
    public void setLieuDestination(String lieuDestination) { this.lieuDestination = lieuDestination; }

    public String getDateDepart() { return dateDepart; }
    public void setDateDepart(String dateDepart) { this.dateDepart = dateDepart; }

    public String getDateDestination() { return dateDestination; }
    public void setDateDestination(String dateDestination) { this.dateDestination = dateDestination; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getNbPlace() { return nbPlace; }
    public void setNbPlace(int nbPlace) { this.nbPlace = nbPlace; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
}

