package org.ljelic.instafram.view.adapter.action;

public class BarItem {

    private final String text;
    private byte[] icon;
    private char mnemonic;

    BarItem(String text) {
        this.text = text;
    }

    BarItem(String text, char mnemonic) {
        this.text = text;
        this.mnemonic = mnemonic;
    }

    protected BarItem(String text, byte[] icon) {
        this.text = text;
        this.icon = icon;
    }

    BarItem(String text, byte[] icon, char mnemonic) {
        this.text = text;
        this.icon = icon;
        this.mnemonic = mnemonic;
    }

    public String getText() {
        return text;
    }

    public byte[] getIcon() {
        return icon;
    }

    public char getMnemonic() {
        return mnemonic;
    }

    public char getAccelerator() {
        return mnemonic;
    }
}