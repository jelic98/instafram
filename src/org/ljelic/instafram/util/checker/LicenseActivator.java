package org.ljelic.instafram.util.checker;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.util.api.RequestBuilder;
import org.ljelic.instafram.util.api.ResponseListener;
import java.io.IOException;

public class LicenseActivator implements ResponseListener {

    private final CheckerListener listener;

    LicenseActivator(CheckerListener listener) {
        this.listener = listener;
    }

    void activate(String code) {
        try {
            new RequestBuilder(Config.ACTIVATION_URL)
                    .addParameter("code", code)
                    .setListener(this)
                    .build();
        }catch(IOException e) {
            listener.onError(Res.STRINGS.ERROR_ACTIVATION_FAILED);
        }
    }

    @Override
    public void onResponse(int code, String content) {
        if(code == 200) {
            listener.onSuccess();
        }else {
            listener.onError(content);
        }
    }
}