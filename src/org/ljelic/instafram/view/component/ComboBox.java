package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface ComboBox<T> extends Component, ChangeObservable<Object> {

    void setItems(T[] items);
    void addItem(T item);
    void clear();
    Object getSelectedItem();
    void setEnabled(boolean enabled);
}