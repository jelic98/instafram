package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.PopupMenu;
import javax.swing.*;

public class SwingPopupMenu extends JPopupMenu implements PopupMenu {

    @Override
    public void addComponent(Component component) {
        add((java.awt.Component) component);
    }

    @Override
    public void show(int x, int y, Object parent) {
        show((java.awt.Component) parent, x, y);
    }
}