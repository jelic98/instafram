package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface CheckBox extends Component, ChangeObservable<Object> {

    boolean isChecked();
    void setChecked(boolean checked);
    void setEnabled(boolean enabled);
}