package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingPanel extends JPanel implements Panel {

    @Override
    public void addComponent(Component component) {
        add((java.awt.Component) component);
    }

    @Override
    public void addComponent(Component component, org.ljelic.instafram.view.component.Label.Position position) {
        switch(position) {
            case TOP:
                add((java.awt.Component) component, BorderLayout.NORTH);
                break;
            case BOTTOM:
                add((java.awt.Component) component, BorderLayout.SOUTH);
                break;
            case LEFT:
                add((java.awt.Component) component, BorderLayout.WEST);
                break;
            case RIGHT:
                add((java.awt.Component) component, BorderLayout.EAST);
                break;
            case CENTER:
                add((java.awt.Component) component, BorderLayout.CENTER);
                break;
        }
    }

    @Override
    public void setLayout(Layout layout) {
        switch(layout) {
            case EXPAND:
                setLayout(new BorderLayout());
                break;
            case SHRINK:
                setLayout(new GridLayout());
                break;
            case NORMAL:
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                break;
        }
    }

    @Override
    public void setPadding(int top, int right, int bottom, int left) {
        setBorder(new EmptyBorder(top, left, bottom, right));
    }

    @Override
    public void clear() {
        removeAll();
    }
}