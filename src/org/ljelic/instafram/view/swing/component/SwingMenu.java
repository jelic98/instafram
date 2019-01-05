package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.Menu;
import javax.swing.*;
import javax.swing.event.MenuEvent;

public class SwingMenu extends JMenu implements Menu {

    private ChangeObservableDelegate<Object> delegate;

    public SwingMenu() {
        delegate = new ChangeObservableDelegate<>();

        addMenuListener(new SwingMenuAdapter() {
            @Override
            public void menuSelected(MenuEvent e) {
                notifyObservers(ChangeType.ACTIVATE, e.getSource());
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
    public void addComponent(Component component) {
        add((java.awt.Component) component);
    }

    @Override
    public void setIcon(String icon) {
        setIcon(new ImageIcon(icon));
    }
}