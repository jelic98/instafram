package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.view.frame.SettingsFrame;

public class SettingsAction extends Command {

    @Override
    void execute() {
        new SettingsFrame().open();
    }
}