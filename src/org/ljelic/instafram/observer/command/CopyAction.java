package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.view.component.Node;

public class CopyAction extends Command {

    public CopyAction() {
        setStatus(Res.STRINGS.STATUS_COPY);
    }

    @Override
    void execute() {
        Transfer.instance().tree.clearClipboard();

        for(Node selected : Transfer.instance().tree.getSelectedNodes()) {
            Transfer.instance().tree.addClipboardNode(selected);
        }
    }
}