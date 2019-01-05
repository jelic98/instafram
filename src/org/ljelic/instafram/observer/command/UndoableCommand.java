package org.ljelic.instafram.observer.command;

public abstract class UndoableCommand extends Command {

    public enum ExecutionState {
        UNDO,
        REDO
    }

    private ExecutionState state;

    public abstract void revert();
    public abstract UndoableCommand getClone();

    public ExecutionState getState() {
        return state;
    }

    public UndoableCommand setState(ExecutionState state) {
        this.state = state;

        return this;
    }

    @Override
    public boolean changesHistory() {
        return true;
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}