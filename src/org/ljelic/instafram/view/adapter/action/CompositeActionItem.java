package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.observer.command.Command;

public final class CompositeActionItem extends ActionItem {

    private CompositeActionItem[] children;
    private boolean separated;

    public CompositeActionItem(String text, Command action) {
        super(text, action);
    }

    public CompositeActionItem(String text, char mnemonic, Command action) {
        super(text, mnemonic, action);
    }

    public CompositeActionItem(String text, byte[] icon, Command action) {
        super(text, icon, action);
    }

    public CompositeActionItem(String text, byte[] icon, char mnemonic, Command action) {
        super(text, icon, mnemonic, action);
    }

    public CompositeActionItem[] getChildren() {
        return children == null ? new CompositeActionItem[0] : children;
    }

    public boolean isEmpty() {
        return children == null || children.length == 0;
    }

    public CompositeActionItem children(CompositeActionItem[] children) {
        this.children = children;

        return this;
    }

    public boolean isSeparated() {
        return separated;
    }

    public CompositeActionItem separate() {
        separated = true;

        return this;
    }
}