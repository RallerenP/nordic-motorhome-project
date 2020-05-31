package com.nordicmotorhome.motorhomerentals.domain.orderlines;

public class OrderLine {
    private String key;
    private Object value;

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
