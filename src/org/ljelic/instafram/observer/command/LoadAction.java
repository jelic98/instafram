package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Question;
import org.ljelic.instafram.view.component.Node;

public class LoadAction extends Command {

    private final AbstractModel rootNode;
    private final boolean forceLoad;
    private String path;

    public LoadAction(AbstractModel rootNode) {
        this(rootNode, null, false);
    }

    public LoadAction(AbstractModel rootNode, String path) {
        this(rootNode, path, false);
    }

    public LoadAction(AbstractModel rootNode, boolean forceLoad) {
        this(rootNode, null, forceLoad);
    }

    public LoadAction(AbstractModel rootNode, String path, boolean forceLoad) {
        this.rootNode = rootNode;
        this.forceLoad = forceLoad;
        this.path = path;

        setStatus(Res.STRINGS.STATUS_LOAD);
    }

    @Override
    void execute() {
        if(forceLoad) {
            loadState();
            return;
        }

        DialogAdapter.question(new Question(Res.STRINGS.QUESTION_LOAD)
                .addOption(Res.STRINGS.OPTION_YES, new Command() {
                    @Override
                    void execute() {
                        loadState();
                    }
                })
                .addOption(Res.STRINGS.OPTION_NO));
    }

    private void loadState() {
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
            Config.STREAM.read(Config.STREAMABLE.from(node), path);
        }catch(Exception e) {
            DialogAdapter.error(Res.STRINGS.ERROR_CANNOT_READ);
        }

        Transfer.instance().tree.reload();
    }
}