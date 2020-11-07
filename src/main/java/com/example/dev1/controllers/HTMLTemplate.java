package com.example.dev1.controllers;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;

import java.time.LocalDate;

public class HTMLTemplate {
    private String hTitle;
    private String hFormActionUrl;
    private String[][] hList;
    private BellyMesurement bellyMesurement;
    private BloodPressureMesurement bloodPressureMesurement;

    private int mesureId;
    private LocalDate mesureDate;
    private int day;
    private int month;
    private int year;
    private double circumRef;
    private double bloodPressureHigh;
    private double bloodPressureLow;
    private double heartBeat;

    public HTMLTemplate() {
    }

    public BellyMesurement getBellyMesurement() {
        return bellyMesurement;
    }

    public void setBellyMesurement(BellyMesurement bellyMesurement) {
        this.bellyMesurement = bellyMesurement;
    }

    public BloodPressureMesurement getBloodPressureMesurement() {
        return bloodPressureMesurement;
    }

    public void setBloodPressureMesurement(BloodPressureMesurement bloodPressureMesurement) {
        this.bloodPressureMesurement = bloodPressureMesurement;
    }

    public String gethTitle() {
        return hTitle;
    }

    public String[][] gethList() {
        return hList;
    }

    public void sethList(String[][] hList) {
        this.hList = hList;
    }

    public void sethTitle(String hTitle) {
        this.hTitle = hTitle;
    }

    public String gethFormActionUrl() {
        return hFormActionUrl;
    }

    public void sethFormActionUrl(String hFormActionUrl) {
        this.hFormActionUrl = hFormActionUrl;
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

}
