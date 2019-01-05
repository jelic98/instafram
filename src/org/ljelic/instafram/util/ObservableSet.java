package org.ljelic.instafram.util;

import org.ljelic.instafram.observer.ChangeObservableDelegate;
import org.ljelic.instafram.observer.ChangeType;
import org.ljelic.instafram.observer.ChangeObservable;
import org.ljelic.instafram.observer.ChangeObserver;
import java.util.TreeSet;

public class ObservableSet<T> extends TreeSet<T> implements ChangeObservable<T> {

    private final ChangeObservableDelegate<T> delegate;

    public ObservableSet() {
        delegate = new ChangeObservableDelegate<>();
    }

    @Override
    public boolean add(T t) {
        boolean result = super.add(t);

        notifyObservers(ChangeType.ADDITION, t);

        return result;
    }

    public void delete(T t) {
        remove(t);

        notifyObservers(ChangeType.REMOVAL, t);
    }

    @Override
    public void addObserver(ChangeObserver observer) {
        delegate.addObserver(observer);
    }

    @Override
    public void notifyObservers(ChangeType type, T bundle) {
        delegate.notifyObservers(type, bundle);
    }
}