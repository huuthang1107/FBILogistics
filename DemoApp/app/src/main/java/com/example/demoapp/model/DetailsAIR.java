package com.example.demoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsAIR {
    @SerializedName("stt")
    @Expose
    private String stt;
    @SerializedName("aol")
    @Expose
    private String aol;
    @SerializedName("aod")
    @Expose
    private String aod;
    @SerializedName("dim")
    @Expose
    private String dim;
    @SerializedName("grossweight")
    @Expose
    private String grossweight;
    @SerializedName("typeofcargo")
    @Expose
    private String typeofcargo;
    @SerializedName("airfreight")
    @Expose
    private String airfreight;
    @SerializedName("surcharge")
    @Expose
    private String surcharge;
    @SerializedName("airlines")
    @Expose
    private String airlines;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("transittime")
    @Expose
    private String transittime;
    @SerializedName("valid")
    @Expose
    private String valid;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("month")
    @Expose
    private  String month;
    @SerializedName("continent")
    @Expose
    private  String continent;

    public DetailsAIR(String stt, String aol, String aod, String dim, String grossweight,
                      String typeofcargo, String airfreight, String surcharge, String airlines,
                      String schedule, String transittime, String valid, String note, String month,
                      String continent) {
        this.stt = stt;
        this.aol = aol;
        this.aod = aod;
        this.dim = dim;
        this.grossweight = grossweight;
        this.typeofcargo = typeofcargo;
        this.airfreight = airfreight;
        this.surcharge = surcharge;
        this.airlines = airlines;
        this.schedule = schedule;
        this.transittime = transittime;
        this.valid = valid;
        this.note = note;
        this.month = month;
        this.continent = continent;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getAol() {
        return aol;
    }

    public void setAol(String aol) {
        this.aol = aol;
    }

    public String getAod() {
        return aod;
    }

    public void setAod(String aod) {
        this.aod = aod;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    public String getGrossweight() {
        return grossweight;
    }

    public void setGrossweight(String grossweight) {
        this.grossweight = grossweight;
    }

    public String getTypeofcargo() {
        return typeofcargo;
    }

    public void setTypeofcargo(String typeofcargo) {
        this.typeofcargo = typeofcargo;
    }

    public String getAirfreight() {
        return airfreight;
    }

    public void setAirfreight(String airfreight) {
        this.airfreight = airfreight;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTransittime() {
        return transittime;
    }

    public void setTransittime(String transittime) {
        this.transittime = transittime;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
