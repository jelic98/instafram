package org.ljelic.instafram.util.error;

import java.io.IOException;

interface ErrorReporter {

    void report(String message) throws IOException;
}