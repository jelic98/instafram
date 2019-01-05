package org.ljelic.instafram.util.error;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MessageExtractor {

    public static String extract(Throwable e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(out));
        return new String(out.toByteArray());
    }
}