package com.example.musclerent;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Classe qui définit l'entité Reservation
 */
@Entity(tableName = "reservation_table", foreignKeys = @ForeignKey(entity = Salle.class, parentColumns = "salle_id", childColumns = "salle_id", onDelete = CASCADE))
public class Reservation {

    /**
     * Id de la reservation qui est auto-généré
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reservation_id")
    private int reservationId;
    
    /**
     * Id de la salle associée à la reservation
     */
    @ColumnInfo(name = "salle_id", index = true)
    private int salleId;

    /**
     * Date de la réservation jj/mm/aaaa
     */
    @ColumnInfo(name = "date")
    private String date;

    /**
     * Horaire de début de séance dans une chaine de caractère
     */
    @ColumnInfo(name = "horaire_debut")
    private String horaireDebut;

    /**
     * Horaire de fin de séance dans une chaine de caractère
     */
    @ColumnInfo(name = "horaire_fin")
    private String horaireFin;

    public Reservation(String d, String hD, String hF, int sId) {
        this.salleId = sId;
        this.date = d;
        this.horaireDebut = hD;
        this.horaireFin = hF;
    }

    public Reservation() {
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getSalleId() {
        return salleId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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
