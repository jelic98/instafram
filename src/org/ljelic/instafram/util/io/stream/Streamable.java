package org.ljelic.instafram.util.io.stream;

public interface Streamable<T> {

    Streamable<T> from(T streamable);
    T get();
}