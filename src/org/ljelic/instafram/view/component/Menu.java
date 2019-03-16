package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface Menu extends Component, ChangeObservable<Object> {

    void addComponent(Component component);
    void addSeparator();
    void setText(String text);
    void setIcon(byte[] icon);
    void setMnemonic(char mnemonic);
}