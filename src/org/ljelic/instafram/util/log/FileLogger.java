package org.ljelic.instafram.util.log;

import org.ljelic.instafram.core.Config;
import org.ljelic.instafram.core.Paths;
import java.io.*;

public class FileLogger implements Logger {

    @Override
    public void log(String tag, String message) {
        String line = tag  + ": " +  message;

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(Paths.getLogPath(), true));
            writer.println(Config.HASHER.hash(line));
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}