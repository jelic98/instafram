package org.ljelic.instafram.util;

import org.ljelic.instafram.observer.ChangeObserver;

public class DelayRunner {

    private final ChangeObserver obs;
    private final long delay;

    public DelayRunner(ChangeObserver data, long delay) {
        this.obs = data;
        this.delay = delay;
    }

    public void run() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while(!isInterrupted()) {
                        obs.onChange();
                        sleep(delay);
                    }
                }catch(InterruptedException e) {
                    interrupt();
                }
            }
        }.start();
    }
}
