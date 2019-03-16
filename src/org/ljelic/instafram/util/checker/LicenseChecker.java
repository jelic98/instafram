package org.ljelic.instafram.util.checker;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Parameters;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.adapter.dialog.Input;

public class LicenseChecker implements CheckerListener {

    private final CheckerListener listener;
    private final LicenseActivator activator;

    public LicenseChecker(CheckerListener listener) {
        this.listener = listener;
        activator = new LicenseActivator(this);
    }

    public void check() {
        if(alreadyActivated()) {
            onSuccess();
            return;
        }

        String code = DialogAdapter.input(new Input(Res.STRINGS.INPUT_LICENSE_TITLE, Res.STRINGS.INPUT_LICENSE_MESSAGE));

        if(code == null || code.isEmpty()) {
            System.exit(0);
        }

        activator.activate(code);
    }

    @Override
    public void onError(String message) {
        DialogAdapter.error(message);

        check();

        listener.onError(message);
    }

    @Override
    public void onSuccess() {
        Config.MODEL.set(Parameters.ACTIVATED, "true");

        listener.onSuccess();
    }

    private boolean alreadyActivated() {
        return Boolean.valueOf(Config.MODEL.get(Parameters.ACTIVATED));
    }
}