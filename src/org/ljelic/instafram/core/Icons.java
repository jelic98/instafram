package org.ljelic.instafram.core;

public class Icons {

    public final String APP = getClass().getResource("/res/app.png").getPath();
    public final String NEW = getClass().getResource("/res/new.png").getPath();
    public final String OPEN = getClass().getResource("/res/open.png").getPath();
    public final String SAVE = getClass().getResource("/res/save.png").getPath();
    public final String SAVE_AS = getClass().getResource("/res/save_as.png").getPath();
    public final String EXIT = getClass().getResource("/res/exit.png").getPath();
    public final String UNDO = getClass().getResource("/res/undo.png").getPath();
    public final String REDO = getClass().getResource("/res/redo.png").getPath();
    public final String CUT = getClass().getResource("/res/cut.png").getPath();
    public final String COPY = getClass().getResource("/res/copy.png").getPath();
    public final String PASTE = getClass().getResource("/res/paste.png").getPath();
    public final String OFFLINE = getClass().getResource("/res/offline.png").getPath();
    public final String ONLINE = getClass().getResource("/res/online.png").getPath();
    public final String ABOUT = getClass().getResource("/res/about.png").getPath();
    public final String COMPANY = getClass().getResource("/res/company.png").getPath();
    public final String PRODUCT = getClass().getResource("/res/product.png").getPath();
    public final String MODULE = getClass().getResource("/res/module.png").getPath();
    public final String PARAMETER = getClass().getResource("/res/parameter.png").getPath();
    public final String ERROR = getClass().getResource("/res/error.png").getPath();
    public final String INFO = getClass().getResource("/res/info.png").getPath();
    public final String QUESTION = getClass().getResource("/res/question.png").getPath();
    public final String LOGOUT = getClass().getResource("/res/logout.png").getPath();
    public final String RUN = getClass().getResource("/res/run.png").getPath();
    public final String BUILD = getClass().getResource("/res/build.png").getPath();

    public final String IMAGE_DEFAULT = getClass().getResource("/res/no_photo.png").getPath();
    public final String IMAGE_AUTHOR = getClass().getResource("/res/author.png").getPath();

    Icons() {
        System.out.println("1: " + getClass().getResource("/res/app.png").getPath());
    }
}