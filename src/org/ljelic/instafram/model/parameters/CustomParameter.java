package org.ljelic.instafram.model.parameters;

import org.ljelic.instafram.model.Parameter;
import org.ljelic.instafram.model.ValueType;

public class CustomParameter extends Parameter {

    public CustomParameter() {
        super(getRandomName(), ValueType.TEXT);

        setEditable(true);
    }

    @Override
    public boolean isCustom() {
        return true;
    }

    private static String getRandomName() {
        return String.valueOf(System.currentTimeMillis());
    }
}