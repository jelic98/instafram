package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Question;
import org.ljelic.instafram.view.component.Node;

public class SaveAction extends Command {

    private final AbstractModel rootNode;
    private String path;

    public SaveAction(AbstractModel rootNode) {
        this(rootNode, null);
    }

    public SaveAction(AbstractModel rootNode, String path) {
        this.rootNode = rootNode;
        this.path = path;

        setStatus(Res.STRINGS.STATUS_SAVE);
    }

    @Override
    void execute() {
        DialogAdapter.question(new Question(Res.STRINGS.QUESTION_SAVE)
                .addOption(Res.STRINGS.OPTION_YES, new Command() {
                    @Override
                    void execute() {
                        saveState();
                    }
                })
                .addOption(Res.STRINGS.OPTION_NO));
    }

    private void saveState() {
        Node selectedNode = Transfer.instance().tree.getSelectedNode();
        AbstractModel node = null;

        if(selectedNode != null) {
            node = (AbstractModel) selectedNode.getDelegateModel();
        }

        if(node == null || path != null) {
            node = rootNode;
        }

        if(node == null) {
            return;
        }

        if(path == null) {
            path = Paths.getStatePath();
        }

        try {
            Config.STREAM.write(Config.STREAMABLE.from(node), path);
        }catch(Exception e) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_WRITE);
        }

        Transfer.instance().tree.reload();
    }
}