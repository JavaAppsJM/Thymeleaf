package com.example.dev1.domain;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(name = "getAllBellyMs",query = "select m from BellyMesurement as m")
@NamedQuery(name = "getBellyMById",query = "select m from BellyMesurement as m where m.mesureId=:srchid")
@Entity
public class BellyMesurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mesureId;
    private LocalDate mesureDate;
    private double circumRef;

    public BellyMesurement() {
    }

    public BellyMesurement(double circumRef) {
        this.circumRef = circumRef;
        this.mesureDate = LocalDate.now();
    }

    public BellyMesurement(LocalDate mesureDate, double circumRef) {
        this.mesureDate = mesureDate;
        this.circumRef = circumRef;
    }

    public int getMesureId() {
        return mesureId;
    }

    public LocalDate getMesureDate() {
        return mesureDate;
    }

    public void setMesureDate(LocalDate mesureDate) {
        this.mesureDate = mesureDate;
    }

    public double getCircumRef() {
        return circumRef;
    }

    public void setCircumRef(double circumRef) {
        this.circumRef = circumRef;
    }

    @Override
    public String toString() {
        return "BellyMesurement{" +
                "mesureId=" + mesureId +
                ", mesureDate=" + mesureDate +
                ", circumRef=" + circumRef +
                '}';
    }
}
