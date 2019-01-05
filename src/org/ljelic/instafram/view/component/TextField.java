package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface TextField extends Component, ChangeObservable<String> {

    String getText();
    void setText(String text);
    void setColumns(int columns);
    void setEditable(boolean editable);
}