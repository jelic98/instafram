package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.util.ObservableSet;
import org.ljelic.instafram.view.adapter.tab.TabItem;
import org.ljelic.instafram.view.component.Node;

public class CloseTabAction extends Command {

    private Node node;
    private ObservableSet<TabItem> tabs;

    public CloseTabAction(Node node, ObservableSet<TabItem> tabSet) {
        this.node = node;
        this.tabs = tabSet;
    }

    @Override
    public void execute() {
        if(node == null) {
            node = Transfer.instance().tree.getSelectedNode();
        }

        if(node == null) {
            return;
        }

        TabItem closingTab = null;

        for(TabItem tab : tabs) {
            if(tab.getModel() == node) {
               closingTab = tab;
               break;
            }
        }

        if(closingTab != null) {
            this.tabs.delete(closingTab);
        }

        node = null;
    }
}