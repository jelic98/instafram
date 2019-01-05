package org.ljelic.instafram.observer.command;

import java.awt.*;
import java.net.URI;
import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;

public class HelpOnlineAction extends Command {

    @Override
    void execute() {
        if(Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(Config.HELP_URL));
            }catch(Exception e) {
                DialogAdapter.info(Res.STRINGS.INFO_ONLINE_HELP + Config.HELP_URL);
            }
        }else {
            DialogAdapter.info(Res.STRINGS.INFO_ONLINE_HELP + Config.HELP_URL);
        }
    }
}