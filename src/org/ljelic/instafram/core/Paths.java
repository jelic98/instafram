package org.ljelic.instafram.core;

import org.ljelic.instafram.util.Machine;
import org.ljelic.instafram.util.log.Log;

import java.io.File;
import java.io.IOException;

public class Paths {

    public static String getConfigPath() {
        return getPath(Config.CONFIG_PATH);
    }

    public static String getLogPath() {
        return getPath(Config.LOG_PATH);
    }

    public static String getStatePath() {
        return getPath(Config.STATE_PATH);
    }

    public static String getHomePath() {
        String path = Config.MODEL.get(Parameters.HOME_PATH);
        String separator = Machine.fileSeparator();

        if(!path.substring(path.length() - 1).equals(separator)) {
            path += separator;
        }

        return path;
    }

    public static void initialize() throws IOException {
        create(getConfigPath());
        create(getLogPath());
        create(getStatePath());
    }

    private static File create(String path) throws IOException {
        File file = new File(path);

        if(!file.exists()) {
            if(file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            file.createNewFile();
        }

        return file;
    }

    public static String[] getExtensions() {
        String[] extensions = new String[Config.Extension.values().length];

        for(int i = 0; i < Config.Extension.values().length; i++) {
            extensions[i] = Config.Extension.values()[i].getSuffix();
        }

        return extensions;
    }

    private static String getPath(String path) {
        try {
            create(path);
        }catch(IOException e) {
            Log.e(e.getMessage());
        }

        return getHomePath() + path;
    }
}