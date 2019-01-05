package org.ljelic.instafram.view.adapter.tab;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.ChangeObservable;
import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.command.*;
import org.ljelic.instafram.view.adapter.action.CompositeActionItem;
import org.ljelic.instafram.view.adapter.action.PopupMenuFactory;
import org.ljelic.instafram.view.component.Component;
import org.ljelic.instafram.view.component.TabPanel;
import java.util.LinkedList;
import java.util.List;

public class ObservableTabPanel implements ChangeObservable<TabItem> {

    private final ChangeObservableDelegate<TabItem> delegate;
    private final List<TabItem> items;
    private final TabPanel panel;

    ObservableTabPanel() {
        delegate = new ChangeObservableDelegate<>();
        items = new LinkedList<>();
        panel = Config.UI.getTabPanel();
        panel.setPopupMenu(new PopupMenuFactory().getPopupMenu(new CompositeActionItem[] {
                new CompositeActionItem(Res.STRINGS.MENU_CLOSE, new CloseTabAction(null, null) {
                    @Override
                    public void execute() {
                        removeItem(items.get(panel.getSelectedIndex()));
                    }
                })
        }));
    }

    public void addItem(TabItem item) {
        if(items.contains(item)) {
            return;
        }

        panel.add(item.getText(), item.getIcon(), item.getPanel());
        items.add(item);
    }

    public void removeItem(TabItem item) {
        int index = items.indexOf(item);

        if(index == -1) {
            return;
        }

        panel.removeAt(index);
        items.remove(item);

        notifyObservers(ChangeType.REMOVAL, item);
    }

    public Component getView() {
        return panel;
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, TabItem bundle) {
        delegate.notifyObservers(type, bundle);
    }
}