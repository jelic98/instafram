package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.observer.ChangeObserver;
import org.ljelic.instafram.observer.ChangeType;

public abstract class Command implements ChangeObserver<Object> {

    private String status;
    protected boolean skipHistory;

    public Command() {
        skipHistory = false;
    }

    abstract void execute();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean hasStatus() {
        return status != null && !status.isEmpty();
    }

    public boolean isSkipHistory() {
        return skipHistory;
    }

    public Command skipHistory() {
        this.skipHistory = true;

        return this;
    }

    public Command skipHistory(boolean skipHistory) {
        this.skipHistory = skipHistory;

        return this;
    }

    public boolean changesHistory() {
        return false;
    }

    public boolean canUndo() {
        return false;
    }

    @Override
    public void onChange() {
        CommandQueue.push(this);
    }

    @Override
    public void onChange(ChangeType type) {
        onChange();
    }

    @Override
    public void onChange(ChangeType type, Object bundle) {
        onChange(type);
    }
}