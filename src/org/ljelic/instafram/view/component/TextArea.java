package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface TextArea extends Component, ChangeObservable<String> {

    String getText();
    void setText(String text);
    void setRows(int rows);
    void setColumns(int columns);
    void setEditable(boolean editable);
}