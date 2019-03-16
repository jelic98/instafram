package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface Button extends Component, ChangeObservable<Object> {

    void setText(String text);
    void setHint(String hint);
    void setIcon(byte[] icon);
    void setEnabled(boolean enabled);
}