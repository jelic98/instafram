package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.PasswordField;

import javax.swing.*;

public class SwingPasswordField extends JPasswordField implements PasswordField {

    private ChangeObservableDelegate<Object> delegate;

    public SwingPasswordField() {
        delegate = new ChangeObservableDelegate<>();
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, String bundle) {
        delegate.notifyObservers(type, bundle);
    }
}