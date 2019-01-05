package org.ljelic.instafram.observer;

public abstract class ChangeObserverAdapter<T> implements ChangeObserver<T> {

    @Override
    public void onChange() {

    }

    @Override
    public void onChange(ChangeType type) {
        onChange();
    }

    @Override
    public void onChange(ChangeType type, T bundle) {
        onChange(type);
    }
}