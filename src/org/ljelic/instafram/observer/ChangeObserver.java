package org.ljelic.instafram.observer;

public interface ChangeObserver<T> {

    void onChange();
    void onChange(ChangeType type);
    void onChange(ChangeType type, T bundle);
}