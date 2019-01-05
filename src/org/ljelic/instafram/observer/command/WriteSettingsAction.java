package org.ljelic.instafram.observer.command;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.util.log.Log;

public class WriteSettingsAction extends Command {

    public WriteSettingsAction() {
        setStatus(Res.STRINGS.STATUS_WRITE_SETTINGS);
    }

    @Override
    void execute() {
        try {
            Config.STREAM.write(Config.MODEL, Paths.getConfigPath());
        }catch(Exception e) {
            Log.e(e.getMessage());
        }
    }
}