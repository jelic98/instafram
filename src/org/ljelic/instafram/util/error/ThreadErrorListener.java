package org.ljelic.instafram.util.error;

import java.io.IOException;
import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.util.log.Log;

public class ThreadErrorListener implements ErrorListener {

    private final ErrorReporter reporter = new APIErrorReporter();

    @Override
    public void listen() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                String message = MessageExtractor.extract(throwable);

                if(!Config.DEBUG) {
                    try {
                        reporter.report(message);
                    }catch(IOException e) {
                        Log.e(message);
                    }
                }

                Log.e(message);
            }
        });
    }
}