package org.ljelic.instafram.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Icons {

    public final byte[] APP = getResource("/res/app.png");
    public final byte[] NEW = getResource("/res/new.png");
    public final byte[] OPEN = getResource("/res/open.png");
    public final byte[] SAVE = getResource("/res/save.png");
    public final byte[] SAVE_AS = getResource("/res/save_as.png");
    public final byte[] EXIT = getResource("/res/exit.png");
    public final byte[] UNDO = getResource("/res/undo.png");
    public final byte[] REDO = getResource("/res/redo.png");
    public final byte[] CUT = getResource("/res/cut.png");
    public final byte[] COPY = getResource("/res/copy.png");
    public final byte[] PASTE = getResource("/res/paste.png");
    public final byte[] OFFLINE = getResource("/res/offline.png");
    public final byte[] ONLINE = getResource("/res/online.png");
    public final byte[] ABOUT = getResource("/res/about.png");
    public final byte[] COMPANY = getResource("/res/company.png");
    public final byte[] PRODUCT = getResource("/res/product.png");
    public final byte[] MODULE = getResource("/res/module.png");
    public final byte[] PARAMETER = getResource("/res/parameter.png");
    public final byte[] ERROR = getResource("/res/error.png");
    public final byte[] INFO = getResource("/res/info.png");
    public final byte[] QUESTION = getResource("/res/question.png");
    public final byte[] LOGOUT = getResource("/res/logout.png");
    public final byte[] RUN = getResource("/res/run.png");
    public final byte[] BUILD = getResource("/res/build.png");

    public final byte[] IMAGE_DEFAULT = getResource("/res/no_photo.png");
    public final byte[] IMAGE_AUTHOR = getResource("/res/author.png");

    private byte[] getResource(String path) {
        byte[] res;

        try {
            InputStream is = getClass().getResourceAsStream(path);
            res = new byte[is.available()];
            new DataInputStream(is).readFully(res);
        }catch(IOException e) {
            res = new byte[0];
        }

        return res;
    }
}