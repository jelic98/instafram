package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.view.frame.AboutFrame;

public class AboutAction extends Command {

    @Override
    void execute() {
        new AboutFrame().open();
    }
}