package com.example.demoapp.model;

public class PriceListContainerSale {
    private String id;
    private String pol;
    private String pod;
    private String of20;
    private String of40;
    private String surcharge20;
    private String surccharge40;
    private String line;
    private String notes;
    private String valid;
    private String type;

    public PriceListContainerSale(String id, String pol, String pod, String of20, String of40,
                                  String surcharge20, String surccharge40, String line,
                                  String notes, String valid, String type) {
        this.id = id;
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.surcharge20 = surcharge20;
        this.surccharge40 = surccharge40;
        this.line = line;
        this.notes = notes;
        this.valid = valid;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getOf20() {
        return of20;
    }

    public void setOf20(String of20) {
        this.of20 = of20;
    }

    public String getOf40() {
        return of40;
    }

    public void setOf40(String of40) {
        this.of40 = of40;
    }

    public String getSurcharge20() {
        return surcharge20;
    }

    public void setSurcharge20(String surcharge20) {
        this.surcharge20 = surcharge20;
    }

    public String getSurccharge40() {
        return surccharge40;
    }

    public void setSurccharge40(String surccharge40) {
        this.surccharge40 = surccharge40;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
