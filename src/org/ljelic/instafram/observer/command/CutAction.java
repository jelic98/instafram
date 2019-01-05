package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.component.Node;
import java.util.*;

public class CutAction extends UndoableCommand {

    private List<Node> currentNodes;
    private List<List<Node>> cutNodes;

    public CutAction() {
        this(new LinkedList<>());
    }

    CutAction(List<Node> cutNodes) {
        this.cutNodes = new LinkedList<>();
        this.cutNodes.add(0, cutNodes);

        setStatus(Res.STRINGS.STATUS_CUT);
    }

    @Override
    void execute() {
        Transfer.instance().tree.clearClipboard();

        currentNodes = new LinkedList<>();

        List<Node> children;

        if(getState() == ExecutionState.REDO) {
            children = cutNodes.get(0);
            cutNodes.remove(0);
        }else {
            children = Transfer.instance().tree.getSelectedNodes();
        }

        for(Node child : children) {
            if(!(child.getDelegateModel() instanceof Parameter)) {
                DialogAdapter.error(Res.STRINGS.ERROR_ACTION_FOR_PARAMETERS);
                continue;
            }

            handle(child);
        }

        if(!currentNodes.isEmpty()) {
            cutNodes.add(0, currentNodes);
        }

        Transfer.instance().tree.reload();
    }

    private void handle(Node selected) {
        Transfer.instance().tree.addClipboardNode(selected);

        selected.removeFromAncestor();

        currentNodes.add(selected);
    }

    @Override
    public void revert() {
        CommandQueue.push(new PasteAction(cutNodes.get(0))
                .setState(ExecutionState.REDO)
                .skipHistory());
    }

    @Override
    public UndoableCommand getClone() {
        return (UndoableCommand) new CutAction(cutNodes.get(0))
                .setState(getState())
                .skipHistory(isSkipHistory());
    }
}