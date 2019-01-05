package org.ljelic.instafram.util.error;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.util.api.RequestBuilder;
import java.io.IOException;

public class APIErrorReporter implements ErrorReporter {

    @Override
    public void report(String message) throws IOException {
        new RequestBuilder(Config.REPORT_API_URL)
                .addHeader("API_KEY", Config.REPORT_API_KEY)
                .addParameter("message", message)
                .build();
    }
}