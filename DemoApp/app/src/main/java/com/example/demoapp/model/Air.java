package com.example.demoapp.model;

public class Air {
    private String stt, aol, aod, dim, gross, typeOfCargo, airFreight, surcharge , airLines,
            schedule, transitTime, valid, note, month, continent;

    public Air(String stt, String aol, String aod, String dim, String gross, String typeOfCargo,
               String airFreight, String surcharge, String airLines, String schedule,
               String transitTime, String valid, String note, String month, String continent) {
        this.stt = stt;
        this.aol = aol;
        this.aod = aod;
        this.dim = dim;
        this.gross = gross;
        this.typeOfCargo = typeOfCargo;
        this.airFreight = airFreight;
        this.surcharge = surcharge;
        this.airLines = airLines;
        this.schedule = schedule;
        this.transitTime = transitTime;
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

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getTypeOfCargo() {
        return typeOfCargo;
    }

    public void setTypeOfCargo(String typeOfCargo) {
        this.typeOfCargo = typeOfCargo;
    }

    public String getAirFreight() {
        return airFreight;
    }

    public void setAirFreight(String airFreight) {
        this.airFreight = airFreight;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getAirLines() {
        return airLines;
    }

    public void setAirLines(String airLines) {
        this.airLines = airLines;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(String transitTime) {
        this.transitTime = transitTime;
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
