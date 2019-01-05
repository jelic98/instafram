package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.model.*;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.component.Node;
import java.util.*;

public class PasteAction extends UndoableCommand {

    private List<Node> currentNodes;
    private List<List<Node>> pastedNodes;

    public PasteAction() {
        this(new LinkedList<>());
    }

    PasteAction(List<Node> pastedNodes) {
        this.pastedNodes = new LinkedList<>();
        this.pastedNodes.add(0, pastedNodes);

        setStatus(Res.STRINGS.STATUS_PASTE);
    }

    @Override
    void execute() {
        currentNodes = new LinkedList<>();

        List<Node> children;

        AbstractModel parent = null;

        if(getState() == ExecutionState.REDO) {
            children = pastedNodes.get(0);
            pastedNodes.remove(0);
        }else {
            Node selectedNode = Transfer.instance().tree.getSelectedNode();

            if(selectedNode != null) {
                parent = (AbstractModel) selectedNode.getDelegateModel();
            }

            if(parent != null && !(parent instanceof Product) && !(parent instanceof Module)) {
                return;
            }

            children = Transfer.instance().tree.getClipboardNodes();
        }

        for(Node child : children) {
            if(!(child.getDelegateModel() instanceof Parameter)) {
                DialogAdapter.error(Res.STRINGS.ERROR_ACTION_FOR_PARAMETERS);
                continue;
            }

            handle(parent == null ? child.getAncestor() : parent, child);
        }

        if(!currentNodes.isEmpty()) {
            pastedNodes.add(0, currentNodes);
        }

        Transfer.instance().tree.reload();
    }

    private void handle(Node parent, Node child) {
        Node clone = ((AbstractModel) child.getDelegateModel()).getClone();

        copyChildren(child, clone);

        parent.addChild(clone);

        currentNodes.add(clone);
    }

    private void copyChildren(Node src, Node dest) {
        for(Node child : src.getChildren()) {
            Node model = child.getDelegateModel();

            if(!(model instanceof AbstractModel)) {
                continue;
            }

            Node clone = ((AbstractModel) model).getClone();

            dest.addChild(clone);

            copyChildren(child, clone);
        }
    }

    @Override
    public void revert() {
        CommandQueue.push(new CutAction(pastedNodes.get(0))
                .setState(ExecutionState.REDO)
                .skipHistory());
    }

    @Override
    public UndoableCommand getClone() {
        return (UndoableCommand) new PasteAction(pastedNodes.get(0))
                .setState(getState())
                .skipHistory(isSkipHistory());
    }
}