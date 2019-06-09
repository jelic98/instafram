package org.ljelic.instafram.core;

import org.ljelic.instafram.model.AbstractModel;
import org.ljelic.instafram.util.io.stream.Streamable;
import org.ljelic.instafram.util.io.stream.serial.SerialModel;
import org.ljelic.instafram.util.io.stream.serial.SerialStream;
import org.ljelic.instafram.util.io.hasher.Hasher;
import org.ljelic.instafram.util.io.hasher.SimpleHasher;
import org.ljelic.instafram.util.io.stream.Stream;
import org.ljelic.instafram.view.AbstractUI;
import org.ljelic.instafram.view.swing.SwingUI;
import static org.ljelic.instafram.core.Config.Extension.*;

public class Config {

    public enum Extension {
        LOG("InstaFramLog", ".ifl"),
        CONFIG("InstaFramConfiguration", ".ifc"),
        BUILD("InstaFramBuild", ".ifb"),
        STATE("InstaFramState", ".ifs");

        private final String name, suffix;

        Extension(String name, String suffix) {
            this.name = name;
            this.suffix = suffix;
        }

        public String getName() {
            return name;
        }

        public String getSuffix() {
            return suffix;
        }
    }

    public static final boolean DEBUG = false;

    public static final String VERSION = "1.0.1";
    public static final String AUTHOR = "Lazar Jelic RN 27/17";

    public static final RuntimeConfig MODEL = new RuntimeConfig();

    public static final Hasher HASHER = new SimpleHasher();

    public static final AbstractUI UI = new SwingUI();

//    public static final Stream<Streamable> STREAM = new FileStream<>();
//    public static final Streamable<AbstractModel> STREAMABLE = new FileModel();
    public static final Stream<Streamable> STREAM = new SerialStream<>();
    public static final Streamable<AbstractModel> STREAMABLE = new SerialModel();

    public static final int COMMAND_QUEUE_SIZE = 10;

    public static final String LOG_PATH = "log" + LOG.getSuffix();
    public static final String CONFIG_PATH = "config" + CONFIG.getSuffix();
    public static final String STATE_PATH = "state" + STATE.getSuffix();

    public static final String DEFAULT_BUILD_PATH = "./build.ifb";
    public static final String DEFAULT_HOME_PATH = ".";
    public static final String DEFAULT_LANGUAGE = "EN";
    public static final String DEFAULT_NODE_CONTENT = "No content";
    public static final String DEFAULT_PARAMETER_VALUE = "No value";

    // See all reported errors at https://lazarjelic.com/ecloga/projects/software_admin/report_show.php
    public static final String REPORT_API_URL = "https://lazarjelic.com/ecloga/projects/software_admin/report_add.php";
    public static final String REPORT_API_KEY = "INSTAFRAM2018RAF";

    public static final String ACTIVATION_URL = "https://lazarjelic.com/ecloga/projects/software_admin/license_activate.php";
    public static final String LOGIN_URL = "https://lazarjelic.com/ecloga/projects/software_admin/user_login.php";
    public static final String SESSION_URL = "https://lazarjelic.com/ecloga/projects/software_admin/user_session.php";
    public static final String HELP_URL = "https://lazarjelic.com/ecloga/projects/software_admin/help.php";
}