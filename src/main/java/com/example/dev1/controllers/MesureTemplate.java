package com.example.dev1.controllers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class MesureTemplate {
    private int mesureId;
    private LocalDate mesureDate;
    private int day;
    private int month;
    private int year;
    private double circumRef;
    private double bloodPressureUp;
    private double bloodPressureDown;
    private double heartBeat;

    public MesureTemplate() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCircumRef() {
        return circumRef;
    }

    public void setMesureId(int mesureId) {
        this.mesureId = mesureId;
    }

    public void setCircumRef(double circumRef) {
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
