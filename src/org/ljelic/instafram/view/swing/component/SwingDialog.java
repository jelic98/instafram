package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Dialog;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class SwingDialog implements Dialog {

    @Override
    public void error(String message, String title, String icon) {
        showMessageDialog(null, message, title, ERROR_MESSAGE, new ImageIcon(icon));
    }

    @Override
    public void info(String message, String title, String icon) {
        showMessageDialog(null, message, title, INFORMATION_MESSAGE, new ImageIcon(icon));
    }

    @Override
    public int question(String message, String title, String icon, String[] options) {
        return showOptionDialog(null, message,
                title, DEFAULT_OPTION, PLAIN_MESSAGE,
                new ImageIcon(icon), options, options[0]);
    }

    @Override
    public int selection(String message, String title, String icon, String[] options, Component panel) {
        return showOptionDialog(null, panel,
                title, DEFAULT_OPTION, PLAIN_MESSAGE,
                new ImageIcon(icon), options, options[0]);
    }

    @Override
    public String input(String message, String title) {
        return JOptionPane.showInputDialog(null, message, title, PLAIN_MESSAGE);
    }
}