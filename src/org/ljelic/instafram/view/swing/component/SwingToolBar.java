package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.ToolBar;
import javax.swing.*;

public class SwingToolBar extends JToolBar implements ToolBar {

    @Override
    public void addComponent(Component component) {
        add((java.awt.Component) component);
    }
}