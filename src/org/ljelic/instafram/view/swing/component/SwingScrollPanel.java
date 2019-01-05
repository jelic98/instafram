package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.ScrollPanel;
import javax.swing.*;

public class SwingScrollPanel extends JScrollPane implements ScrollPanel {

    @Override
    public void addComponent(Component component) {
        setViewportView((java.awt.Component) component);
    }
}