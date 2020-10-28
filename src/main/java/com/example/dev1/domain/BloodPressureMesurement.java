package com.example.dev1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class BloodPressureMesurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mesureId;
    private LocalDate mesureDate;
    private double bloodPressureUp;
    private double bloodPressureDown;
    private double heartBeat;

    public BloodPressureMesurement(double bloodPressureUp, double bloodPressureDown, double heartBeat) {
        this.bloodPressureUp = bloodPressureUp;
        this.bloodPressureDown = bloodPressureDown;
        this.heartBeat = heartBeat;
        this.mesureDate = LocalDate.now();
    }

    public BloodPressureMesurement(LocalDate mesureDate, double bloodPressureUp, double bloodPressureDown, double heartBeat) {
        this.mesureDate = mesureDate;
        this.bloodPressureUp = bloodPressureUp;
        this.bloodPressureDown = bloodPressureDown;
        this.heartBeat = heartBeat;
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

    public double getBloodPressureUp() {
        return bloodPressureUp;
    }

    public void setBloodPressureUp(double bloodPressureUp) {
        this.bloodPressureUp = bloodPressureUp;
    }

    public double getBloodPressureDown() {
        return bloodPressureDown;
    }

    public void setBloodPressureDown(double bloodPressureDown) {
        this.bloodPressureDown = bloodPressureDown;
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
                ", bloodPressureUp=" + bloodPressureUp +
                ", bloodPressureDown=" + bloodPressureDown +
                ", heartBeat=" + heartBeat +
                '}';
    }
}
