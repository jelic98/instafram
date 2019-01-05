package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;

public class HelpOfflineAction extends Command {

    @Override
    void execute() {
        DialogAdapter.info("Offline help should show up");
    }
}