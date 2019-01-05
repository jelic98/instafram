package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.MenuBar;
import javax.swing.*;

public class SwingMenuBar extends JMenuBar implements MenuBar {

    @Override
    public void addComponent(Component component) {
        add((java.awt.Component) component);
    }

    @Override
    public void addGlue() {
        add(Box.createHorizontalGlue());
    }
}