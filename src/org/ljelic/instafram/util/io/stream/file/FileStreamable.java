package org.ljelic.instafram.util.io.stream.file;

import org.ljelic.instafram.util.io.stream.Streamable;

public interface FileStreamable<T> extends Streamable<T> {

    void read(String line);
    String write(StringBuilder builder);
}