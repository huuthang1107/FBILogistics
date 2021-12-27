package com.example.demoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Import implements Serializable {
    @SerializedName("stt")
    @Expose
    private String stt;

    @SerializedName("pol")
    @Expose
    private String pol;

    @SerializedName("pod")
    @Expose
    private String pod;

    @SerializedName("of20")
    @Expose
    private String of20;

    @SerializedName("of40")
    @Expose
    private String of40;

    @SerializedName("surcharge")
    @Expose
    private String surcharge;

    @SerializedName("total_freight")
    @Expose
    private String totalFreight;

    @SerializedName("carrier")
    @Expose
    private String carrier;

    @SerializedName("schedule")
    @Expose
    private String schedule;

    @SerializedName("transit_time")
    @Expose
    private String transitTime;

    @SerializedName("free_time")
    @Expose
    private String freeTime;

    @SerializedName("valid")
    @Expose
    private String valid;

    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("continent")
    @Expose
    private String continent;

    /**
     *
     * @param stt stt
     * @param pol pol
     * @param pod pod
     * @param of20 ocean freight 20
     * @param of40 ocean freight 40
     * @param surcharge surcharge
     * @param totalFreight total freight
     * @param carrier carrier
     * @param schedule schedule
     * @param transitTime transit time
     * @param freeTime free time
     * @param valid valid
     * @param note note
     * @param type type
     * @param month month
     * @param continent continent
     */
    public Import(String stt, String pol, String pod, String of20, String of40, String surcharge, String totalFreight, String carrier, String schedule, String transitTime, String freeTime, String valid, String note, String type, String month, String continent) {
        this.stt = stt;
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.surcharge = surcharge;
        this.totalFreight = totalFreight;
        this.carrier = carrier;
        this.schedule = schedule;
        this.transitTime = transitTime;
        this.freeTime = freeTime;
        this.valid = valid;
        this.note = note;
        this.type = type;
        this.month = month;
        this.continent = continent;
    }

    /**
     * Constructor
     * @param pol pol
     * @param pod pod
     * @param of20 ocean freight 20
     * @param of40 ocean freight 40
     * @param surcharge surcharge
     * @param totalFreight total freight
     * @param carrier carrier
     * @param schedule schedule
     * @param transitTime transit
     * @param freeTime free time
     * @param valid valid
     * @param note note
     * @param type type
     * @param month month
     * @param continent continent
     */
    public Import(String pol, String pod, String of20, String of40, String surcharge,
                  String totalFreight, String carrier, String schedule, String transitTime,
                  String freeTime, String valid, String note, String type, String month, String continent) {
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.surcharge = surcharge;
        this.totalFreight = totalFreight;
        this.carrier = carrier;
        this.schedule = schedule;
        this.transitTime = transitTime;
        this.freeTime = freeTime;
        this.valid = valid;
        this.note = note;
        this.type = type;
        this.month = month;
        this.continent = continent;
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

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(String totalFreight) {
        this.totalFreight = totalFreight;
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

    public String getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
