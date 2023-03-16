package com.example.musclerent;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "salle_table")
public class Salle {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "salle_id")
    private int salleId;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "adresse")
    private String adresse;
    @ColumnInfo(name = "details")
    private String details;

    public Salle(String n, String a, String d) {
        this.nom = n;
        this.adresse = a;
        this.details = d;
    }

    public int getSalleId() {
        return salleId;
    }

    public void setSalleId(int salleId) {
        this.salleId = salleId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
