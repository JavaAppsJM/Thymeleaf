package com.example.dev1.domain;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(name = "getAllBloodMs",query = "select m from BloodPressureMesurement as m order by m.mesureDate")
@NamedQuery(name = "getBloodMById",query = "select m from BloodPressureMesurement as m where m.mesureId=:srchid")

@Entity
public class BloodPressureMesurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mesureId;
    private LocalDate mesureDate;
    private double bloodPressureHigh;
    private double bloodPressureLow;
    private double heartBeat;

    public BloodPressureMesurement(double bloodPressureHigh, double bloodPressureLow, double heartBeat) {
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.heartBeat = heartBeat;
        this.mesureDate = LocalDate.now();
    }

    public BloodPressureMesurement(LocalDate mesureDate, double bloodPressureHigh, double bloodPressureLow, double heartBeat) {
        this.mesureDate = mesureDate;
        this.bloodPressureHigh = bloodPressureHigh;
        this.bloodPressureLow = bloodPressureLow;
        this.heartBeat = heartBeat;
    }

    public BloodPressureMesurement() {
    }

    public void setMesureId(int mesureId) {
        this.mesureId = mesureId;
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

    public double getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(double bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public double getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(double bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public double getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(double heartBeat) {
        this.heartBeat = heartBeat;
    }

    @Override
    public String toString() {
        return "BloodPressureMesurement{" +
                "mesureId=" + mesureId +
                ", mesureDate=" + mesureDate +
                ", bloodPressureUp=" + bloodPressureHigh +
                ", bloodPressureDown=" + bloodPressureLow +
                ", heartBeat=" + heartBeat +
                '}';
    }
}
