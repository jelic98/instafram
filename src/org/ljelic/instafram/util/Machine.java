package org.ljelic.instafram.util;

import org.ljelic.instafram.util.log.Log;

public class Machine {

    public static String lineSeparator() {
        return getProperty("line.separator");
    }

    public static String fileSeparator() {
        return getProperty("file.separator");
    }

    public static String userHome() {
        return getProperty("user.home");
    }

    public static String user() {
        return getProperty("user.name");
    }

    public static String platform() {
        return getProperty("os.name") + " " + getProperty("os.version");
    }

    private static String getProperty(String property) {
        String propery = "";

        try {
            propery = System.getProperty(property);
        }catch(Exception e) {
            Log.e(e.getMessage());
        }

        return propery;
    }
}