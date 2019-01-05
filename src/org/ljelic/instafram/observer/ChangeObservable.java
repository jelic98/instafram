package org.ljelic.instafram.observer;

public interface ChangeObservable<T> {

    void addObserver(ChangeObserver observer);
    void notifyObservers(ChangeType type, T bundle);
}