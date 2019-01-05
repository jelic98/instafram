package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.TextField;
import javax.swing.*;

public class SwingTextField extends JTextField implements TextField {

    private ChangeObservableDelegate<Object> delegate;

    public SwingTextField() {
        delegate = new ChangeObservableDelegate<>();

        getDocument().addDocumentListener(new SwingDocumentAdapter() {
            @Override
            public void onUpdate() {
                notifyObservers(ChangeType.UPDATE, getText());
            }
        });
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