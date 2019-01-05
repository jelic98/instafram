package org.ljelic.instafram.view.frame;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Parameters;
import org.ljelic.instafram.core.Res;
import org.ljelic.instafram.core.User;
import org.ljelic.instafram.observer.ChangeObserverAdapter;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.util.api.RequestBuilder;
import org.ljelic.instafram.util.api.ResponseListener;
import org.ljelic.instafram.view.adapter.dialog.DialogAdapter;
import org.ljelic.instafram.view.component.*;

import java.io.IOException;

public class LoginFrame extends FrameTemplate implements ResponseListener {

    public LoginFrame() {
        super(Res.STRINGS.FRAME_LOGIN);
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

        Label lblUsername = Config.UI.getLabel();
        lblUsername.setText(Res.STRINGS.INPUT_USERNAME);
        layout.addComponent(lblUsername);

	    TextField tfUsername = Config.UI.getTextField();
	    tfUsername.setColumns(10);
        layout.addComponent(tfUsername);

        Label lblPassword = Config.UI.getLabel();
        lblPassword.setText(Res.STRINGS.INPUT_PASSWORD);
        layout.addComponent(lblPassword);

        PasswordField pfPassword = Config.UI.getPasswordField();
        pfPassword.setColumns(10);
        layout.addComponent(pfPassword);

        Button btnLogin = Config.UI.getButton();
        btnLogin.setText(Res.STRINGS.OPTION_LOGIN);
        btnLogin.addObserver(new ChangeObserverAdapter() {
            @Override
            public void onChange() {
                login(tfUsername.getText(), pfPassword.getText());
            }
        });
        layout.addComponent(btnLogin);

        frame.addComponent(layout);
        frame.pack();
	}

    @Override
    public void onResponse(int code, String content) {
        if(code == 200) {
            onSuccess(content);
        }else {
            onError(content);
        }
    }

    private void login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            onError(Res.STRINGS.ERROR_CREDENTIALS_REQUIRED);
            return;
        }

        try {
            new RequestBuilder(Config.LOGIN_URL)
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .setListener(this)
                    .build();
        }catch(IOException e) {
            onError(Res.STRINGS.ERROR_LOGIN_FAILED);
        }
    }

    private void onSuccess(String content) {
        Config.MODEL.set(Parameters.SESSION_HASH, content.substring(0, content.indexOf(' ')));
        Config.MODEL.set(Parameters.SESSION_USER, content.substring(content.indexOf(' ') + 1));

        if(Config.MODEL.get(Parameters.SESSION_USER).equals(User.INSTALLER.name())) {
            new PreInstallationFrame().open();
        }else {
            new MainFrame().open();
        }

        close();
    }

    private void onError(String message) {
        DialogAdapter.error(message);
    }
}