package com.example.lin9080.fzuscorey;

public class Monitor {
    private String mon_id;
    private String mon_pas;

    public Monitor() {
    }

    public Monitor(String mon_id, String mon_pas) {
        this.mon_id = mon_id;
        this.mon_pas = mon_pas;
    }

    public String getMon_id() {
        return mon_id;
    }

    public void setMon_id(String mon_id) {
        this.mon_id = mon_id;
    }

    public String getMon_pas() {
        return mon_pas;
    }

    public void setMon_pas(String mon_pas) {
        this.mon_pas = mon_pas;
    }
}
