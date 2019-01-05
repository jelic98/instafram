package org.ljelic.instafram.view.adapter.tab;

import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.util.ObservableSet;
import org.ljelic.instafram.view.component.Component;

public class TabManager {

    private final ObservableTabPanel panel;

    public TabManager() {
        panel = new ObservableTabPanel();
    }

    public void addObserver(ChangeObserver observer) {
        panel.addObserver(observer);
    }

    public void addTab(TabItem tab) {
        panel.addItem(tab);
    }

    public void removeTab(TabItem tab) {
        panel.removeItem(tab);
    }

    public Component getPanel() {
        return panel.getView();
    }
}