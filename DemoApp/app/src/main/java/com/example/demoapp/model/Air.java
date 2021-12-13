package com.example.demoapp.model;

public class Air {
    private String stt, pol, pod, dim, gross, typeOfCargo, oceanFreight, localCharge, carrier,
            schedule, transitTime, valid, note, month;

    public Air(String stt, String pol, String pod, String dim, String gross,
               String typeOfCargo, String oceanFreight, String localCharge, String carrier,
               String schedule, String transitTime, String valid, String note,
               String month) {
        this.stt = stt;
        this.pol = pol;
        this.pod = pod;
        this.dim = dim;
        this.gross = gross;
        this.typeOfCargo = typeOfCargo;
        this.oceanFreight = oceanFreight;
        this.localCharge = localCharge;
        this.carrier = carrier;
        this.schedule = schedule;
        this.transitTime = transitTime;
        this.valid = valid;
        this.note = note;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
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

    public String getOceanFreight() {
        return oceanFreight;
    }

    public void setOceanFreight(String oceanFreight) {
        this.oceanFreight = oceanFreight;
    }

    public String getLocalCharge() {
        return localCharge;
    }

    public void setLocalCharge(String localCharge) {
        this.localCharge = localCharge;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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
}
