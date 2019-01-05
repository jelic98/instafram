package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;

public class RedoAction extends Command {

    public RedoAction() {
        setStatus(Res.STRINGS.STATUS_REDO);
    }

    @Override
    void execute() {
        CommandQueue.redo();
    }
}