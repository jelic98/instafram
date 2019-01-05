package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Frame;
import org.ljelic.instafram.view.component.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingFrame extends JFrame implements Frame {

    private ChangeObservableDelegate<Object> delegate;

    public SwingFrame() {
        delegate = new ChangeObservableDelegate<>();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notifyObservers(ChangeType.CLOSE, e.getNewState());
            }
        });
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, Object bundle) {
        delegate.notifyObservers(type, bundle);
    }

    @Override
    public void setIcon(String icon) {
        setIconImage(new ImageIcon(icon).getImage());
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(null);
        super.setVisible(b);
    }

    @Override
    public void setFocused(boolean focused) {
        if(focused) {
            requestFocus();
        }
    }

    @Override
    public void setMenuBar(MenuBar menuBar) {
        setJMenuBar((JMenuBar) menuBar);
    }

    @Override
    public int getInitialWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    @Override
    public int getInitialHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

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
    public void refresh() {
        revalidate();
        doLayout();
    }

    @Override
    public void close() {
        dispose();
    }
}