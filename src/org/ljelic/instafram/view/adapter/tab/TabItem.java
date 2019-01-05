package org.ljelic.instafram.view.adapter.tab;

import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.view.adapter.action.BarItem;
import org.ljelic.instafram.view.component.ScrollPanel;

public class TabItem extends BarItem implements Comparable {

    private final AbstractModel model;
    private final ScrollPanel panel;

    public TabItem(AbstractModel model, ScrollPanel panel) {
        super(model.getName(), model.getIcon());
        this.model = model;
        this.panel = panel;
    }

    public AbstractModel getModel() {
        return model;
    }

    public ScrollPanel getPanel() {
        return panel;
    }

    @Override
    public int compareTo(Object obj) {
        return equals(obj) ? 0 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof TabItem)) {
            return false;
        }

        TabItem item = (TabItem) obj;

        return getModel().equals(item.getModel());
    }
}