package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.model.ModelUtils;
import org.ljelic.instafram.util.ObservableSet;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Question;
import org.ljelic.instafram.view.adapter.tab.TabItem;
import org.ljelic.instafram.view.component.Node;
import org.ljelic.instafram.view.component.Tree;

public class RemoveAction extends UndoableCommand {

    private ObservableSet<TabItem> tabs;
    private AbstractModel node;

    public RemoveAction(ObservableSet<TabItem> tabs) {
        this.tabs = tabs;

        setStatus(Res.STRINGS.STATUS_REMOVE);
    }

    public RemoveAction(AbstractModel node) {
        this.node = node;

        setStatus(Res.STRINGS.STATUS_REMOVE);
    }

    @Override
    void execute() {
        if(!Config.MODEL.get(Parameters.SESSION_USER).equals(User.ADMINISTRATOR.name())) {
            DialogAdapter.error(Res.STRINGS.ERROR_RESTRICTED_ACCESS);
            return;
        }

        if(node == null) {
            Node selectedNode = Transfer.instance().tree.getSelectedNode();

            if(selectedNode != null) {
                node = (AbstractModel) selectedNode.getDelegateModel();
            }
        }

        if(node == null) {
            return;
        }

        ModelUtils utils = new ModelUtils();

        DialogAdapter.question(new Question(Res.STRINGS.QUESTION_REMOVE
                .replace("<<CHILD_COUNT>>", String.valueOf(utils.getChildCount(node)))
                .replace("<<LEAF_COUNT>>", String.valueOf(utils.getLeafCount(node))))
                .addOption(Res.STRINGS.OPTION_YES, new Command() {
                    @Override
                    void execute() {
                        removeNode();
                    }
                }.skipHistory(isSkipHistory()))
                .addOption(Res.STRINGS.OPTION_NO));
    }

    @Override
    public void revert() {
        CommandQueue.push(new AddAction(node)
                .setState(ExecutionState.REDO)
                .skipHistory());
    }

    @Override
    public UndoableCommand getClone() {
        RemoveAction clone = new RemoveAction(tabs);
        clone.node = node;
        clone.setState(getState());
        clone.skipHistory(isSkipHistory());

        return clone;
    }

    private void removeNode() {
        closeTab(node);
        removeFromTree(node);
    }

    private void closeTab(AbstractModel node) {
        if(tabs == null) {
            return;
        }

        CommandQueue.push(new CloseTabAction(node, tabs).skipHistory(isSkipHistory()));

        for(Node child : node.getChildren()) {
            Node model = child.getDelegateModel();

            if(model instanceof AbstractModel) {
                closeTab((AbstractModel) model);
            }
        }
    }

    private void removeFromTree(AbstractModel node) {
        Tree tree = Transfer.instance().tree;

        if(node.getAncestor() != null) {
            node.removeFromAncestor();
        }else {
            if(node == tree.getRoot()) {
                tree.removeChildren();
            }
        }

        tree.reload();
    }
}