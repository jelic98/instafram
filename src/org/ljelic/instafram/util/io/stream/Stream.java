package org.ljelic.instafram.util.io.stream;

public interface Stream<T> {

    void read(T streamable, String path) throws Exception;
    void write(T streamable, String path) throws Exception;
}