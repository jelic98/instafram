package org.ljelic.instafram.view.component;

public interface Dialog {

    void error(String message, String title, byte[] icon);
    void info(String message, String title, byte[] icon);
    int question(String message, String title, byte[] icon, String[] options);
    int selection(String message, String title, byte[] icon, String[] options, Component panel);
    String input(String message, String title);
}