package org.ljelic.instafram.util.checker;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Parameters;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.util.api.RequestBuilder;
import org.ljelic.instafram.util.api.ResponseListener;
import java.io.IOException;

public class SessionChecker implements ResponseListener {

    private final CheckerListener listener;

    public SessionChecker(CheckerListener listener) {
        this.listener = listener;
    }

    public void check() {
        String sessionHash = Config.MODEL.get(Parameters.SESSION_HASH);

        try {
            new RequestBuilder(Config.SESSION_URL)
                    .addParameter("hash", sessionHash)
                    .setListener(this)
                    .build();
        }catch(IOException e) {
            listener.onError(Res.STRINGS.ERROR_LOGIN_FAILED);
        }
    }

    @Override
    public void onResponse(int code, String content) {
        if(code == 200) {
            Config.MODEL.set(Parameters.SESSION_HASH, content.substring(0, content.indexOf(' ')));
            Config.MODEL.set(Parameters.SESSION_USER, content.substring(content.indexOf(' ') + 1));

            listener.onSuccess();
        }else {
            listener.onError(content);
        }
    }
}