package com.example.dev1.controllers;

import com.example.dev1.domain.BellyMesurement;
import com.example.dev1.domain.BloodPressureMesurement;

import java.time.LocalDate;

public class HTMLColumnListD {
    private String hTitle;
    private String hFormActionUrl;
    private String[] hList;
    private String[][] hDisplayList;
    private String hMType;


    public HTMLColumnListD() {
    }

    public String gethMType() {
        return hMType;
    }

    public void sethMType(String hMType) {
        this.hMType = hMType;
    }

    public String[][] gethDisplayList() {
        return hDisplayList;
    }

    public void sethDisplayList(String[][] hDisplayList) {
        this.hDisplayList = hDisplayList;
    }

    public String gethTitle() {
        return hTitle;
    }

    public String[] gethList() {
        return hList;
    }

    public void sethList(String[] hList) {
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

}
