package org.ljelic.instafram.view.component;

public interface Dialog {

    void error(String message, String title, String icon);
    void info(String message, String title, String icon);
    int question(String message, String title, String icon, String[] options);
    int selection(String message, String title, String icon, String[] options, Component panel);
    String input(String message, String title);
}