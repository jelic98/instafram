package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.util.ObservableSet;
import org.ljelic.instafram.view.adapter.tab.ParameterTab;
import org.ljelic.instafram.view.adapter.tab.TextTab;
import org.ljelic.instafram.view.adapter.tab.TabItem;
import org.ljelic.instafram.view.component.Node;
import org.ljelic.instafram.view.component.ScrollPanel;

public class DetailsAction extends Command {

    private AbstractModel node;
    private ObservableSet<TabItem> tabs;

    public DetailsAction(AbstractModel node, ObservableSet<TabItem> tabs) {
        this.node = node;
        this.tabs = tabs;
    }

    public DetailsAction(ObservableSet<TabItem> tabs) {
        this(null, tabs);
    }

    @Override
    void execute() {
        if(node == null) {
            Node selectedNode = Transfer.instance().tree.getSelectedNode();

            if(selectedNode != null) {
                node = (AbstractModel) selectedNode.getDelegateModel();
            }
        }

        if(node == null) {
            return;
        }

        ScrollPanel panel = Config.UI.getScrollPanel();

        try {
            panel.addComponent(new ParameterTab((Parameter) node).getView());
        }catch(ClassCastException e) {
            panel.addComponent(new TextTab(node).getView());
        }

        tabs.add(new TabItem(node, panel));

        node = null;
    }
}