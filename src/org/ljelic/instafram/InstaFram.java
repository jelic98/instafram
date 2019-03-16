package org.ljelic.instafram;

import org.ljelic.instafram.core.*;
import org.ljelic.instafram.observer.command.*;
import org.ljelic.instafram.util.error.ThreadErrorListener;
import org.ljelic.instafram.util.checker.CheckerAdapter;
import org.ljelic.instafram.util.checker.LicenseChecker;
import org.ljelic.instafram.util.checker.SessionChecker;
import org.ljelic.instafram.view.frame.LoginFrame;
import org.ljelic.instafram.view.frame.MainFrame;
import org.ljelic.instafram.view.frame.PreInstallationFrame;

/**
 * @author Lazar Jelic - ljelic2718rn@raf.rs
 * <p>
 * Activation code: TESTCODE
 * Username: admin
 * Password: 123
 * </p>
 */
public class InstaFram {

    public static void main(String[] args) {
        new InstaFram().start();
    }

	private void start() {
        new ThreadErrorListener().listen();

        if(!Config.DEBUG) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        CommandQueue.push(new ReadSettingsAction(), ThreadOptions.SINGLE_THREAD);
        new LicenseChecker(new CheckerAdapter() {
            @Override
            public void onSuccess() {
                new SessionChecker(new CheckerAdapter() {
                    @Override
                    public void onSuccess() {
                        if(Config.MODEL.get(Parameters.SESSION_USER).equals(User.INSTALLER.name())) {
                            new PreInstallationFrame().open();
                        }else {
                            new MainFrame().open();
                        }
                    }

                    @Override
                    public void onError(String message) {
                        new LoginFrame().open();
                    }
                }).check();
            }
        }).check();
    }
}