package com.example.demoapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fcl implements Serializable {
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

    @SerializedName("su20")
    @Expose
    private String su20;

    @SerializedName("su40")
    @Expose
    private String su40;

    @SerializedName("linelist")
    @Expose
    private String linelist;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("valid")
    @Expose
    private String valid;

    @SerializedName("notes2")
    @Expose
    private String notes2;

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
     * Constructor
     *
     * @param stt      stt
     * @param pol      pol
     * @param pod      pod
     * @param of20     ocean freight 20
     * @param of40     ocean freight 40
     * @param su20     surcharge 20
     * @param su40     surcharge 40
     * @param linelist linelist
     * @param notes    notes 1
     * @param valid    valid
     * @param notes2   notes2
     */
    public Fcl(String stt, String pol, String pod, String of20, String of40, String su20,
               String su40, String linelist, String notes, String valid, String notes2) {
        this.stt = stt;
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.su20 = su20;
        this.su40 = su40;
        this.linelist = linelist;
        this.notes = notes;
        this.valid = valid;
        this.notes2 = notes2;
    }

    public Fcl( String pol, String pod, String of20, String of40, String su20,
               String su40, String linelist, String notes, String valid, String notes2) {
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.su20 = su20;
        this.su40 = su40;
        this.linelist = linelist;
        this.notes = notes;
        this.valid = valid;
        this.notes2 = notes2;
    }

    /**
     * Constructor
     *
     * @param pol      pol
     * @param pod      pod
     * @param of20     ocean freight 20
     * @param of40     ocean freight 40
     * @param su20     surcharge 20
     * @param su40     surcharge 40
     * @param linelist linelist
     * @param notes    notes 1
     * @param valid    valid
     * @param notes2   notes2
     * @param type type of container
     * @param month month
     * @param continent continent
     */
    public Fcl(String pol, String pod, String of20, String of40,
               String su20, String su40, String linelist, String notes, String valid,
               String notes2, String type, String month, String continent) {
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.su20 = su20;
        this.su40 = su40;
        this.linelist = linelist;
        this.notes = notes;
        this.valid = valid;
        this.notes2 = notes2;
        this.type = type;
        this.month = month;
        this.continent = continent;
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

    public String getSu20() {
        return su20;
    }

    public void setSu20(String su20) {
        this.su20 = su20;
    }

    public String getSu40() {
        return su40;
    }

    public void setSu40(String su40) {
        this.su40 = su40;
    }

    public String getLinelist() {
        return linelist;
    }

    public void setLinelist(String linelist) {
        this.linelist = linelist;
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

    public String getNotes2() {
        return notes2;
    }

    public void setNotes2(String notes2) {
        this.notes2 = notes2;
    }

    public String getType() {
        return type;
    }
}
