package org.ljelic.instafram.view.adapter.action;

import org.ljelic.instafram.observer.command.Command;

public class ActionItem extends BarItem {

    private final Command action;

    public ActionItem(String text, Command action) {
        super(text);
        this.action = action;
    }

    ActionItem(String text, char mnemonic, Command action) {
        super(text, mnemonic);
        this.action = action;
    }

    public ActionItem(String text, byte[] icon, Command action) {
        super(text, icon);
        this.action = action;
    }

    ActionItem(String text, byte[] icon, char mnemonic, Command action) {
        super(text, icon, mnemonic);
        this.action = action;
    }

    public Command getAction() {
        return action;
    }
}