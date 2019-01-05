package org.ljelic.instafram.view.swing.component;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.view.component.ComboBox;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SwingComboBox<T> extends JComboBox<T> implements ComboBox<T> {

    private ChangeObservableDelegate<Object> delegate;

    public SwingComboBox() {
        delegate = new ChangeObservableDelegate<>();

        addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                notifyObservers(ChangeType.SELECTION, e.getItem());
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
    public void setItems(T[] items) {
        for(T item : items) {
            addItem(item);
        }
    }

    @Override
    public void clear() {
        removeAllItems();
    }
}