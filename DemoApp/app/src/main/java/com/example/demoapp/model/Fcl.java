package com.example.demoapp.model;

public class Fcl {
    String stt, pol, pod, of20, of40, su20, su40, lines, notes1, valid, notes2, month, type;

    public Fcl(String stt, String pol, String pod, String of20, String of40, String su20,
               String su40, String lines, String notes1, String valid, String notes2,
               String month, String type) {
        this.stt = stt;
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.su20 = su20;
        this.su40 = su40;
        this.lines = lines;
        this.notes1 = notes1;
        this.valid = valid;
        this.notes2 = notes2;
        this.month = month;
        this.type = type;
    }

    public Fcl(String stt, String pol, String pod, String of20, String of40, String su20, String su40, String lines, String notes1, String valid, String notes2) {
        this.stt = stt;
        this.pol = pol;
        this.pod = pod;
        this.of20 = of20;
        this.of40 = of40;
        this.su20 = su20;
        this.su40 = su40;
        this.lines = lines;
        this.notes1 = notes1;
        this.valid = valid;
        this.notes2 = notes2;
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

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getNotes1() {
        return notes1;
    }

    public void setNotes1(String notes1) {
        this.notes1 = notes1;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
