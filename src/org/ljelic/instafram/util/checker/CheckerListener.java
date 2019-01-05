package org.ljelic.instafram.util.checker;

interface CheckerListener {

    void onError(String message);
    void onSuccess();
}