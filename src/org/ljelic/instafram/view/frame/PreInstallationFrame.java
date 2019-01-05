package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Parameters;
import org.ljelic.instafram.core.Paths;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.command.CommandQueue;
import org.ljelic.instafram.observer.command.LogoutAction;
import org.ljelic.instafram.observer.command.RunAction;
import org.ljelic.instafram.util.api.RequestBuilder;
import org.ljelic.instafram.util.api.ResponseListener;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.component.*;

import java.io.File;
import java.io.IOException;

public class PreInstallationFrame extends FrameTemplate {

    public PreInstallationFrame() {
        super(Res.STRINGS.FRAME_INSTALL);
    }

	@Override
	protected void configure() {
        setSizeRatio(0.3f);

        frame.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange(ChangeType type) {
                if(type == ChangeType.CLOSE) {
                    close();
                }
            }
        });
	}
	
	@Override
	protected void populate() {
        Panel layout = Config.UI.getPanel();
        layout.setPadding(20, 20, 20, 20);
        layout.setLayout(Panel.Layout.NORMAL);

        Button btnRun = Config.UI.getButton();
        btnRun.setText(Res.STRINGS.MENU_RUN);
        btnRun.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                File selected = Config.UI.getFileChooser()
                        .setStartPath(Paths.getHomePath())
                        .setPathType(FileChooser.PathType.FILES_ONLY)
                        .setExtensions(Res.STRINGS.INFO_APP_FILES, Paths.getExtensions())
                        .getSingle();

                if(selected != null) {
                    CommandQueue.push(new RunAction(selected.getPath()));
                }
            }
        });
        layout.addComponent(btnRun);

        Button btnLogout = Config.UI.getButton();
        btnLogout.setText(Res.STRINGS.MENU_LOGOUT);
        btnLogout.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                CommandQueue.push(new LogoutAction(PreInstallationFrame.this));
            }
        });
        layout.addComponent(btnLogout);

        frame.addComponent(layout);
        frame.pack();
	}
}