package com.example.musclerent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation_table")
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reservation_id")
    private int reservationId;
    private int salleId;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "horaire_debut")
    private String horaireDebut;
    @ColumnInfo(name = "horaire_fin")
    private String horaireFin;

    public Reservation(String d, String hD, String hF) {
        this.salleId = -1;
        this.date = d;
        this.horaireDebut = hD;
        this.horaireFin = hF;
    }

    public int getSalleId() {
        return salleId;
    }

    public void setSalleId(int salleId) {
        this.salleId = salleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(String horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public String getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(String horaireFin) {
        this.horaireFin = horaireFin;
    }
}
