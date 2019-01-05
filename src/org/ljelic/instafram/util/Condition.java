package org.ljelic.instafram.util;

public class Condition {

    private boolean value;

    public Condition(boolean value) {
        this.value = value;
    }

    public boolean isTrue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}