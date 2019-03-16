package org.ljelic.instafram.view.component;

import org.ljelic.instafram.observer.ChangeObservable;

public interface Frame extends ChangeObservable<Object> {

    void setTitle(String title);
    void setIcon(byte[] icon);
    void setSize(int width, int height);
    void setResizable(boolean resizable);
    void setVisible(boolean visible);
    void setFocused(boolean focused);
    void setMenuBar(MenuBar menuBar);
    int getInitialWidth();
    int getInitialHeight();
    void addComponent(Component component);
    void addComponent(Component component, Label.Position position);
    void refresh();
    void pack();
    void close();
}