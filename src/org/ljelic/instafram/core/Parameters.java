package org.ljelic.instafram.core;

public enum Parameters {

    ACTIVATED("false", false),
    SESSION_HASH("default", false),
    SESSION_USER("default", false),
    BUILD_PATH(Config.DEFAULT_BUILD_PATH, true),
    HOME_PATH(Config.DEFAULT_HOME_PATH, false),
    LANGUAGE(Config.DEFAULT_LANGUAGE, true);

    private final String defaultValue;
    private final boolean editable;

    Parameters(String defaultValue, boolean editable) {
        this.defaultValue = defaultValue;
        this.editable = editable;
    }

    public String defaultValue() {
        return defaultValue;
    }

    public boolean isEditable() {
        return editable;
    }
}