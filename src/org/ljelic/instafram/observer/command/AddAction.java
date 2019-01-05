package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.model.*;
import org.ljelic.instafram.util.Bundle;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Question;
import org.ljelic.instafram.view.component.Node;

public class AddAction extends UndoableCommand {

    private AbstractModel parent, child;
    private boolean firstLevel;

    public AddAction(AbstractModel parent) {
        this(parent, true);
    }

    public AddAction(AbstractModel parent, boolean firstLevel) {
        this.parent = parent;
        this.firstLevel = firstLevel;

        setStatus(Res.STRINGS.STATUS_ADD);
    }

    @Override
    void execute() {
        if(!Config.MODEL.get(Parameters.SESSION_USER).equals(User.ADMINISTRATOR.name())) {
            DialogAdapter.error(Res.STRINGS.ERROR_RESTRICTED_ACCESS);
            return;
        }

        if(parent == null || !firstLevel) {
            Node selectedNode = Transfer.instance().tree.getSelectedNode();

            if(selectedNode != null) {
                parent = (AbstractModel) selectedNode.getDelegateModel();
            }
        }

        if(parent == null) {
            return;
        }

        final String prefix = parent.getChildType() + "_";

        int i = 1;

        child = parent.getChild(prefix + i);

        if(child instanceof Parameter) {
            ParameterType[] types = ParameterType.values();
            String[] options = new String[types.length];

            for(int j = 0; j < types.length; j++) {
                options[j] = types[j].name().replace('_', ' ');
            }

            Bundle result = new Bundle(options[0]);

            DialogAdapter.selection(new Question(Res.STRINGS.QUESTION_PARAMETER_TYPE)
                    .addOption(Res.STRINGS.OPTION_OK, new Command() {
                        @Override
                        void execute() {
                            try {
                                Parameter tmp = ParameterType.valueOf(result.getValue().toString()).getParameter();
                                tmp.setOrder(((Parameter) child).getOrder());

                                child = tmp;

                                addNode(prefix, i);
                            }catch(Exception e) {
                                child = null;
                            }
                        }
                    })
                    .addOption(Res.STRINGS.OPTION_CANCEL),
                    options, result);

            return;
        }

        addNode(prefix, i);
    }

    private void addNode(String prefix, int i) {
        if(child == null) {
            return;
        }

        while(!parent.addChild(child)) {
            child.setName(prefix + ++i);
        }

        Transfer.instance().tree.reload();
    }

    @Override
    public void revert() {
        CommandQueue.push(new RemoveAction(child)
                .setState(ExecutionState.REDO)
                .skipHistory());
    }

    @Override
    public UndoableCommand getClone() {
        AddAction clone = new AddAction(parent, firstLevel);
        clone.child = child;
        clone.setState(getState());
        clone.skipHistory(isSkipHistory());

        return clone;
    }
}