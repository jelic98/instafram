package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface MenuItem extends Component, ChangeObservable<Object> {

    void addComponent(Component component);
    void setText(String text);
    void setIcon(byte[] icon);
    void setMnemonic(char mnemonic);
    void setAccelerator(char accelerator);
    void setPadding(int top, int right, int bottom, int left);
}