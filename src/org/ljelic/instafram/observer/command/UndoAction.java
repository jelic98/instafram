package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;

public class UndoAction extends Command {

    public UndoAction() {
        setStatus(Res.STRINGS.STATUS_UNDO);
    }

    @Override
    void execute() {
        CommandQueue.undo();
    }
}