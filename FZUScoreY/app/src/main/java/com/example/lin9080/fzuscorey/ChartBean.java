package com.example.lin9080.fzuscorey;

public class ChartBean {
    private int value;
    private String valName;

    public ChartBean(int value, String valName) {
        this.value = value;
        this.valName = valName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getValName() {
        return valName;
    }

    public void setValName(String valName) {
        this.valName = valName;
    }
}