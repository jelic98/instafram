package org.ljelic.instafram.util.io.stream.serial;

import org.ljelic.instafram.util.io.stream.Streamable;

public interface SerialStreamable<T> extends Streamable<T> {

    void handle(Object obj);
}