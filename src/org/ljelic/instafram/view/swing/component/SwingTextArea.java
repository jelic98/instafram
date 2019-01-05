package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.TextArea;
import javax.swing.*;

public class SwingTextArea extends JTextArea implements TextArea {

    private ChangeObservableDelegate<Object> delegate;

    public SwingTextArea() {
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