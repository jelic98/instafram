package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.Button;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingButton extends JButton implements Button {

    private ChangeObservableDelegate<Object> delegate;

    public SwingButton() {
        delegate = new ChangeObservableDelegate<>();

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    public void setHint(String hint) {
        setToolTipText(hint);
    }

    @Override
    public void setIcon(String icon) {
        setIcon(new ImageIcon(icon));
    }
}