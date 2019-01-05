package org.ljelic.instafram.core;

import org.ljelic.instafram.view.component.Tree;

public class Transfer {

    private static volatile Transfer instance;

    public Tree tree;

    public void reset() {
        tree = null;
    }

    private Transfer() {
        reset();
    }

    public static Transfer instance() {
        if(instance == null) {
            Transfer.synchronize();
        }

        return instance;
    }
    
    private static synchronized void synchronize() {
        if(instance == null) {
            instance = new Transfer();
        }
    }
}