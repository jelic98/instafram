package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.util.log.Log;

public class ReadSettingsAction extends Command {

    @Override
    void execute() {
        try {
            Config.STREAM.read(Config.MODEL, Paths.getConfigPath());
        }catch(Exception e) {
            Log.e(e.getMessage());
        }
    }
}