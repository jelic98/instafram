package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.frame.FrameTemplate;

public class DiscardSettingsAction extends Command {

    private final FrameTemplate frame;

    public DiscardSettingsAction(FrameTemplate frame) {
        this.frame = frame;

        setStatus(Res.STRINGS.STATUS_DISCARD_SETTINGS);
    }

    @Override
    void execute() {
        if(frame != null) {
            frame.close();
        }
    }
}