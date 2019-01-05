package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Parameters;
import org.ljelic.instafram.core.Transfer;
import org.ljelic.instafram.view.frame.FrameTemplate;
import org.ljelic.instafram.view.frame.LoginFrame;

public class LogoutAction extends Command {

    private FrameTemplate frame;

    public LogoutAction(FrameTemplate frame) {
        this.frame = frame;
    }

    @Override
    void execute() {
        Config.MODEL.reset(Parameters.SESSION_HASH);
        Config.MODEL.reset(Parameters.SESSION_USER);

        Transfer.instance().reset();

        new LoginFrame().open();

        frame.close();
    }
}